package io.github.simplycmd.quake.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WToggleButton;
import io.github.simplycmd.quake.mods.Fullbright;
import io.github.simplycmd.quake.mods.Toggles;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.TranslatableText;

public class MenuGui extends LightweightGuiDescription {
    private static MinecraftClient client = MinecraftClient.getInstance();

    public MenuGui() {
        //Sync toggles with options
        Sync();

        //Background
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(300, 200);
        
        //Title
        WLabel title = new WLabel("Quake Client");
        root.add(title, 0, 0);

        //Fullbright
        WToggleButton fullbright = new WToggleButton(new TranslatableText("gui.button.fullbright"));
        root.add(fullbright, 0, 1);
        fullbright.setToggle(Fullbright.fbToggle);
        fullbright.setOnToggle(b -> Fullbright.Fullbright());

        //Toggle Sprint
        WToggleButton toggleSprint = new WToggleButton(new TranslatableText("gui.button.togglesprint"));
        root.add(toggleSprint, 0, 2);
        toggleSprint.setToggle(Toggles.spToggle);
        toggleSprint.setOnToggle(b -> Toggles.ToggleSprint());

        //Toggle Sneak
        WToggleButton toggleSneak = new WToggleButton(new TranslatableText("gui.button.togglesneak"));
        root.add(toggleSneak, 0, 3);
        toggleSneak.setToggle(Toggles.snToggle);
        toggleSneak.setOnToggle(b -> Toggles.ToggleSneak());
        
        root.validate(this);
    }

    public static void Sync() {
        if (client.options != null) {
            Toggles.spToggle = client.options.sprintToggled;
            Toggles.snToggle = client.options.sneakToggled;
            if (Fullbright.gameOptions == null) {Fullbright.gameOptions = client.options;}
            if (Fullbright.gameOptions.gamma == 12.0D) {
                Fullbright.fbToggle = true;
            }
        }
    }
}