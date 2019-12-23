package fr.ulity.world.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.mrmicky.fastparticle.FastParticle;
import fr.mrmicky.fastparticle.ParticleType;
import fr.ulity.core.bukkit.Lang;
import fr.ulity.world.Main;

public class ExplosionsEvent implements Listener {

	@EventHandler
	public void OnEntityExplode (EntityExplodeEvent e) {
		if (Main.worlds.get("worlds." + e.getEntity().getWorld().getName() + ".explosions") != null) {
			if (!Main.worlds.getBoolean("worlds." + e.getEntity().getWorld().getName() + ".explosions")){
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void OnBlockExplode (BlockExplodeEvent e) {
		if (Main.worlds.get("worlds." + e.getBlock().getWorld().getName() + ".explosions") != null) {
			if (!Main.worlds.getBoolean("worlds." + e.getBlock().getWorld().getName() + ".explosions")){
				e.setCancelled(true);
			}
		}
	}
	
	@SuppressWarnings("deprecation") // m'en blc
	@EventHandler
	public void OnActivateTNT (PlayerInteractEvent e) {
		
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.TNT) {
			if (e.getPlayer().getItemInHand().getType().equals(Material.FLINT_AND_STEEL)) {
				if (Main.worlds.get("worlds." + e.getClickedBlock().getWorld().getName() + ".explosions") != null) {
					if (!Main.worlds.getBoolean("worlds." + e.getClickedBlock().getWorld().getName() + ".explosions")){
						e.setCancelled(true);
						e.getClickedBlock().setType(Material.AIR);
						e.getPlayer().sendMessage(Lang.get("UlityWorld.OnActivateTNT_disabledWorld"));
						FastParticle.spawnParticle(e.getClickedBlock().getWorld(), ParticleType.SMOKE_LARGE, e.getClickedBlock().getLocation(), 50);
					}
				}
			}
		}
		

	}
	
	
	static boolean DangerOooooh (Material material) {
		switch (material.name()) {
			case "REDSTONE_BLOCK":
			case "REDSTONE_TORCH":
			case "LEVER":
			case "LAVA":
			case "LEGACY_LAVA":
			case "REDSTONE_WIRE":
				return true;
			default:
				return false;
		}
	}
	
	
	@EventHandler
	public void OnPlaceFrontSideTNT (BlockPlaceEvent e) {
		
		
		if (Main.worlds.get("worlds." + e.getBlock().getWorld().getName() + ".explosions") != null) {
			if (!Main.worlds.getBoolean("worlds." + e.getBlock().getWorld().getName() + ".explosions")){
		
				Block b = e.getBlock();
				
				if (b.getType() == Material.TNT) {
					if (Main.worlds.get("worlds." + e.getBlock().getWorld().getName() + ".explosions") != null) {
						if (!Main.worlds.getBoolean("worlds." + e.getBlock().getWorld().getName() + ".tnt")){
							e.setCancelled(true);
							// b.setType(Material.AIR);
							e.getPlayer().sendMessage(Lang.get("UlityWorld.OnActivateTNT_disabledWorld"));
							FastParticle.spawnParticle(e.getPlayer().getWorld(), ParticleType.SMOKE_LARGE, b.getLocation(), 50);
							return;
						}
					}
				}
			
				for (BlockFace x : BlockFace.values()) {
					if ((DangerOooooh((b.getRelative(x).getType())) || b.getRelative(x).isBlockPowered()) && (b.getType().equals(Material.TNT)) || ((DangerOooooh(b.getType()) && b.getRelative(x).getType().equals(Material.TNT))) && !x.equals(BlockFace.SELF)) {
						e.setCancelled(true);
						// b.getRelative(x).setType(Material.AIR);
						e.getPlayer().sendMessage(Lang.get("UlityWorld.OnActivateTNT_disabledWorld"));
						FastParticle.spawnParticle(e.getPlayer().getWorld(), ParticleType.SMOKE_LARGE, b.getLocation(), 50);
						return;
					}
				}
			}
		}
	}
	
	
}
