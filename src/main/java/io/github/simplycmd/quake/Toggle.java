package io.github.simplycmd.quake;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import net.minecraft.text.LiteralText;

public class Toggle implements ClientModInitializer {
    private KeyBinding toggleSprint;
    private KeyBinding toggleSneak;

    @Override
    public void onInitializeClient() {
        SetupKeybinds();
    }

    private void SetupKeybinds() {
        toggleSprint = new KeyBinding(
            "key.quake.sprint",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_CONTROL,
            "key.categories.quake"
        );
        toggleSneak = new KeyBinding(
            "key.quake.sneak",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_SHIFT,
            "key.categories.quake"
        );

        KeyBindingHelper.registerKeyBinding(toggleSprint);
        KeyBindingHelper.registerKeyBinding(toggleSneak);

        ClientTickCallback.EVENT.register(e->keyPressed());
    }

    public void keyPressed() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (toggleSprint.wasPressed()) {
            if(client.options.sprintToggled == true) {
                client.player.sendMessage(new TranslatableText("msg.sprinthold"), false);
                client.options.sprintToggled = false;
            } else {
                client.player.sendMessage(new TranslatableText("msg.sprinttoggle"), false);
                client.options.sprintToggled = true;
            }
        }
        if (toggleSneak.wasPressed()) {
            if(client.options.sneakToggled == true) {
                client.player.sendMessage(new TranslatableText("msg.sneakhold"), false);
                client.options.sneakToggled = false;
            } else {
                client.player.sendMessage(new TranslatableText("msg.sneaktoggle"), false);
                client.options.sneakToggled = true;
            }
        }
    }
}