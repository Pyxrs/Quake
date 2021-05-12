package io.github.simplycmd.quake.mixin;

import io.github.simplycmd.quake.render.CapeFeatureRenderer2;
import io.github.simplycmd.quake.render.ElytraFeatureRenderer2;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.client.render.entity.feature.CapeFeatureRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public class CustomCapeMixin extends LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
    public CustomCapeMixin(EntityRenderDispatcher dispatcher, PlayerEntityModel<AbstractClientPlayerEntity> model, float shadowRadius) {
        super(dispatcher, model, shadowRadius);
    }

    @Inject(method = {"<init>(Lnet/minecraft/client/render/entity/EntityRenderDispatcher;Z)V"}, at = @At("RETURN"))
    private void ConstructorMixinPlayerEntityRenderer(EntityRenderDispatcher dispatcher, boolean bl, CallbackInfo ci) {
        this.addFeature(new CapeFeatureRenderer2(this));
        //this.addFeature(new ElytraFeatureRenderer2(this));
        //this.features.removeIf(renderer -> renderer instanceof ElytraFeatureRenderer);
    }

    @Override
    public Identifier getTexture(AbstractClientPlayerEntity entity) {
        return entity.getSkinTexture();
    }
}
