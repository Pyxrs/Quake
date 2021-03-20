package io.github.simplycmd.quake;

import io.github.simplycmd.quake.config.QuakeConfig;
import io.github.simplycmd.quake.gui.MenuGui;
import io.github.simplycmd.quake.gui.MenuScreen;
//import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
//import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.lwjgl.glfw.GLFW;


public class Main implements ClientModInitializer {
	public static final double MAX_BRIGHTNESS = 12.0D;
	public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier("speedy", 100, EntityAttributeModifier.Operation.MULTIPLY_BASE);
	public static final MinecraftClient CLIENT = MinecraftClient.getInstance();
	public static QuakeConfig QUAKE_CONFIG;

	public static GameOptions gameOptions;

	public static Boolean fullbrightOld;
	public static Boolean sprintOld;
	public static Boolean sneakOld;

	private final KeyBinding menu = new KeyBinding(
			"key.quake.menu",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_RIGHT_SHIFT,
			"key.categories.quake"
	);
	private final KeyBinding fullbrightKey = new KeyBinding(
			"key.quake.fullbright",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_UNKNOWN,
			"key.categories.quake"
	);
	private final KeyBinding toggleSprint = new KeyBinding(
			"key.quake.sprint",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_UNKNOWN,
			"key.categories.quake"
	);
	private final KeyBinding toggleSneak = new KeyBinding(
			"key.quake.sneak",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_UNKNOWN,
			"key.categories.quake"
	);
	private final KeyBinding togglePvp = new KeyBinding(
			"key.quake.pvp",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_K,
			"key.categories.quake"
	);
	private final KeyBinding ghostBlocks = new KeyBinding(
			"key.quake.reveal",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_G,
			"key.categories.quake"
	);

	@Override
	public void onInitializeClient() {
		//AutoConfig.register(QuakeConfig.class, JanksonConfigSerializer::new);
		QUAKE_CONFIG = new QuakeConfig(); //AutoConfig.getConfigHolder(QuakeConfig.class).getConfig();
		fullbrightOld = QUAKE_CONFIG.fullbright;
		sprintOld = QUAKE_CONFIG.sprint;
		sneakOld = QUAKE_CONFIG.sneak;
		RegisterKeybindings();

		ClientTickCallback.EVENT.register(client -> { if (client.player != null) {
			//Menu
			if (menu.wasPressed()) {
				client.openScreen(new MenuScreen(new MenuGui()));
			}

			//Fullbright
			if (fullbrightKey.wasPressed()) {
				QUAKE_CONFIG.fullbright = !QUAKE_CONFIG.fullbright;
				if (QUAKE_CONFIG.fullbright) {
					client.player.sendMessage(new TranslatableText("msg.fullbright"), false);
				} else {
					client.player.sendMessage(new TranslatableText("msg.nobright"), false);
				}
			}
			if (HasChanged(fullbrightOld, QUAKE_CONFIG.fullbright)) {
				FullbrightToggle();
				fullbrightOld = QUAKE_CONFIG.fullbright;
			}

			//Toggle Sprint
			if (toggleSprint.wasPressed()) {
				QUAKE_CONFIG.sprint = !QUAKE_CONFIG.sprint;
				if (QUAKE_CONFIG.sprint) {
					client.player.sendMessage(new TranslatableText("msg.sprinthold"), false);
				} else {
					client.player.sendMessage(new TranslatableText("msg.sprinttoggle"), false);
				}
			}
			if (HasChanged(sprintOld, QUAKE_CONFIG.sprint)) {
				SprintToggle();
				sprintOld = QUAKE_CONFIG.sprint;
			}

			//Toggle Sneak
			if (toggleSneak.wasPressed()) {
				QUAKE_CONFIG.sneak = !QUAKE_CONFIG.sneak;
				if (QUAKE_CONFIG.sneak) {
					client.player.sendMessage(new TranslatableText("msg.sneakhold"), false);
				} else {
					client.player.sendMessage(new TranslatableText("msg.sneaktoggle"), false);
				}
			}
			if (HasChanged(sneakOld, QUAKE_CONFIG.sneak)) {
				SneakToggle();
				sneakOld = QUAKE_CONFIG.sneak;
			}

			//Toggle Legacy Pvp - it needs to be constantly updated because Mojank
			if (togglePvp.wasPressed()) {
				QUAKE_CONFIG.pvp = !QUAKE_CONFIG.pvp;
				if (QUAKE_CONFIG.pvp) {
					client.player.sendMessage(new TranslatableText("msg.legacy"), false);
				} else {
					client.player.sendMessage(new TranslatableText("msg.normal"), false);
				}
			}
			PvpToggle();

			//Ghost Blocks
			if (ghostBlocks.wasPressed()) {
				ClientPlayNetworkHandler connection = client.getNetworkHandler();
				if (connection != null) {
					BlockPos pos = client.player.getBlockPos();
					for (int dx=-4; dx<=4; dx++)
						for (int dy=-4; dy<=4; dy++)
							for (int dz=-4; dz<=4; dz++) {
								PlayerActionC2SPacket packet=new PlayerActionC2SPacket(
										PlayerActionC2SPacket.Action.ABORT_DESTROY_BLOCK,
										new BlockPos(pos.getX()+dx, pos.getY()+dy, pos.getZ()+dz),
										Direction.UP
								);
								connection.sendPacket(packet);
							}
					client.player.sendMessage(new TranslatableText("msg.request"), false);
				}
			}
		}});
	}

	// Yea so these add the keybindings into the game because why not
	private void RegisterKeybindings() {
		KeyBindingHelper.registerKeyBinding(menu);
		KeyBindingHelper.registerKeyBinding(fullbrightKey);
		KeyBindingHelper.registerKeyBinding(toggleSprint);
		KeyBindingHelper.registerKeyBinding(toggleSneak);
		KeyBindingHelper.registerKeyBinding(togglePvp);
		KeyBindingHelper.registerKeyBinding(ghostBlocks);
	}

	public static void FullbrightToggle() {
		if (gameOptions == null) {gameOptions = CLIENT.options;}

		if (!QUAKE_CONFIG.fullbright) {
			gameOptions.gamma = 1.0D;
		} else {
			gameOptions.gamma = MAX_BRIGHTNESS;
		}
	}

	public static void SprintToggle() {
		if (!QUAKE_CONFIG.sprint) {
			CLIENT.options.sprintToggled = false;
		} else {
			CLIENT.options.sprintToggled = true;
		}
	}

	public static void SneakToggle() {
		if (!QUAKE_CONFIG.sneak) {
			CLIENT.options.sneakToggled = false;
		} else {
			CLIENT.options.sneakToggled = true;
		}
	}

	public static void PvpToggle() {
		if (QUAKE_CONFIG.pvp) {
			if (!CLIENT.player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_SPEED).hasModifier(MODIFIER))
			CLIENT.player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_SPEED).addPersistentModifier(MODIFIER);
		} else {
			if (CLIENT.player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_SPEED).hasModifier(MODIFIER))
			CLIENT.player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_SPEED).removeModifier(MODIFIER);
		}
	}

	public static Boolean HasChanged(Boolean previous, Boolean now) {
		if (previous != now) {
			return true;
		} else {
			return false;
		}
	}
}
