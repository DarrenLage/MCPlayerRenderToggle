package dev.draco.playerrendertoggle.classes;

import java.util.UUID;

public class Cooldown {
    public UUID ID;
    public Long EpochTime;

    public Cooldown(UUID id, Long epochTime){
        this.ID = id;
        this.EpochTime = epochTime;
    }
}
