package io.github.simplycmd.quake.features;

import io.github.simplycmd.quake.Main;
import lombok.Getter;
import lombok.Setter;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public abstract class Feature {
    @Getter
    @Setter
    String name;

    @Getter
    @Setter
    KeyBinding keyBinding;

    //@Getter
    //@Setter
    //Entry<?> configEntry;

    public Feature(String name, Integer key) {
        this.name = name;

        //startup();

        keyBinding = registerKeybinding(name, key);
    }

    public abstract void functionality();

    private static KeyBinding registerKeybinding(String name, Integer key) {
        final KeyBinding keyBinding = new KeyBinding("key.quake." + name, InputUtil.Type.KEYSYM, key, "key.categories.quake");
        KeyBindingHelper.registerKeyBinding(keyBinding);
        return keyBinding;
    }

    public void startup() {
        String name = this.getName();
        // Assign entry
    }
}
