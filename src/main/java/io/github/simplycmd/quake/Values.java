package io.github.simplycmd.quake;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Values implements ClientModInitializer {
    public static boolean menu = false;
    public static boolean ghost = false;

    public static boolean fullbright = false;
    public static boolean sprint = false;
    public static boolean sneak = false;
    public static boolean pvp = false;
    public static boolean ping = false;

    private final KeyBinding menuKey = RegisterKeybinding("menu", GLFW.GLFW_KEY_RIGHT_SHIFT);
    private final KeyBinding ghostKey = RegisterKeybinding("reveal", GLFW.GLFW_KEY_G);
    private final KeyBinding fullbrightKey = RegisterKeybinding("fullbright", GLFW.GLFW_KEY_B);
    private final KeyBinding sprintKey = RegisterKeybinding("sprint", null);
    private final KeyBinding sneakKey = RegisterKeybinding("sneak", null);
    private final KeyBinding pvpKey = RegisterKeybinding("pvp", GLFW.GLFW_KEY_K);
    private final KeyBinding pingKey = RegisterKeybinding("ping", null);

    @Override
    public void onInitializeClient() {
        ClientTickCallback.EVENT.register(client -> { if (client.player != null) {
            if (menuKey.wasPressed()) menu = true;
            if (ghostKey.wasPressed()) ghost = true;

            if (fullbrightKey.wasPressed()) fullbright = !fullbright;
            if (sprintKey.wasPressed()) sprint = !sprint;
            if (sneakKey.wasPressed()) sneak = !sneak;
            if (pvpKey.wasPressed()) pvp = !pvp;
            if (pingKey.wasPressed()) ping = !ping;
        }});
    }

    private static KeyBinding RegisterKeybinding(String name, Integer key) {
        if (key == null) key = -1;
        final KeyBinding keyBinding = new KeyBinding("key.quake." + name, InputUtil.Type.KEYSYM, key, "key.categories.quake");
        KeyBindingHelper.registerKeyBinding(keyBinding);
        return keyBinding;
    }
}
