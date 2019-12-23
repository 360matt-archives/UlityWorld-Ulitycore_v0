package fr.ulity.world.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import fr.ulity.core.bukkit.Lang;
import fr.ulity.world.Main;

public class DamageEvent implements Listener {


	@EventHandler
	public void OnDamageSolo (EntityDamageEvent e) {
		if (Main.worlds.get("worlds." + e.getEntity().getWorld().getName() + ".invincible") != null) {
			if (Main.worlds.getBoolean("worlds." + e.getEntity().getWorld().getName() + ".invincible"))
				e.setCancelled(true);
		}
	}

	@EventHandler
	public void OnDamageWithAttacker (EntityDamageByEntityEvent e) {
		
		
		Entity victim = e.getEntity();
		Entity attacker = e.getDamager();

			
		if (Main.worlds.get("worlds." + victim.getWorld().getName() + ".invincible") != null) {
			if (Main.worlds.getBoolean("worlds." + victim.getWorld().getName() + ".invincible")){
				e.setCancelled(true);
				attacker.sendMessage(Lang.get("UlityWorld.PvPDisabled"));
			}
		}
		if (victim.getType().equals(EntityType.PLAYER) && attacker.getType().equals(EntityType.PLAYER)) {
			if (Main.worlds.get("worlds." + victim.getWorld().getName() + ".PvP") != null) {
				if (!Main.worlds.getBoolean("worlds." + victim.getWorld().getName() + ".PvP")){
					e.setCancelled(true);
					attacker.sendMessage(Lang.get("UlityWorld.PvPDisabled"));
				}
			}
		}
		else if (victim.getType().equals(EntityType.PLAYER) && !attacker.getType().equals(EntityType.PLAYER)) {
			if (Main.worlds.get("worlds." + victim.getWorld().getName() + ".DamageMobToPlayer") != null) {
				if (!Main.worlds.getBoolean("worlds." + victim.getWorld().getName() + ".DamageMobToPlayer"))
					e.setCancelled(true);
			}
		}
		else {
			if (Main.worlds.get("worlds." + victim.getWorld().getName() + ".DamagePlayerToMob") != null) {
				if (!Main.worlds.getBoolean("worlds." + victim.getWorld().getName() + ".DamagePlayerToMob")){
					e.setCancelled(true);
					attacker.sendMessage(Lang.get("UlityWorld.MobDamageDisabled"));
				}
			}
		}
			
			
	}
	
}
