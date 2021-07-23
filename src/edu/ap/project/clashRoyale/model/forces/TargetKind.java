package edu.ap.project.clashRoyale.model.forces;

import java.util.HashMap;

public enum TargetKind {
    GROUND("ground", "Ground"), AIR("air", "Air"), GROUND_AND_AIR("all", "Ground And Air"), BUILDINGS("buildings", "Buildings");

    public final String name;
    public final String displayName;
    private final static HashMap<String, TargetKind> list;

    /**
     * Constructor
     * @param name name
     * @param displayName Display name
     */
    private TargetKind(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    /**
     * get target kind
     * @param name
     * @return
     */
    public static TargetKind getTargetKind(String name) {
        return TargetKind.list.get(name);
    }

    static {
        list = new HashMap<>();
        for(TargetKind kind : TargetKind.values()) {
            list.put(kind.name, kind);
        }
    }
}
