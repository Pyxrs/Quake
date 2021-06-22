package io.github.simplycmd.quake.mixin;

import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Deque;

@Mixin(ToastManager.class)
public class DisableToastsMixin {
    @Final
    @Shadow
    private Deque<Toast> toastQueue;
    /**
     * @author SimplyCmd
     * @reason Remove toasts
     */
    @Overwrite
    public void add(Toast toast) {
        if (false /* Replace with config check */) {
            this.toastQueue.add(toast);
        }
    }
}
