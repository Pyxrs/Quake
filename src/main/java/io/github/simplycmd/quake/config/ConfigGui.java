package io.github.simplycmd.quake.config;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Axis;
import net.minecraft.text.TranslatableText;

public class ConfigGui extends LightweightGuiDescription {
    public ConfigGui() {
        System.out.println("dudududu");

        //Background
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(300, 200);

        //Title
        WLabel title = new WLabel("Quake Client");
        root.add(title, 1, 1);

        //Main
        WToggleButton fullbright = new WToggleButton(new TranslatableText("gui.button.fullbright"));
        root.add(fullbright, 1, 2);

        //Toggle Sprint
        WToggleButton toggleSprint = new WToggleButton(new TranslatableText("gui.button.sprint"));
        root.add(toggleSprint, 1, 3);

        //Toggle Sneak
        WToggleButton toggleSneak = new WToggleButton(new TranslatableText("gui.button.sneak"));
        root.add(toggleSneak, 1, 4);

        //Toggle Legacy Pvp
        WToggleButton legacyPvp = new WToggleButton(new TranslatableText("gui.button.pvp"));
        root.add(legacyPvp, 1, 5);

        //Toggle Time Changer
        WToggleButton timeChanger = new WToggleButton(new TranslatableText("gui.button.time"));
        root.add(timeChanger, 1, 6);

        //Change Time Value
        WSlider timeSlider = new WSlider(0, 10, Axis.HORIZONTAL);
        root.add(timeSlider, 1, 7);

        root.validate(this);
    }
}