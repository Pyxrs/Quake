package io.github.simplycmd.quake;

import net.fabricmc.api.ModInitializer;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Quake implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "quake";
    public static final String MOD_NAME = "Quake Client";

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}