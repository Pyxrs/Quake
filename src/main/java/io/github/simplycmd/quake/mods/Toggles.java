package io.github.simplycmd.quake.mods;

import io.github.simplycmd.quake.gui.MenuGui;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.TranslatableText;

public class Toggles extends MenuGui {
    private static MinecraftClient client = MinecraftClient.getInstance();
    public static Boolean spToggle = false;
    public static Boolean snToggle = false;

    public static void ToggleSprint() {
        spToggle = !spToggle;
        if(client.options.sprintToggled) {
            client.player.sendMessage(new TranslatableText("msg.sprinthold"), false);
            client.options.sprintToggled = false;
        } else {
            client.player.sendMessage(new TranslatableText("msg.sprinttoggle"), false);
            client.options.sprintToggled = true;
        }
    }

    public static void ToggleSneak() {
        snToggle = !snToggle;
        if(client.options.sneakToggled) {
            client.player.sendMessage(new TranslatableText("msg.sneakhold"), false);
            client.options.sneakToggled = false;
        } else {
            client.player.sendMessage(new TranslatableText("msg.sneaktoggle"), false);
            client.options.sneakToggled = true;
        }
    }
}