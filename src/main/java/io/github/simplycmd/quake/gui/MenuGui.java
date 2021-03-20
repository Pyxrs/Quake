package io.github.simplycmd.quake.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WToggleButton;
import net.minecraft.text.TranslatableText;

public class MenuGui extends LightweightGuiDescription {
    /*public MenuGui() {
        //Background
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(300, 200);

        //Title
        WLabel title = new WLabel("Quake Client");
        root.add(title, 0, 0);

        //Main
        WToggleButton fullbright = new WToggleButton(new TranslatableText("gui.button.fullbright"));
        ToggleButton(root, fullbright, 0, 1, 0);

        //Toggle Sprint
        WToggleButton toggleSprint = new WToggleButton(new TranslatableText("gui.button.sprint"));
        ToggleButton(root, toggleSprint, 0, 2, 1);

        //Toggle Sneak
        WToggleButton toggleSneak = new WToggleButton(new TranslatableText("gui.button.sneak"));
        ToggleButton(root, toggleSneak, 0, 3, 2);

        //Toggle Legacy Pvp
        WToggleButton legacyPvp = new WToggleButton(new TranslatableText("gui.button.pvp"));
        ToggleButton(root, legacyPvp, 0, 4, 3);

        root.validate(this);
    }

    private static void ToggleButton(WGridPanel root, WToggleButton button, int x, int y, int config) {
        root.add(button, x, y);
        button.setToggle(GetConfig(config));
        button.setOnToggle(on -> {
            if (button.getToggle()) {
                SetConfig(config, true);
            } else {
                SetConfig(config, false);
            }
        });
    }

    public static void SetConfig(int config, boolean output) {
        if (config == 0) {
            QUAKE_CONFIG.fullbright = output;
        } else if (config == 1) {
            QUAKE_CONFIG.sprint = output;
        } else if (config == 2) {
            QUAKE_CONFIG.sneak = output;
        } else if (config == 3) {
            QUAKE_CONFIG.pvp = output;
        } else {
            throw new RuntimeException("You gotta add the new case for the new option ya know?");
        }
    }

    public static Boolean GetConfig(int config) {
        if (config == 0) {
            return QUAKE_CONFIG.fullbright;
        } else if (config == 1) {
            return QUAKE_CONFIG.sprint;
        } else if (config == 2) {
            return QUAKE_CONFIG.sneak;
        } else if (config == 3) {
            return QUAKE_CONFIG.pvp;
        } else {
            throw new RuntimeException("You gotta add the new case for the new option ya know?");
        }
    }*/
}