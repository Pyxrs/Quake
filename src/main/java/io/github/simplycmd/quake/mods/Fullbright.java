package io.github.simplycmd.quake.mods;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.GameOptions;

public class Fullbright {
    private static MinecraftClient client = MinecraftClient.getInstance();
    private static GameOptions gameOptions;
    public static Boolean fbToggle = false;
    
    public static void Fullbright() {
        if (gameOptions == null) {gameOptions = client.options;}
        fbToggle = !fbToggle;
        if (fbToggle) {gameOptions.gamma = 12.0D;} 
        else {gameOptions.gamma = 1.0D;}
    }
}