package io.github.simplycmd.quake.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ClientPlayerEntity.class)
public class PvpParticlesMixin {
    /**
     * @author SimplyCmd
     * @reason Particle Multiplier
     */
    @Overwrite
    public void addCritParticles(Entity target) {
        for (int i = 0; i < 25; i++) MinecraftClient.getInstance().particleManager.addEmitter(target, ParticleTypes.CRIT);
    }
}
