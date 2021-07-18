package edu.AP.Project.ClashRoyale.Server.Model;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.*;
import java.util.Arrays;

public class DBConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/clash_royale";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "TestPass12345";
    private static final int SALT_LEN = 16;
    private static final int PASS_LEN = 128;
    private Connection connection;

    public int connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            return -2;
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            disconnect();
            return -1;
        }

        return 0;
    }

    public int login(String username, String password) {
        String query = "SELECT userid, salt, password FROM users WHERE username = '" + username + "'";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            int userid = 0;
            byte[] salt = null;
            byte[] pass = null;
            while (resultSet.next()) {
                if(pass != null)
                    return -5; // duplicate username!
                //TODO: do something about signed and unsigned int mixture
                userid = resultSet.getInt("userid");
                salt = resultSet.getBytes("salt");
                pass = resultSet.getBytes("password");
            }
            if(pass == null)
                return -2; // wrong username

            byte[] passToCheck = PBKDF2Hash(password, salt, PASS_LEN);
            if(passToCheck == null)
                return -6; // encryption error
            if(Arrays.equals(pass, passToCheck))
                return userid;
            else
                return -3; // wrong username;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return -1; //connection error
        }
    }

    private byte[] getSalt(int len) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[len];
        random.nextBytes(salt);
        return salt;
    }

    private byte[] PBKDF2Hash(String password, byte[] salt, int length) {
        SecretKeyFactory factory;
        byte[] hash;
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, length * 8);

        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Wrong encryption Algorithm: " + e.toString());
            return null;
        }

        try {
            hash = factory.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            System.out.println("Invalid encryption key: " + e.toString());
            return null;
        }
        return hash;
    }

    private String byteArrToStr(byte[] arr) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < arr.length; i++)
            stringBuilder.append((char)arr[i]);
        return stringBuilder.toString();
    }

    public boolean usernameExists(String username) throws SQLException {
        String query = "SELECT userid FROM users WHERE username = '" + username + "'";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            int userid = 0;
            return resultSet.next();

        } catch (SQLException e) {
            System.out.println(e.toString());
            throw e;
        }
    }

    public int signup(String username, String password) {
        try {
            if (usernameExists(username))
                return -2; // taken username
        } catch (SQLException e) {
            System.out.println(e.toString());
            return -1; //connection error
        }
        byte[] salt = getSalt(SALT_LEN);
        byte[] hashedPass = PBKDF2Hash(password, salt, PASS_LEN);
        if(hashedPass == null)
            return -6; // encryption error
        //System.out.println(salt + ", salt size: " + salt.length);
        //for(int i = 0; i < SALT_LEN; i++)
        //    System.out.print((char)salt[i]);
        //System.out.println();
        //System.out.println(byteArrToStr(salt));
        //System.out.println(hashedPass + ", hashedPass size: " + hashedPass.length);
        String query = "INSERT INTO users (username, salt, password) VALUES ('" + username +"',  ?, ?)";
        //System.out.println(query);
        try {
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBytes(1, salt);
            preparedStatement.setBytes(2, hashedPass);
            int changes = preparedStatement.executeUpdate();
            if(changes == 0)
                return -5; //something went wrong
        } catch (SQLException e) {
            System.out.println(e.toString());
            return -1; //connection error
        }
        return login(username, password);
    }

    private int closeStatement(Statement statement) {
        try {
            if(statement != null && !statement.isClosed())
            statement.close();
        } catch (SQLException e) {
            return -1;
        }
        return 0;
    }

    public int disconnect() {
        try {
            if(connection != null && !connection.isClosed())
            connection.close();
        } catch (SQLException e) {
            return -1;
        }
        return 0;
    }
}
