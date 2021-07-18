package edu.AP.Project.ClashRoyale.Model.Instructions.Server;

import java.io.Serializable;
import java.util.Arrays;

public class ServerInstruction implements Serializable {
    private final char kind;
    private final Object[] args;

    public ServerInstruction(ServerInstructionKind kind, Object... args) {
        this.kind = kind.code;
        this.args = args;
    }

    public ServerInstructionKind getKind() {
        return ServerInstructionKind.getInstructionKind(kind);
    }

    public int getKindCode() {
        return (int)kind;
    }

    public Object getArg(int n) {
        if(n < args.length)
            return args[n];
        return null;
    }

    public Object[] getArgs() {
        return args;
    }

    @Override
    public String toString() {
        ServerInstructionKind kind = ServerInstructionKind.getInstructionKind(this.kind);
        if(kind == null)
            return null;
        return kind.name + Arrays.toString(args);
    }
}
