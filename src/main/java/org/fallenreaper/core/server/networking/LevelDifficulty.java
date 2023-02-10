package org.fallenreaper.core.server.networking;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Comparator;

public enum LevelDifficulty {
    PEACEFUL(0, "peaceful"),
    EASY(1, "easy"),
    NORMAL(2, "normal"),
    HARD(3, "hard");

    private static final LevelDifficulty[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(LevelDifficulty::getId)).toArray((p_19035_) -> {
        return new LevelDifficulty[p_19035_];
    });
    private final int id;
    private final String key;

    private LevelDifficulty(int pId, String pKey) {
        this.id = pId;
        this.key = pKey;
    }

    public int getId() {
        return this.id;
    }



    public static LevelDifficulty byId(int pId) {
        return BY_ID[pId % BY_ID.length];
    }

    @Nullable
    public static LevelDifficulty byName(String pName) {
        for(LevelDifficulty difficulty : values()) {
            if (difficulty.key.equals(pName)) {
                return difficulty;
            }
        }

        return null;
    }

    public String getKey() {
        return this.key;
    }
}
