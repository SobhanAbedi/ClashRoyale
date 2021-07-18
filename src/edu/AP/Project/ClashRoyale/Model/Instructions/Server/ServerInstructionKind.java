package edu.AP.Project.ClashRoyale.Model.Instructions.Server;

import java.util.HashMap;

public enum ServerInstructionKind {

    LOGIN((char)0, "login"), SIGNUP((char)1, "signup");

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
