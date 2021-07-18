package edu.AP.Project.ClashRoyale.Server.Model;

import edu.AP.Project.ClashRoyale.Model.Instructions.Server.ServerInstruction;

public class ClientHandler{
    public int loginCheck(ServerInstruction instruction) {
        String username = (String) instruction.getArg(0);
        String password = (String) instruction.getArg(1);
        return 0;
    }
}
