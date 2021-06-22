package io.github.simplycmd.quake.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.minecraft.client.gui.DrawableHelper.GUI_ICONS_TEXTURE;

@Mixin(PlayerListHud.class)
public class PingDisplayMixin {
    /**
     * @author SimplyCmd
     * @reason Ping display
     */
    @Overwrite
    public void renderLatencyIcon(MatrixStack matrices, int width, int x, int y, PlayerListEntry entry) {
        if (true) { // Add config
            int color;
            if (entry.getLatency() < 0) {
                color = 0;
            } else if (entry.getLatency() < 150) {
                color = 5635925;
            } else if (entry.getLatency() < 300) {
                color = 16777045;
            } else if (entry.getLatency() < 600) {
                color = 16755200;
            } else if (entry.getLatency() < 1000) {
                color = 16733525;
            } else {
                color = 11141120;
            }
            MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, entry.getLatency() + "ms", x + width + 52, y, color);
        } else {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, GUI_ICONS_TEXTURE);
            byte o;
            if (entry.getLatency() < 0) {
                o = 5;
            } else if (entry.getLatency() < 150) {
                o = 0;
            } else if (entry.getLatency() < 300) {
                o = 1;
            } else if (entry.getLatency() < 600) {
                o = 2;
            } else if (entry.getLatency() < 1000) {
                o = 3;
            } else {
                o = 4;
            }

            ((PlayerListHud) (Object) this).setZOffset(((PlayerListHud) (Object) this).getZOffset() + 100);
            ((PlayerListHud) (Object) this).drawTexture(matrices, x + width - 11, y, 0, 176 + o * 8, 10, 8);
            ((PlayerListHud) (Object) this).setZOffset(((PlayerListHud) (Object) this).getZOffset() - 100);
        }
    }
}
