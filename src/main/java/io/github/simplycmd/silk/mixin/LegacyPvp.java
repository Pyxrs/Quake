package io.github.simplycmd.silk.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;

@Mixin(EntityAttributes.class)
public class LegacyPvp {
    @Final
    @Shadow
    @Mutable
    private static EntityAttribute GENERIC_ATTACK_SPEED;
    static{
        GENERIC_ATTACK_SPEED = (new ClampedEntityAttribute("attribute.name.generic.attack_speed", 104.0D, 100.0D, 1024.0D)).setTracked(true);
    }
}