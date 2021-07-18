package edu.AP.Project.ClashRoyale.Model.Instructions.Client;

import edu.AP.Project.ClashRoyale.Model.Instructions.Server.ServerInstructionKind;

import java.io.Serializable;
import java.util.Arrays;

public class ClientInstruction implements Serializable {
    private static final long serialVersionUID = 737783L;
    private final char kind;
    private final Object[] args;

    public ClientInstruction(ClientInstructionKind kind, Object... args) {
        this.kind = kind.code;
        this.args = args;
    }

    public ClientInstructionKind getKind() {
        return ClientInstructionKind.getInstructionKind(kind);
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
        ClientInstructionKind kind = ClientInstructionKind.getInstructionKind(this.kind);
        if(kind == null)
            return null;
        return kind.name + Arrays.toString(args);
    }
}
