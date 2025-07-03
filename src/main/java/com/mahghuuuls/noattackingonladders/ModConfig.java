package com.mahghuuuls.noattackingonladders;

import net.minecraftforge.common.config.Config;

@Config(modid = NoAttackingOnLaddersMod.MODID)
public final class ModConfig {

	@Config.Comment("Block melee attacks while the player is on a ladder or vine")
	public static boolean blockMelee = true;

	@Config.Comment("Block bow shooting while the player is on a ladder or vine")
	public static boolean blockBow = true;

	@Config.Comment("Show messages when an attack is blocked")
	public static boolean showMessages = true;

	private ModConfig() {
	}

}
