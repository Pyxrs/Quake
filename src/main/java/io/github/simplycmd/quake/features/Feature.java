package io.github.simplycmd.quake.features;

import io.github.simplycmd.quake.Main;
import lombok.Getter;
import lombok.Setter;
import me.lortseam.completeconfig.data.Entry;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;

public abstract class Feature {
    @Getter
    @Setter
    String name;

    @Getter
    @Setter
    KeyBinding keyBinding;

    @Getter
    @Setter
    Entry<?> configEntry;

    public Feature(String name, Integer key) {
        this.name = name;

        //startup();

        keyBinding = registerKeybinding(name, key);
    }

    public abstract void functionality();

    private static KeyBinding registerKeybinding(String name, Integer key) {
        if (key == null) key = -1;
        final KeyBinding keyBinding = new KeyBinding("key.quake." + name, InputUtil.Type.KEYSYM, key, "key.categories.quake");
        KeyBindingHelper.registerKeyBinding(keyBinding);
        return keyBinding;
    }

    public void startup() {
        String name = this.getName();
        while (Main.getConfig().getEntries().iterator().hasNext()) {
            Entry entry = Main.getConfig().getEntries().iterator().next();
            String entryName = entry.getID();
            System.out.println("AOISJDOAISJD: " + entryName);
            if (entryName.equals(name)) setConfigEntry(entry);
        }
        //System.out.println(Main.getConfig().getEntries());
    }
}
