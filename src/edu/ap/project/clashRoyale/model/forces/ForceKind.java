package edu.ap.project.clashRoyale.model.forces;

public enum ForceKind {
    SOLDIER(1, "Soldier"), BUILDING(2, "Building"), SPELL(3, "Spell");

    public final int forceCode;
    public final String displayName;

    /**
     * Force kind Constructor
     * @param forceCode force code
     * @param displayName display name
     */
    private ForceKind(int forceCode, String displayName) {
        this.forceCode = forceCode;
        this.displayName = displayName;
    }
}
