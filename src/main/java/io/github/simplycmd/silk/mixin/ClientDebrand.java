package io.github.simplycmd.silk.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.MinecraftClient;

@Mixin(MinecraftClient.class)
public class ClientDebrand {
	@Redirect(method = "getWindowTitle", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;isModded()Z"))
	public boolean isModded(MinecraftClient client) {
		//if (!Main.CONFIG.modded.windowTitle) return false;
        //return client.isModded();
        return false;
	}
}