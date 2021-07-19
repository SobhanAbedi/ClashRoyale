package edu.AP.Project.ClashRoyale.Model.Forces;

import java.util.HashMap;

public enum SpeedTier {
    SLOW(1, 0.5f, "Slow"), MEDIUM(2, 1f, "Medium"), FAST(3, 1.5f, "Fast");

    public final int tier;
    public final float speed;
    public final String displayName;

    private static HashMap<Integer, SpeedTier> list;

    static {
        for(SpeedTier tierItr : SpeedTier.values()) {
            list.put(tierItr.tier, tierItr);
        }
    }

    private SpeedTier(int tier, float speed, String displayName) {
        this.tier = tier;
        this.speed = speed;
        this.displayName = displayName;
    }

    public static SpeedTier getSpeedTier(int tier) {
        return list.get(tier);
    }
}
