package fr.ulity.world.events;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;

import fr.ulity.core.bukkit.Lang;
import fr.ulity.core.bukkit.Temp;
import fr.ulity.world.Main;

public class AntiGriefEvent implements Listener {

	@EventHandler
	public void OnPlace (BlockPlaceEvent e) {
		
		if (Main.worlds.get("worlds." + e.getBlock().getWorld().getName() + ".AntiGrief") != null) {
			if (Main.worlds.getBoolean("worlds." + e.getBlock().getWorld().getName() + ".AntiGrief")){
				if (!Temp.isSet("player." + e.getPlayer().getName() + ".AntiGrief")) {
					e.setCancelled(true);
					e.getPlayer().sendMessage(Lang.get("UlityWorld.CannotEditBlocks"));
				}
			}
		}
	}
	
	@EventHandler
	public void OnBreak (BlockBreakEvent e) {
		if (Main.worlds.get("worlds." + e.getBlock().getWorld().getName() + ".AntiGrief") != null) {
			if (Main.worlds.getBoolean("worlds." + e.getBlock().getWorld().getName() + ".AntiGrief")){
				if (!Temp.isSet("player." + e.getPlayer().getName() + ".AntiGrief")) {
					e.setCancelled(true);
					e.getPlayer().sendMessage(Lang.get("UlityWorld.CannotEditBlocks"));
				}
			}
		}
	}
	
	
	@EventHandler
	public void EntityModifyWorld (EntityChangeBlockEvent e) {
		if (e.getEntity().getType() != EntityType.PLAYER) {
			if (Main.worlds.get("worlds." + e.getBlock().getWorld().getName() + ".MobsGrief") != null) {
				if (!Main.worlds.getBoolean("worlds." + e.getBlock().getWorld().getName() + ".MobsGrief")){
					e.setCancelled(true);
				}
			}
		}
	}
	
	
	
}
