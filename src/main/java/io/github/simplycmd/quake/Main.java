package io.github.simplycmd.quake;

import io.github.simplycmd.quake.config.Settings;
import io.github.simplycmd.quake.features.Feature;
import lombok.Getter;
import me.lortseam.completeconfig.api.ConfigContainer;
import me.lortseam.completeconfig.data.Config;
import me.lortseam.completeconfig.data.Entry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.GameOptions;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import org.lwjgl.glfw.GLFW;

import java.util.Collection;


public class Main implements ClientModInitializer {
	/*
	By the way, this project uses Lombok
	https://objectcomputing.com/resources/publications/sett/january-2010-reducing-boilerplate-code-with-project-lombok
	 */

	public static final EntityAttributeModifier MODIFIER = new EntityAttributeModifier("speedy", 100, EntityAttributeModifier.Operation.MULTIPLY_BASE);
	public static final String MOD_ID = "quake";

	private static short tick = 0;

	@Getter
	private static final Config config = Config.builder(Main.MOD_ID)
			.add(new Settings())
			.build();

	public static GameOptions gameOptions = MinecraftClient.getInstance().options;

	// NORMAL FEATURES

	public static Feature fullbright = new Feature("fullbright", GLFW.GLFW_KEY_B) {
		@Override
		public void functionality() {
			if (!(Boolean) this.getConfigEntry().getValue()) {
				gameOptions.gamma = 1.0D;
			} else {
				gameOptions.gamma = 12.0D;
			}
		}
	};

	public static Feature sprint = new Feature("sprint", null) {
		@Override
		public void functionality() {

		}
	};

	public static Feature sneak = new Feature("sneak", null) {
		@Override
		public void functionality() {

		}
	};

	public static Feature ping = new Feature("ping", null) {
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

	@Override
	public void onInitializeClient() {
		fullbright.startup();
		sprint.startup();
		sneak.startup();
		ping.startup();
		pvp.startup();
		ClientTickCallback.EVENT.register(client -> { if (client.player != null) {
			if (fullbright.getKeyBinding().wasPressed()) unoReverse((Entry<Boolean>) fullbright.getConfigEntry());
		}});
	}

	private static void unoReverse(Entry<Boolean> entry) {
		entry.setValue(!((Boolean) entry.getValue()));
	}
}




		/*//Ghost Blocks
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
}*/
