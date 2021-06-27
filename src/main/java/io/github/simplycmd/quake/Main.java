package io.github.simplycmd.quake;

import com.mojang.authlib.GameProfile;
import io.github.simplycmd.quake.features.Feature;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.lwjgl.glfw.GLFW;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class Main implements ClientModInitializer {
	public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier("speedy", 100, EntityAttributeModifier.Operation.MULTIPLY_BASE);
	public static final String MOD_ID = "quake";

	private static short tick = 0;

	// NORMAL FEATURES

	public static Feature fullbright = new Feature("fullbright", GLFW.GLFW_KEY_UNKNOWN) {
		@Override
		public void functionality() {
			Config.getInstance().setFullbright(!Config.getInstance().isFullbright());
			if (Config.getInstance().isFullbright()) {
				MinecraftClient.getInstance().options.gamma = 1.0D;
			} else {
				MinecraftClient.getInstance().options.gamma = 12.0D;
			}
		}
	};

	public static Feature sprint = new Feature("sprint", GLFW.GLFW_KEY_UNKNOWN) {
		@Override
		public void functionality() {

		}
	};

	public static Feature sneak = new Feature("sneak", GLFW.GLFW_KEY_UNKNOWN) {
		@Override
		public void functionality() {

		}
	};

	public static Feature ping = new Feature("ping", GLFW.GLFW_KEY_UNKNOWN) {
		@Override
		public void functionality() {

		}
	};

	// EXPERIMENTAL FEATURES

	public static Feature pvp = new Feature("pvp", GLFW.GLFW_KEY_K) {
		@Override
		public void functionality() {

		}
	};

	public static final KeyBinding zoom = new KeyBinding("key.quake.zoom", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_C, "key.categories.quake");

	@Override
	public void onInitializeClient() {
		final KeyBinding print = new KeyBinding("key.quake.print", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F8, "key.categories.quake");
		KeyBindingHelper.registerKeyBinding(print);
		KeyBindingHelper.registerKeyBinding(zoom);

		ConfigHandler.init();
		fullbright.startup();
		sprint.startup();
		sneak.startup();
		ping.startup();
		pvp.startup();
		ClientTickCallback.EVENT.register(client -> { if (client.player != null) {
			if (fullbright.getKeyBinding().wasPressed()) fullbright.functionality();

			ConfigHandler.saveTick();

			if (print.wasPressed()) System.out.println(Config.getInstance().toString());
		}});
	}

	/*
	try {
				URL url = new URL("http://s.optifine.net/capes/" + player.getName() + ".png");
				NativeImage tex = NativeImage.read(url.openStream()); //uncrop(NativeImage.read(url.openStream()));
				NativeImageBackedTexture nIBT = new NativeImageBackedTexture(tex);
				Identifier id = MinecraftClient.getInstance().getTextureManager().registerDynamicTexture("cape" + player.getName().toLowerCase(), nIBT);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	 */
}