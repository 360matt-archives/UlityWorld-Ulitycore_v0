package fr.ulity.world;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.ulity.core.bukkit.Config;
import fr.ulity.world.commands.*;
import fr.ulity.world.events.*;





public class Main extends JavaPlugin {
	
	public static Main plugin;
	public static Server server;
	public static Config worlds;

	
	
	
	@Override
	public void onEnable() {
    	
    	plugin = this;
    	server = this.getServer();

    	api.initialize(plugin);
    	
    	if (api.isInitialized()) {
    		
        	worlds = api.config("worlds"); // worlds.yml 
        	worlds.reload();
        	
        	new BukkitRunnable() {
				@Override
				public void run() {
					ConfWorld.GenerateOrReload();
					
				}
        	}.runTaskTimerAsynchronously(this, 0L, 20*60*10L);
        	
        	getCommand("AntiGrief").setExecutor(new AntiGriefCommandExecutor());
        	getCommand("world").setExecutor(new WorldCommandExecutor());
        	
        	getServer().getPluginManager().registerEvents(new AntiGriefEvent(), this);
        	getServer().getPluginManager().registerEvents(new ExplosionsEvent(), this);
        	getServer().getPluginManager().registerEvents(new DamageEvent(), this);

        	
        	
    	}
    	

    	
	}
	
    @Override
    public void onDisable(){
    	
    }
	
}
