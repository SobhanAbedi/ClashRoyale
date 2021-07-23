package edu.ap.project.clashRoyale.server.view;

public abstract class ServerUI {
    /**
     * Start server
     */
    public abstract void start();

    /**
     * Stop server
     */
    public abstract void stop();

    /**
     * send message
     * @param message message
     * @param ending ending of message
     * @param Color color of message
     */
    public abstract void sendMessage(String message, String ending, String Color);

    /**
     * send verification message
     * @param message verification message
     */
    public abstract void sendVerification(String message);

    /**
     * send error message
     * @param message error message
     */
    public abstract void sendError(String message);

    /**
     * stop Server
     */
    public abstract void stopServer();

}
