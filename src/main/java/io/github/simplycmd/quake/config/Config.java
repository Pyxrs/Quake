package io.github.simplycmd.quake.config;

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

    @Getter
    @Setter
    private boolean time_changer = false;

    @Getter
    @Setter
    private int time_changer_value = 0;

    public Config() {
    }

    public static Config getInstance() { return ConfigHandler.config; };
}
