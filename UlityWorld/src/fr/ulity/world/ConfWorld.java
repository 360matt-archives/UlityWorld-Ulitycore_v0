package fr.ulity.world;

import org.bukkit.World;

public class ConfWorld {
	
	public static void GenerateOrReload () {
		
		Main.worlds.get("defaults.AntiGrief", true);
		Main.worlds.get("defaults.explosions", true);
		Main.worlds.get("defaults.tnt", true);
		Main.worlds.get("defaults.MobsGrief", true);
		Main.worlds.get("defaults.invincible", false);
		Main.worlds.get("defaults.PvP", true);
		Main.worlds.get("defaults.DamageMobToPlayer", true);
		Main.worlds.get("defaults.DamagePlayerToMob", true);

		
		
		for (World x: Main.server.getWorlds()) {
			if (Main.worlds.get("worlds." + x.getName() + ".enabled") == null) 
				Main.worlds.get("worlds." + x.getName() + ".enabled", true);
			
			for (String y: Main.worlds.getSection("defaults").getKeys(false)) {
				
				if (!Main.worlds.isSet("worlds." + x.getName() + "." + y)) {
					Main.worlds.set("worlds." + x.getName() + "." + y, Main.worlds.get("defaults." + y));
				}
				
				
				
			}
			
			
		}
		
		
	}
	
}
