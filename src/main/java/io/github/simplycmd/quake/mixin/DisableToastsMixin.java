package io.github.simplycmd.quake.mixin;

import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ToastManager.class)
public class DisableToastsMixin {
    /**
     * @author SimplyCmd
     * @reason Remove toasts
     */
    @Overwrite
    public void add(Toast toast) {
    }
}
