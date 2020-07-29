package io.github.simplycmd.quake;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class AntiGhost implements ClientModInitializer
{
    private KeyBinding requestBlocks;
    
    @Override
    public void onInitializeClient()
    {
        SetupKeybinds();
    }

    private void SetupKeybinds() {
        requestBlocks = new KeyBinding(
            "key.quake.reveal",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_G,
            "key.categories.quake"
        );

        KeyBindingHelper.registerKeyBinding(requestBlocks);

        ClientTickCallback.EVENT.register(e->keyPressed());
    }

    public void keyPressed() {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (requestBlocks.wasPressed()) {
            this.execute();
            player.sendMessage(new TranslatableText("msg.request"), false);
        }
    }
    
    public void execute() {
        MinecraftClient mc=MinecraftClient.getInstance();
        ClientPlayNetworkHandler conn = mc.getNetworkHandler();
        if (conn==null)
            return;
        BlockPos pos=mc.player.getBlockPos();
        for (int dx=-4; dx<=4; dx++)
            for (int dy=-4; dy<=4; dy++)
                for (int dz=-4; dz<=4; dz++) {
                    PlayerActionC2SPacket packet=new PlayerActionC2SPacket(
                            PlayerActionC2SPacket.Action.ABORT_DESTROY_BLOCK, 
                            new BlockPos(pos.getX()+dx, pos.getY()+dy, pos.getZ()+dz),
                            Direction.UP       // with ABORT_DESTROY_BLOCK, this value is unused
                    );
                    conn.sendPacket(packet);
                }
    }
}