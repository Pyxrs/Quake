package io.github.simplycmd.quake.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WToggleButton;
import io.github.simplycmd.quake.mods.Fullbright;
import net.minecraft.text.TranslatableText;

public class MenuGui extends LightweightGuiDescription {

    public MenuGui() {
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
        
        root.validate(this);
    }
}