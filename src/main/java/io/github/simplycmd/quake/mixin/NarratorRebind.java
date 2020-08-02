package io.github.simplycmd.quake.mixin;

import io.github.simplycmd.quake.Keybinds;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Keyboard.class)
public class NarratorRebind {
    @ModifyConstant(
		method = "onKey",
		constant = @Constant(intValue = 66) //GLFW.GLFW_KEY_B
	)
	private int narrator(int old) {
		return KeyBindingHelper.getBoundKeyOf(Keybinds.narratorRebind).getCode();
	}
}