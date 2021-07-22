package edu.ap.project.clashRoyale.model.instructions.server;

import java.io.Serializable;
import java.util.Arrays;

public class ServerInstruction implements Serializable {
    private static final long serialVersionUID = 536268L;
    private final char kind;
    private final Object[] args;

    /**
     * Server Instruction Constructor
     * @param kind kind of instruction
     * @param args input arguments
     */
    public ServerInstruction(ServerInstructionKind kind, Object... args) {
        this.kind = kind.code;
        this.args = args;
    }

    /**
     * get kind
     * @return kind
     */
    public ServerInstructionKind getKind() {
        return ServerInstructionKind.getInstructionKind(kind);
    }

    /**
     * get kind code
     * @return kind code
     */
    public int getKindCode() {
        return (int)kind;
    }

    /**
     * get nth argument
     * @param n th
     * @return nth argument
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
     * to string override
     * @return string
     */
    @Override
    public String toString() {
        ServerInstructionKind kind = ServerInstructionKind.getInstructionKind(this.kind);
        if(kind == null)
            return null;
        return kind.name + Arrays.toString(args);
    }
}
