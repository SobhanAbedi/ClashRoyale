package edu.AP.Project.ClashRoyale.Server.View;

public abstract class ServerUI {
    public abstract void start();
    public abstract void stop();
    public abstract void sendMessage(String message, String ending, String Color);
    public abstract void sendVerification(String message);
    public abstract void sendError(String message);
    public abstract void stopServer();

}
