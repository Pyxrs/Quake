package io.github.simplycmd.quake.mods;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.GameOptions;
import net.minecraft.text.TranslatableText;

public class Fullbright {
    private static MinecraftClient client = MinecraftClient.getInstance();
    public static GameOptions gameOptions;
    public static Boolean fbToggle = false;
    
    public static void Fullbright() {
        if (gameOptions == null) {gameOptions = client.options;}
        
        fbToggle = !fbToggle;

        if (fbToggle) {gameOptions.gamma = 12.0D;
            client.player.sendMessage(new TranslatableText("msg.fullbright"), false);} 
        else {gameOptions.gamma = 1.0D;
            client.player.sendMessage(new TranslatableText("msg.nobright"), false);}
    }
}