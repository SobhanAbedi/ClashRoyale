package edu.ap.project.clashRoyale.model.forces;

import java.util.HashMap;

public enum TargetKind {
    GROUND("ground", "Ground"), AIR("air", "Air"), GROUND_AND_AIR("all", "Ground And Air"), BUILDINGS("buildings", "Buildings");

    public final String name;
    public final String displayName;
    private final static HashMap<String, TargetKind> list;

    private TargetKind(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

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
