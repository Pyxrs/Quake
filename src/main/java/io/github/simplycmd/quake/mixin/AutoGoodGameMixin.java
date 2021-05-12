package io.github.simplycmd.quake.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class AutoGoodGameMixin {
    @Inject(method = "onGameMessage", at = @At("HEAD"))
    public void onGameMessage(GameMessageS2CPacket packet, CallbackInfo ci) {
        if (packet.getMessage().getString().contains("1st Killer - ")) {
            MinecraftClient.getInstance().player.sendChatMessage("gg");
        }
    }
}
