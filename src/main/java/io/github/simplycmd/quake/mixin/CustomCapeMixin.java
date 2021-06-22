package io.github.simplycmd.quake.mixin;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import io.github.simplycmd.quake.Main;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(PlayerListEntry.class)
public class CustomCapeMixin {
    @Shadow
    @Final
    private GameProfile profile;
    @Shadow @Final
    private Map<MinecraftProfileTexture.Type, Identifier> textures;
    @Shadow
    private boolean texturesLoaded;

    // Thanks to https://github.com/Hibiii/Kappa for providing this code
    @Inject(at = @At("HEAD"), method = "loadTextures()V")
    protected void loadTextures(CallbackInfo info) {
        if(texturesLoaded) return;
        Main.loadCape(this.profile, id -> {
            this.textures.putIfAbsent(MinecraftProfileTexture.Type.CAPE, id);
        });
    }
}
