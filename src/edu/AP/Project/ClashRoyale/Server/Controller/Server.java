package edu.AP.Project.ClashRoyale.Server.Controller;

import edu.AP.Project.ClashRoyale.Model.Instructions.Server.ServerInstruction;
import edu.AP.Project.ClashRoyale.Model.Instructions.Server.ServerInstructionKind;
import edu.AP.Project.ClashRoyale.Server.Model.DBConnector;
import edu.AP.Project.ClashRoyale.Server.Network.ConnectionListener;
import edu.AP.Project.ClashRoyale.Server.View.ServerCLUI;
import edu.AP.Project.ClashRoyale.Server.View.ServerUI;

import java.sql.SQLException;

public class Server implements Runnable{

    private final Object stateLock;
    private String  state;
    private ConnectionListener listener;
    private Thread listenerThread;

    public Server() {
        stateLock = new Object();
        state = "Running";
    }

    @Override
    public void run() {
        ServerUI ui = new ServerCLUI(this);
        ui.start();
        listener = new ConnectionListener(ui, this);
        listenerThread = new Thread(listener);
        listenerThread.start();
        byte[] bArray = new byte[2];
        bArray[0] = 12;
        bArray[1] = 25;
        ServerInstruction instruction = new ServerInstruction(ServerInstructionKind.LOGIN, "SobhanAbedi", "TestPass", true, (Float)12.5f, bArray);
        System.out.println(instruction.toString());
        DBConnector dbConnector = new DBConnector();
        if(dbConnector.connect() == 0)
            ui.sendVerification("DataBase Connected");
        ui.sendMessage(Integer.toString(dbConnector.signup("SobhanAbedi", "TestPass")), "\n", null);
        try {
            System.out.println(dbConnector.usernameExists("SobhanAbedi"));
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        ui.sendMessage(Integer.toString(dbConnector.login("SobhanAbedi", "TestPass")), "\n", null);


        if(dbConnector.disconnect() == 0)
            ui.sendVerification("DataBase Disconnected");
        else
            ui.sendError("DataBase Connection Problem");
        while (!Thread.interrupted()) {
            synchronized (stateLock) {
                if (state.equals("Interrupted")) {
                    Thread.currentThread().interrupt();
                    continue;
                }
            }
        }
    }

    public void stop() {
        System.out.println("Trying to stop the server");
        listenerThread.interrupt();
        synchronized (stateLock) {
            state = "Interrupted";
        }
    }
}
