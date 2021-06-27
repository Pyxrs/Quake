package io.github.simplycmd.quake.gui;

import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.simplycmd.quake.Config;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

public class ConfigGui extends LightweightGuiDescription {
    public ConfigGui() {
        //Background
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(300, 200);

        //Title
        WLabel title = new WLabel("Quake Client");
        root.add(title, 1, 1);

        //Main
        WToggleButton fullbright = new WToggleButton(new TranslatableText("gui.button.fullbright"));
        ToggleButton(root, fullbright, 1, 2, 0);

        //Toggle Sprint
        WToggleButton toggleSprint = new WToggleButton(new TranslatableText("gui.button.sprint"));
        ToggleButton(root, toggleSprint, 1, 3, 1);

        //Toggle Sneak
        WToggleButton toggleSneak = new WToggleButton(new TranslatableText("gui.button.sneak"));
        ToggleButton(root, toggleSneak, 1, 4, 2);

        //Toggle Legacy Pvp
        WToggleButton legacyPvp = new WToggleButton(new TranslatableText("gui.button.pvp"));
        ToggleButton(root, legacyPvp, 1, 5, 3);

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
            Config.getInstance().setFullbright(output);
        } else if (config == 1) {
            Config.getInstance().setSprint(output);
        } else if (config == 2) {
            Config.getInstance().setSneak(output);
        } else if (config == 3) {
            Config.getInstance().setPvp(output);
        } else {
            throw new RuntimeException("You gotta add the new case for the new option ya know?");
        }
    }

    public static Boolean GetConfig(int config) {
        if (config == 0) {
            return Config.getInstance().isFullbright();
        } else if (config == 1) {
            return Config.getInstance().isSprint();
        } else if (config == 2) {
            return Config.getInstance().isSneak();
        } else if (config == 3) {
            return Config.getInstance().isPvp();
        } else {
            throw new RuntimeException("You gotta add the new case for the new option ya know?");
        }
    }
}