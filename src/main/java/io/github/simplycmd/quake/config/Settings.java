package io.github.simplycmd.quake.config;

import me.lortseam.completeconfig.api.ConfigContainer;
import me.lortseam.completeconfig.api.ConfigEntries;
import me.lortseam.completeconfig.api.ConfigEntry;
import me.lortseam.completeconfig.api.ConfigGroup;

import java.util.Arrays;
import java.util.List;

public final class Settings implements ConfigContainer {

    @Transitive
    @ConfigEntries
    public static class Features implements ConfigGroup {

        @ConfigEntry(tooltipTranslationKeys = {"fullbrightTooltip"})
        private boolean fullbright;

        @ConfigEntry(tooltipTranslationKeys = {"sprintTooltip"})
        private boolean sprint;

        @ConfigEntry(tooltipTranslationKeys = {"sneakTooltip"})
        private boolean sneak;
    }

    @Transitive
    @ConfigEntries
    public static class Experimental implements ConfigGroup {

        @ConfigEntry(tooltipTranslationKeys = {"pvpTooltip1", "pvpTooltip2", "pvpTooltip3"})
        private boolean pvp;

    }

}