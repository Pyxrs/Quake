package io.github.simplycmd.quake.render;

import io.github.simplycmd.quake.Main;
import io.github.simplycmd.simplylib.SimplyLib;
import lombok.SneakyThrows;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class CapeFeatureRenderer2 extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
    public CapeFeatureRenderer2(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        Identifier texture = MinecraftClient.getInstance().getTextureManager().registerDynamicTexture("cheese", SimplyLib.imageFromURL(new URL("https://simplycmd.go-outsi.de/lwyffV.png"), 64, 32));
        if (entity.canRenderCapeTexture() && !entity.isInvisible() && entity.isPartVisible(PlayerModelPart.CAPE)) {
            final ItemStack itemStack = entity.getEquippedStack(EquipmentSlot.CHEST);
            if (itemStack.getItem() != Items.ELYTRA) {

                matrices.push();
                matrices.translate(0.0D, 0.0D, 0.125D);
                final double d = MathHelper.lerp(tickDelta, entity.prevCapeX, entity.capeX) - MathHelper.lerp(tickDelta, entity.prevX, entity.getX());
                final double e = MathHelper.lerp(tickDelta, entity.prevCapeY, entity.capeY) - MathHelper.lerp(tickDelta, entity.prevY, entity.getY());
                final double m = MathHelper.lerp(tickDelta, entity.prevCapeZ, entity.capeZ) - MathHelper.lerp(tickDelta, entity.prevZ, entity.getZ());
                final float n = entity.prevBodyYaw + (entity.bodyYaw - entity.prevBodyYaw);
                final double o = MathHelper.sin(n * 0.017453292F);
                final double p = -MathHelper.cos(n * 0.017453292F);
                float q = (float)e * 10.0F;
                q = MathHelper.clamp(q, -6.0F, 32.0F);
                float r = (float)(d * o + m * p) * 100.0F;
                r = MathHelper.clamp(r, 0.0F, 150.0F);
                float s = (float)(d * p - m * o) * 100.0F;
                s = MathHelper.clamp(s, -20.0F, 20.0F);
                if (r < 0.0F) {
                    r = 0.0F;
                }

                final float t = MathHelper.lerp(tickDelta, entity.prevStrideDistance, entity.strideDistance);
                q += MathHelper.sin(MathHelper.lerp(tickDelta, entity.prevHorizontalSpeed, entity.horizontalSpeed) * 6.0F) * 32.0F * t;
                if (entity.isInSneakingPose()) {
                    q += 25.0F;
                }

                matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(6.0F + r / 2.0F + q));
                matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(s / 2.0F));
                matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(180.0F - s / 2.0F));
                final VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntitySolid(texture));
                (this.getContextModel()).renderCape(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
                matrices.pop();
            }
        }
    }
}
