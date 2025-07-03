package com.mahghuuuls.noattackingonladders;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = NoAttackingOnLaddersMod.MODID, name = NoAttackingOnLaddersMod.NAME, version = NoAttackingOnLaddersMod.VERSION)
public class NoAttackingOnLaddersMod {

	public static final String MODID = "noattackingonladders";
	public static final String NAME = "No Attacking On Ladders";
	public static final String VERSION = "1.0.0";

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onPlayerAttack(AttackEntityEvent event) {

		if (!ModConfig.blockMelee)
			return;
		EntityPlayer player = event.getEntityPlayer();

		if (player.isOnLadder() && !player.onGround) {
			event.setCanceled(true); // Tell the server to ignore the hit

			if (!player.world.isRemote && ModConfig.showMessages) {
				player.sendStatusMessage(new TextComponentString("You can't attack while climbing!"), true);
			}
		}
	}

	@SubscribeEvent
	public void onArrowLoose(ArrowLooseEvent event) {

		if (!ModConfig.blockBow)
			return;

		EntityPlayer player = event.getEntityPlayer();

		if (player.isOnLadder() && !player.onGround) {

			event.setCanceled(true); // stops the arrow from spawning

			if (!player.world.isRemote && ModConfig.showMessages) {
				player.sendStatusMessage(new TextComponentString("You can't shoot while climbing!"), true);
			}
		}
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent e) {
		if (e.getModID().equals(MODID)) {
			net.minecraftforge.common.config.ConfigManager.sync(MODID,
					net.minecraftforge.common.config.Config.Type.INSTANCE);
		}
	}
}
