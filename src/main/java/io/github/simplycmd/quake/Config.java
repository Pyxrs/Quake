package io.github.simplycmd.quake;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class Config implements Serializable {
    @Getter
    @Setter
    private boolean fullbright = false;

    @Getter
    @Setter
    private boolean sprint = false;

    @Getter
    @Setter
    private boolean sneak = false;

    @Getter
    @Setter
    private boolean ping = false;

    @Getter
    @Setter
    private boolean pvp = false;

    public Config() {
    }

    public static Config getInstance() { return ConfigHandler.config; };

    @Override
    public String toString() {
        return "Fullbright: " + fullbright + ", Spring: " + sprint + ", Sneak: " + sneak + ", Ping: " + ping + ", PVP: " + pvp;
    }
}
