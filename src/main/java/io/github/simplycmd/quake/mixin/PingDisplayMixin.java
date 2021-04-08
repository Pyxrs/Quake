package io.github.simplycmd.quake.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.simplycmd.quake.Values;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(PlayerListHud.class)
public class PingDisplayMixin {
    /**
     * @author SimplyCmd
     * @reason Ping display
     */
    @Overwrite
    public void renderLatencyIcon(MatrixStack matrices, int i, int j, int k, PlayerListEntry entry) {
        if (Values.ping) {
            int color;
            if (entry.getLatency() < 0) {
                color = Formatting.BLACK.getColorValue();
            } else if (entry.getLatency() < 150) {
                color = Formatting.GREEN.getColorValue();
            } else if (entry.getLatency() < 300) {
                color = Formatting.YELLOW.getColorValue();
            } else if (entry.getLatency() < 600) {
                color = Formatting.GOLD.getColorValue();
            } else if (entry.getLatency() < 1000) {
                color = Formatting.RED.getColorValue();
            } else {
                color = Formatting.DARK_RED.getColorValue();
            }
            MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, entry.getLatency() + "ms", j + 52, k, color);
        } else {
            PlayerListHud playerList = ((PlayerListHud) (Object) this);

            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            MinecraftClient.getInstance().getTextureManager().bindTexture(PlayerListHud.GUI_ICONS_TEXTURE);
            byte r;
            if (entry.getLatency() < 0) {
                r = 5;
            } else if (entry.getLatency() < 150) {
                r = 0;
            } else if (entry.getLatency() < 300) {
                r = 1;
            } else if (entry.getLatency() < 600) {
                r = 2;
            } else if (entry.getLatency() < 1000) {
                r = 3;
            } else {
                r = 4;
            }
            playerList.setZOffset(playerList.getZOffset() + 100);
            playerList.drawTexture(matrices, j + i - 11, k, 0, 176 + r * 8, 10, 8);
            playerList.setZOffset(playerList.getZOffset() - 100);
        }
    }
}
