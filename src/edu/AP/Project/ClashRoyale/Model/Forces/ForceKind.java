package edu.AP.Project.ClashRoyale.Model.Forces;

public enum ForceKind {
    SOLDIER(1, "Soldier"), BUILDING(2, "Building"), SPELL(3, "Spell");

    public final int forceCode;
    public final String displayName;
    private ForceKind(int forceCode, String displayName) {
        this.forceCode = forceCode;
        this.displayName = displayName;
    }
}
