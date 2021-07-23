package edu.ap.project.clashRoyale.model.instructions.client;

import java.io.Serializable;
import java.util.Arrays;

public class ClientInstruction implements Serializable {
    private static final long serialVersionUID = 737783L;
    private final char kind;
    private final Object[] args;

    /**
     * Client instruction Constructor
     * @param kind kind of client instruction
     * @param args arguments
     */
    public ClientInstruction(ClientInstructionKind kind, Object... args) {
        this.kind = kind.code;
        this.args = args;
    }

    /**
     * get kind
     * @return kind
     */
    public ClientInstructionKind getKind() {
        return ClientInstructionKind.getInstructionKind(kind);
    }

    /**
     * get kind code
     * @return code of kind
     */
    public int getKindCode() {
        return (int)kind;
    }

    /**
     * get arg
     * @param n nth argument
     * @return argument number n
     */
    public Object getArg(int n) {
        if(n < args.length)
            return args[n];
        return null;
    }

    /**
     * get all arguments
     * @return args
     */
    public Object[] getArgs() {
        return args;
    }

    /**
     * override toString Method
     * @return String
     */
    @Override
    public String toString() {
        ClientInstructionKind kind = ClientInstructionKind.getInstructionKind(this.kind);
        if(kind == null)
            return null;
        return kind.name + Arrays.toString(args);
    }
}
