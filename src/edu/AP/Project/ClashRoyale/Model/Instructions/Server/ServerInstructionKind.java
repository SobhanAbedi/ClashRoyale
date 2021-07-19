package edu.AP.Project.ClashRoyale.Model.Instructions.Server;

import java.util.HashMap;

public enum ServerInstructionKind {

    LOGIN((char)0, "login"), SIGNUP((char)1, "signup"), GET_PLAYER_INFO((char)2, "get player info"),
    GET_ALL_CARDS((char)3, "get all cards"), GET_FORCE_INFO((char)4, "get force info"),
    GET_ALL_FORCES((char)5, "get all forces");

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
