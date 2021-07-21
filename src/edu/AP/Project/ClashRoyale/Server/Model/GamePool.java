package edu.AP.Project.ClashRoyale.Server.Model;

import edu.AP.Project.ClashRoyale.Server.Model.Players.ClientPlayer;
import edu.AP.Project.ClashRoyale.Server.Model.Players.Player;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class GamePool implements Runnable{
    private final Queue<ClientHandler> list;
    private final int groupSize;

    public GamePool(int groupSize) {
        list = new LinkedBlockingQueue<>();
        this.groupSize = groupSize;
    }

    @Override
    public void run() {

        while (!Thread.interrupted()) {
            synchronized (list) {
                try {
                    list.wait();
                    if(list.size() >= groupSize) {
                        System.out.println("starting a new game");
                        Player[] players = new Player[groupSize];
                        for(int i = 0; i < groupSize; i++) {
                            ClientHandler handler = list.poll();
                            players[i] = new ClientPlayer(handler.getUsername(), handler.getDeckCards(), (i/(groupSize/2))%2, handler, handler.getGameModel());
                            if(players[i] == null) {
                                System.out.println("Null Player entering a game");
                                //TODO: return other players to the queue
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

    }

    public void addToPool(ClientHandler handler) {
        if(handler == null)
            return;
        synchronized (list) {
            list.add(handler);
            list.notify();
        }
    }
}
