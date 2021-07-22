package edu.ap.project.clashRoyale.model.instructions.server;

import java.util.HashMap;

public enum ServerInstructionKind {

    LOGIN((char)0, "login"), SIGNUP((char)1, "signup"), GET_PLAYER_INFO((char)2, "get player info"),
    GET_ALL_CARDS((char)3, "get all cards"), GET_FORCE_INFO((char)4, "get force info"),
    GET_ALL_FORCES((char)5, "get all forces"), UPDATE_DECK((char)6, "update deck"),
    START_TRAINING_CAMP((char)7, "start training camp"), START_TRAINING_CAMP_SMART((char)8, "start training camp smart"),
    JOIN_1V1_POOL((char)9, "join 1v1 pool"), JOIN_2V2_POOL((char)10, "join 2v2 pool");

    public final char code;
    public final String name;
    private final static HashMap<Character, ServerInstructionKind> list;
    private ServerInstructionKind(char code, String name) {
        this.code = code;
        this.name = name;
    }

    public static ServerInstructionKind getInstructionKind(char code) {
        return ServerInstructionKind.list.get(code);
    }

    static {
       list = new HashMap<>();
       for(ServerInstructionKind kind : ServerInstructionKind.values()) {
           list.put(kind.code, kind);
       }
    }
}
