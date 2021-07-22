package edu.ap.project.clashRoyale.model.instructions.client;

import java.util.HashMap;

public enum ClientInstructionKind {
    SUCCESS((char)0, "success"), FAIL((char)1, "fail"), USER_INFO((char)2, "user info"), ALL_CARDS((char)3, "all cards"),
    FORCE_INFO((char)4, "force info"), ALL_FORCES_INFO((char)5, "all forces info");

    public final char code;
    public final String name;
    private final static HashMap<Character, ClientInstructionKind> list;
    private ClientInstructionKind(char code, String name) {
        this.code = code;
        this.name = name;
    }

    public static ClientInstructionKind getInstructionKind(char code) {
        return ClientInstructionKind.list.get(code);
    }

    static {
        list = new HashMap<>();
        for(ClientInstructionKind kind : ClientInstructionKind.values()) {
            list.put(kind.code, kind);
        }
    }
}
