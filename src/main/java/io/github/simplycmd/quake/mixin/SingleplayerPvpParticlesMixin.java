package io.github.simplycmd.quake.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntityAnimationS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ServerPlayerEntity.class)
public class SingleplayerPvpParticlesMixin {
    /**
     * @author SimplyCmd
     * @reason Particle Multiplier
     */
    @Overwrite
    public void addCritParticles(Entity target) {
        for (int i = 0; i < 25; i++) ((ServerPlayerEntity) (Object) this).getServerWorld().getChunkManager().sendToNearbyPlayers(((ServerPlayerEntity) (Object) this), new EntityAnimationS2CPacket(target, 4));
    }
}
