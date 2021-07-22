package edu.ap.project.clashRoyale.server.model.gameEngine;

import edu.ap.project.clashRoyale.model.Card;
import edu.ap.project.clashRoyale.model.forces.Building;
import edu.ap.project.clashRoyale.model.forces.Force;
import edu.ap.project.clashRoyale.model.forces.Soldier;
import edu.ap.project.clashRoyale.model.forces.Spell;
import edu.ap.project.clashRoyale.model.GlobalVariables;
import edu.ap.project.clashRoyale.model.instructions.server.ServerInstruction;
import edu.ap.project.clashRoyale.model.PointDouble;
import edu.ap.project.clashRoyale.server.controller.Server;
import edu.ap.project.clashRoyale.server.model.CardForce;
import edu.ap.project.clashRoyale.server.model.players.ClientPlayer;
import edu.ap.project.clashRoyale.server.model.players.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class GameEngine implements Runnable{
    private final Server server;
    private final int playerCount;
    private final Player[] players;
    private final float deltaTime;
    private int lastForceID;
    private HashMap<Integer, ForceEngine> currentForces;
    private HashMap<Integer, ForceEngine> deadForces;
    private ArrayList<ArrayList<ForceState>> gameState;
    private ArrayList<CreationRecord> creationRecords;
    private BlockingQueue<CardRequest> instructionQueue;
    private int gameSteps;
    private int quarterWidth;
    private int quarterLength;
    private int currentStep;
    private float currentTime;
    private int rebaseStep;

    /**
     * Game Engine Constructor
     * @param server
     * @param playerCount
     * @param players
     */
    public GameEngine(Server server, int playerCount, Player[] players) {
        this.server = server;
        this.playerCount = playerCount;
        this.players = players;
        deltaTime = GlobalVariables.DELTA_TIME;
        lastForceID = 0;
        currentForces = new HashMap<>();
        deadForces = new HashMap<>();
        gameSteps = (int)Math.ceil(GlobalVariables.GAME_DURATION / GlobalVariables.DELTA_TIME);
        gameState = new ArrayList<>(gameSteps);
        creationRecords = new ArrayList<>(100);
        instructionQueue = new LinkedBlockingQueue<>(5);
        currentStep = 0;
        currentTime = 0;
        quarterWidth = GlobalVariables.QUARTER_WIDTH;
        quarterLength = GlobalVariables.QUARTER_LENGTH;
        addInitialBuildings();
        rebaseStep = 0;
    }

    /**
     * get id
     * @return id
     */
    public int getID() {
        lastForceID++;
        return lastForceID;
    }

    /**
     * get current Forces
     * @return array of force engine
     */
    public ForceEngine[] getCurrentForces() {
        return (ForceEngine[]) currentForces.values().toArray();
    }

    /**
     * remove force
     * @param forceID force ID
     */
    public void removeForce(int forceID) {
        deadForces.put(forceID, currentForces.get(forceID));
        currentForces.remove(forceID);

        //Game end Conditions:
        if(playerCount == 2) {
            boolean zeroWin = false;
            boolean gameEnd = false;
            if(forceID == 1) {
                gameEnd = true;
                zeroWin = false;
            }
            if(forceID == 2) {
                gameEnd = true;
                zeroWin = true;
            }
            if(gameEnd) {
                if (players[0] instanceof ClientPlayer)
                    ((ClientPlayer) players[0]).endGame(zeroWin);
                if (players[1] instanceof ClientPlayer)
                    ((ClientPlayer) players[1]).endGame(!zeroWin);
            }
        }else if (playerCount == 4) {
            boolean zeroWin = false;
            boolean gameEnd = false;
            if(currentForces.get(1) == null && currentForces.get(2) == null) {
                gameEnd = true;
                zeroWin = false;
            }
            if(currentForces.get(3) == null && currentForces.get(4) == null) {
                gameEnd = true;
                zeroWin = true;
            }
            if(gameEnd) {
                if (players[0] instanceof ClientPlayer)
                    ((ClientPlayer) players[0]).endGame(zeroWin);
                if (players[1] instanceof ClientPlayer)
                    ((ClientPlayer) players[1]).endGame(zeroWin);
                if (players[2] instanceof ClientPlayer)
                    ((ClientPlayer) players[2]).endGame(!zeroWin);
                if (players[3] instanceof ClientPlayer)
                    ((ClientPlayer) players[3]).endGame(!zeroWin);
            }
        }
    }

    /**
     * get force
     * @param forceID force ID
     * @return Force Engine
     */
    public ForceEngine getForce(int forceID){
        return currentForces.get(forceID);
    }

    /**
     * add force
     * @param force force
     */
    public void addForce(ForceEngine force) {
        currentForces.put(force.getForceID(), force);
        creationRecords.add(new CreationRecord(force.getForceID(), currentTime));
    }

    /**
     * add initial buildings
     */
    private void addInitialBuildings() {
        PointDouble princessLocation0 = new PointDouble(5.5, 6.5 - quarterLength);
        PointDouble princessLocation1 = new PointDouble(-5.5, 6.5 - quarterLength);
        int[] level = new int[4];
        if(playerCount == 2) {
            PointDouble kingLocation0 = new PointDouble(0, 3-quarterLength);
            BuildingEngine king0 = new BuildingEngine(this, 0, (Building) server.getForceInfo("King Tower", players[0].getLevel()), kingLocation0);
            BuildingEngine king1 = new BuildingEngine(this, 1, (Building) server.getForceInfo("King Tower", players[1].getLevel()), kingLocation0.reverseMapPoint());
            level[0] = players[0].getLevel();
            level[1] = level[0];
            level[2] = players[1].getLevel();
            level[3] = level[0];
            addForce(king0);
            addForce(king1);
        } else if (playerCount == 4) {
            PointDouble kingLocation0 = new PointDouble(-2, 3-quarterLength);
            PointDouble kingLocation1 = new PointDouble(2, 3-quarterLength);
            BuildingEngine king00 = new BuildingEngine(this, 0, (Building) server.getForceInfo("King Tower", players[0].getLevel()), kingLocation0);
            BuildingEngine king01 = new BuildingEngine(this, 0, (Building) server.getForceInfo("King Tower", players[1].getLevel()), kingLocation1);
            BuildingEngine king10 = new BuildingEngine(this, 1, (Building) server.getForceInfo("King Tower", players[2].getLevel()), kingLocation0.reverseMapPoint());
            BuildingEngine king11 = new BuildingEngine(this, 1, (Building) server.getForceInfo("King Tower", players[3].getLevel()), kingLocation1.reverseMapPoint());
            level[0] = players[0].getLevel();
            level[1] = players[1].getLevel();
            level[2] = players[2].getLevel();
            level[3] = players[3].getLevel();
            addForce(king00);
            addForce(king01);
            addForce(king10);
            addForce(king11);
        }
        BuildingEngine princess00 = new BuildingEngine(this, 0, (Building) server.getForceInfo("Princess Tower", level[0]), princessLocation0);
        BuildingEngine princess01 = new BuildingEngine(this, 0, (Building) server.getForceInfo("Princess Tower", level[1]), princessLocation1);
        BuildingEngine princess10 = new BuildingEngine(this, 1, (Building) server.getForceInfo("Princess Tower", level[2]), princessLocation0.reverseMapPoint());
        BuildingEngine princess11 = new BuildingEngine(this, 1, (Building) server.getForceInfo("Princess Tower", level[3]), princessLocation1.reverseMapPoint());
        addForce(princess00);
        addForce(princess01);
        addForce(princess10);
        addForce(princess11);



    }

    /**
     * run simulation
     */
    private void runSimulation() {
        if(currentStep == 0) {
            ArrayList<ForceState> currentState = new ArrayList<>(16);
            for(ForceEngine force : currentForces.values()) {
                currentState.add(force.getState());
            }
            gameState.add(currentState);
        }
        while (currentStep < gameSteps) {
            currentTime += deltaTime;
            currentStep++;

            for(ForceEngine force : currentForces.values()) {
                force.doAction();
            }

            ArrayList<ForceState> currentState = new ArrayList<>(16);
            for(ForceEngine force : currentForces.values()) {
                currentState.add(force.getNextState());
                force.next();
                force.genNextState();
            }
            gameState.add(currentState);
        }
    }

    /**
     * role back simulation
     * @param time time
     */
    private void roleBackSimulation(float time) {
        int roleToStep = (int)(time / deltaTime);
        float roleToTime =  roleToStep * deltaTime;
        if(roleToStep > gameState.size())
            return;
        Iterator<CreationRecord> iterator = creationRecords.iterator();
        int minIdRemoved = GlobalVariables.MAX_ID_IN_GAME;
        while (iterator.hasNext()) {
            CreationRecord record = iterator.next();
            if(record.getTime() > roleToTime) {
                if(record.getId() < minIdRemoved)
                    minIdRemoved = record.getId();
                currentForces.remove(record.getId());
                deadForces.remove(record.getId());
                iterator.remove();
            }
        }
        lastForceID = minIdRemoved;
        for(int key : currentForces.keySet()) {
            if(key >= lastForceID)
                System.out.println("**GameEngine RoleBack Error**");
        }
        for(int key : deadForces.keySet()) {
            if(key >= lastForceID)
                System.out.println("**GameEngine RoleBack Error**");
        }
        int maxIndex = gameState.size();

        //TODO: make sure this is ok. Intellij changed it to this
        if (maxIndex > roleToStep + 1) {
            gameState.subList(roleToStep + 1, maxIndex).clear();
        }

        currentStep = roleToStep;
        rebaseStep = currentStep;
        currentTime = roleToTime;
    }

    public void putCardRequest(Player player, ServerInstruction instruction) {
        try {
            instructionQueue.put(new CardRequest(instruction, player));
        }catch (InterruptedException e) {
            System.out.println("Couldn't add Your request to game instruction queue: " + e.toString());
        }
    }

    /**
     * run this Thread
     */
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            runSimulation();
            for (Player player : players) {
                player.updatePlayer(gameState, rebaseStep);
            }

            CardRequest request;
            try {
                request = instructionQueue.take();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                continue;
            }
            ServerInstruction instruction = request.getInstruction();
            Player player = request.getPlayer();

            Card card = (Card) instruction.getArg(0);
            PointDouble location = (PointDouble) instruction.getArg(1);
            float time = (Float) instruction.getArg(2);
            ArrayList<CardForce> forces = server.getCardForces(card.getName(), 1);

            roleBackSimulation(time);

            for(CardForce force : forces) {
                Force referenceForce = server.getForceInfo(force.getForceName(), player.getLevel());
                ForceEngine forceEngine = null;
                if(gameState.isEmpty())
                    System.out.println("**GameState is empty**");
                switch (referenceForce.getForceKind()) {
                    case SOLDIER:
                        forceEngine = new SoldierEngine(this, player.getSide(), (Soldier) referenceForce, location.combineWith(force.getRelLocation(), false));
                        break;
                    case BUILDING:
                        forceEngine = new BuildingEngine(this, player.getSide(), (Building) referenceForce, location.combineWith(force.getRelLocation(), false));
                        break;
                    case SPELL:
                        forceEngine = new SpellEngine(this, player.getSide(), (Spell) referenceForce, location.combineWith(force.getRelLocation(), false));
                        break;
                }
                if(forceEngine != null) {
                    currentForces.put(forceEngine.getForceID(), forceEngine);
                    gameState.get(gameState.size() - 1).add(forceEngine.getState());
                }
            }
        }
    }

}


class CreationRecord {
    private final int id;
    private final float time;

    /**
     * Constructor
     * @param id id
     * @param time time
     */
    public CreationRecord(int id, float time) {
        this.id = id;
        this.time = time;
    }

    /**
     * get id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * get time
     * @return time
     */
    public float getTime() {
        return time;
    }
}

class CardRequest {
    private final ServerInstruction instruction;
    private final Player player;

    /**
     * request Call Constructor
     * @param instruction instruction
     * @param player player
     */
    public CardRequest(ServerInstruction instruction, Player player) {
        this.instruction = instruction;
        this.player = player;
    }

    /**
     * get instruction
     * @return instruction
     */
    public ServerInstruction getInstruction() {
        return instruction;
    }

    /**
     * get player
     * @return player
     */
    public Player getPlayer() {
        return player;
    }
}