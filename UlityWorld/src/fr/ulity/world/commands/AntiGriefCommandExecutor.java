package fr.ulity.world.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.ulity.core.bukkit.CommandManager;
import fr.ulity.core.bukkit.Lang;
import fr.ulity.core.bukkit.Temp;
import fr.ulity.world.api;


public class AntiGriefCommandExecutor implements CommandExecutor {
	
	static {
		CommandManager c = api.commandManager("AntiGrief");
		c.description("Permet de basculer en mode édition de map");
		c.syntax("player");
		
	}
	
	
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	if (sender instanceof Player == false) {
    		sender.sendMessage(Lang.get("msg.InGameOnly"));
    	}
    	else {
    		Player player = (Player)sender;
    		String name = player.getName();
    		
    		if (Temp.isSet("player." + name + ".AntiGrief")) {
    			Temp.delete("player." + name + ".AntiGrief");
    			player.sendMessage(Lang.get("UlityWorld.AntiGriefCommand").replaceAll("%stat%", "§c" + Lang.get("expressions.disabled")));
    		}
    		else {
    			Temp.set("player." + name + ".AntiGrief", true);
    			player.sendMessage(Lang.get("UlityWorld.AntiGriefCommand").replaceAll("%stat%", "§a" + Lang.get("expressions.enabled")));
    		}

    	}
    	
    	
	return true;
   } 
}