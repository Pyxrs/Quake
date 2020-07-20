package io.github.simplycmd.silk.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.ClientBrandRetriever;

@Mixin(ClientBrandRetriever.class)
public class test {
    @Redirect(method = "getClientModName", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/ClientBrandRetrieverClass;getClientModName()S"))
    public static String getClientModName() {
        return "test";
    }
}