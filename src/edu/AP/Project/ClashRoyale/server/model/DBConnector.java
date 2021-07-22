package edu.ap.project.clashRoyale.server.model;

import edu.ap.project.clashRoyale.model.Card;
import edu.ap.project.clashRoyale.model.forces.*;
import edu.ap.project.clashRoyale.model.GlobalVariables;
import edu.ap.project.clashRoyale.model.PlayerInfo;
import edu.ap.project.clashRoyale.model.PointDouble;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
        int userid = login(username, password);
        insertDeck(userid);
        return userid;
    }

    public PlayerInfo getUserInfo(int userid) {
        String query1 = "SELECT username, score, coins FROM users WHERE userid=" + userid;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query1);
            if(!resultSet.next())
                return null;
            String username = resultSet.getString("username");
            int score = resultSet.getInt("score");
            int coins = resultSet.getInt("coins");
            String query2 = "SELECT * FROM level_score WHERE score>=" + score + " ORDER BY score ASC LIMIT 1";
            closeStatement(statement);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query2);
            int level = 1;
            if(!resultSet.next()) {
                level = GlobalVariables.MAX_LEVEL;
            } else {
                level = resultSet.getInt("level");
            }
            closeStatement(statement);
            return new PlayerInfo(username, userid, score, coins, level);
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public Card[] getAllCardsGeneral() {
        String query1 = "SELECT * FROM cards";

        try {
            Statement statement1 = connection.createStatement();
            ResultSet resultSet1 = statement1.executeQuery(query1);
            ArrayList<Card> cards = new ArrayList<>();
            while (resultSet1.next()) {
                String name = resultSet1.getString("name");
                int cost = resultSet1.getInt("cost");
                ArrayList<String> forces = new ArrayList<>();
                String query2 = "SELECT force_name, round FROM card_forces WHERE card_name='"+ name +"' ORDER BY round ASC";
                Statement statement2 = connection.createStatement();
                ResultSet resultSet2 = statement2.executeQuery(query2);
                while (resultSet2.next()) {
                    String forceName = resultSet2.getString("force_name");
                    if(!forces.contains(forceName))
                        forces.add(forceName);
                }
                Card card = new Card(name, cost);
                if(forces.size() > 0)
                    card.setForces(forces.toArray(new String[0]));
                cards.add(card);
                closeStatement(statement2);
            }
            closeStatement(statement1);
            return cards.toArray(new Card[0]);
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public HashMap<String, CardForces> getAllCardForces() {
        String query = "SELECT card_name, round, force_name, ST_X(rel_position) AS rel_x, ST_Y(rel_position) AS rel_y FROM card_forces ORDER BY card_name ASC, round ASC, force_name ASC;";
        HashMap<String, CardForces> result = new HashMap<>(20);
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String cardName = resultSet.getString("card_name");
                if(!result.containsKey(cardName))
                    result.put(cardName, new CardForces(cardName));
                String forceName = resultSet.getString("force_name");
                int round = resultSet.getInt("round");
                double relX = resultSet.getDouble("rel_x");
                double relY = resultSet.getDouble("rel_y");
                result.get(cardName).addForce(forceName, new PointDouble(relX, relY), round);
            }
            closeStatement(statement);
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }
        return result;
    }

    public HashMap<String, Force[]> getAllForces () {
        HashMap<String , Force[]> forceList = new HashMap<>();
        String query;
        Statement statement;
        ResultSet resultSet;
        try {
            //Get Soldiers
            query = "SELECT ca.name, va.level, va.hp, va.damage, ca.hit_speed, ca.speed_tier, ca.target, ca.range, ca.area_splash, ca.flies, ca.projectile FROM soldiers_constant_attributes AS ca JOIN variable_attributes AS va WHERE ca.name=va.name";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                if(!forceList.containsKey(name)){
                    forceList.put(name, new Force[GlobalVariables.MAX_LEVEL]);
                }
                Force[] force = forceList.get(name);
                force[resultSet.getInt("level") - 1] = new Soldier(resultSet.getString("name"), resultSet.getInt("hp"), resultSet.getInt("damage"), resultSet.getFloat("hit_speed"), SpeedTier.getSpeedTier(resultSet.getInt("speed_tier")), TargetKind.getTargetKind(resultSet.getString("target")), resultSet.getFloat("range"), resultSet.getBoolean("area_splash"), resultSet.getBoolean("flies"), resultSet.getInt("projectile"));
            }
            closeStatement(statement);

            //Get Buildings
            query = "SELECT ca.name, va.level, va.hp, va.damage, ca.hit_speed, ca.target, ca.range, ca.lifetime, ca.projectile FROM building_constant_attributes AS ca JOIN variable_attributes AS va WHERE ca.name=va.name";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                if(!forceList.containsKey(name)){
                    forceList.put(name, new Force[GlobalVariables.MAX_LEVEL]);
                }
                Force[] force = forceList.get(name);
                force[resultSet.getInt("level") - 1] = new Building(resultSet.getString("name"), resultSet.getInt("hp"), resultSet.getInt("damage"), resultSet.getFloat("hit_speed"),  TargetKind.getTargetKind(resultSet.getString("target")), resultSet.getFloat("range"), resultSet.getInt("lifetime"), resultSet.getInt("projectile"));
            }
            closeStatement(statement);

            //Get Spells
            query = "SELECT ca.name, va.level, ca.radius, va.damage, va.duration FROM spell_constant_attributes AS ca JOIN spell_variable_attributes AS va WHERE ca.name=va.name";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                if(!forceList.containsKey(name)){
                    forceList.put(name, new Force[GlobalVariables.MAX_LEVEL]);
                }
                Force[] force = forceList.get(name);
                force[resultSet.getInt("level") - 1] = new Spell(resultSet.getString("name"),resultSet.getFloat("radius"), resultSet.getInt("damage"), resultSet.getFloat("duration"));
            }
            closeStatement(statement);

            return forceList;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public String[] getDeck(int userid) {
        String query = "SELECT place, card_name FROM deck WHERE userid=" + userid + " ORDER BY place ASC";
        String[] deck = new String[GlobalVariables.DECK_SIZE];
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            int index = 0;
            while (resultSet.next()) {
                deck[index] = resultSet.getString("card_name");
                index++;
            }
            closeStatement(statement);
            return deck;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public Force getForceInfo(String forceName, int userLevel) {
        String query1 = "SELECT kind FROM force_kind WHERE name='" + forceName +"'";
        int forceKind = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query1);
            if(!resultSet.next())
                return null;
            forceKind = resultSet.getInt("kind");
            closeStatement(statement);

            String query2;
            statement = connection.createStatement();
            switch (forceKind) {
                case 1:
                    query2 = "SELECT ca.name, va.hp, va.damage, ca.hit_speed, ca.speed_tier, ca.target, ca.range, ca.area_splash, ca.flies, ca.projectile FROM soldiers_constant_attributes AS ca JOIN variable_attributes AS va WHERE ca.name='"+forceName+"' and va.name='"+forceName+"' and va.level="+userLevel;
                    resultSet = statement.executeQuery(query2);
                    closeStatement(statement);
                    if(!resultSet.next())
                        return null;
                    return new Soldier(resultSet.getString("name"), resultSet.getInt("hp"), resultSet.getInt("damage"), resultSet.getFloat("hit_speed"), SpeedTier.getSpeedTier(resultSet.getInt("speed_tier")), TargetKind.getTargetKind(resultSet.getString("target")), resultSet.getFloat("range"), resultSet.getBoolean("area_splash"), resultSet.getBoolean("flies"), resultSet.getInt("projectile"));
                case 2:
                    query2 = "SELECT ca.name, va.hp, va.damage, ca.hit_speed, ca.target, ca.range, ca.lifetime, ca.projectile FROM building_constant_attributes AS ca JOIN variable_attributes AS va WHERE ca.name='"+forceName+"' and va.name='"+forceName+"' and va.level="+userLevel;
                    resultSet = statement.executeQuery(query2);
                    closeStatement(statement);
                    if(!resultSet.next())
                        return null;
                    return new Building(resultSet.getString("name"), resultSet.getInt("hp"), resultSet.getInt("damage"), resultSet.getFloat("hit_speed"),  TargetKind.getTargetKind(resultSet.getString("target")), resultSet.getFloat("range"), resultSet.getInt("lifetime"), resultSet.getInt("projectile"));
                case 3:
                    query2 = "SELECT ca.name, ca.radius, va.damage, va.duration FROM spell_constant_attributes AS ca JOIN spell_variable_attributes AS va WHERE ca.name='"+forceName+"' and va.name='"+forceName+"' and va.level="+userLevel;
                    resultSet = statement.executeQuery(query2);
                    closeStatement(statement);
                    if(!resultSet.next())
                        return null;
                    return new Spell(resultSet.getString("name"),resultSet.getFloat("radius"), resultSet.getInt("damage"), resultSet.getFloat("duration"));
                default:
                    return null;
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public int updateUserScore(int userid, int newScore) {
        try {
            String query = "UPDATE users SET score=" +newScore+ " WHERE userid=" +userid;
            Statement statement = connection.createStatement();
            int res = statement.executeUpdate(query);
            closeStatement(statement);
            if(res == 0)
                return -1;
            return 0;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return -1;
        }
    }

    private int insertDeck(int userid) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("INSERT INTO deck (`userid`, `place`) VALUES");
            for(int i = 1; i <= GlobalVariables.DECK_SIZE; i++) {
                if(i > 1)
                    stringBuilder.append(",");
                stringBuilder.append(" (").append(userid).append(", ").append(i).append(")");

            }
            stringBuilder.append(";");
            String query = stringBuilder.toString();
            System.out.println("query: " + query);
            Statement statement = connection.createStatement();
            int res = statement.executeUpdate(query);
            closeStatement(statement);
            if(res == 0)
                return -1;
            return 0;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return -1;
        }
    }

    public int updateDeck(int userid, int place, String cardName) {
        if(place > GlobalVariables.DECK_SIZE)
            return -1;
        try {
            String query = "UPDATE deck SET card_name='" +cardName+ "' WHERE userid=" +userid+ " AND place=" +place;
            Statement statement = connection.createStatement();
            int res = statement.executeUpdate(query);
            closeStatement(statement);
            if(res == 0)
                return -1;
            return 0;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return -1;
        }
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
