package fr.ulity.world.commands;

import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.ulity.core.bukkit.CommandManager;
import fr.ulity.core.bukkit.Lang;
import fr.ulity.core.utils.Text;
import fr.ulity.world.api;

public class WorldCommandExecutor implements CommandExecutor{
	
	public WorldCommandExecutor() {
		CommandManager WorldCM = api.commandManager("World");
		WorldCM.description("Commande principale de gestion des mondes");
		
		CommandManager argWorldNameCM = api.commandManager("World.argWorldName");
		argWorldNameCM.description("Affiche ou modifie les paramètres d'un monde");
		argWorldNameCM.syntax("world", "[...]");
		
	}

	
	private void showIndex (CommandSender sender, int Page) {
		
		int TotalPages = api.commandManager().getCommandsFromList("World").size()%3;
		
		String msg_index = Text.center("§a☻ - ♥ - ♦ - ♣ - ♪ -  §2UlityWorld  §a- ♪ - ♣ - ♦ - ♥ - ☺\n");
		msg_index = msg_index.concat(Text.center("§3" + Lang.get("expressions.page") + ": §b" + Page + "/" + TotalPages + "\n \n"));
		
		Page--;
		
		int temoin = 0;
		int checked = 0;
				
		
		for (Map<String, String> x: api.commandManager().getCommandsFromList("World")) {
			if (Page*3 <= temoin) {
				if (checked == 3)
					break;
				msg_index = msg_index.concat("§6" + Lang.get("expressions.description") + ": §e" + x.get("description") + "\n\n");
				msg_index = msg_index.concat("§6" + Lang.get("expressions.syntax") + ": §e" + x.get("syntax") + "\n \n");
				checked++;
			}
			
			temoin++;
		}
		
		if (!(checked >0)) 
			msg_index = msg_index.concat("" + Lang.get("msg.nothings_value") + "\n \n");

		msg_index = msg_index.concat(Text.center("§a☻ - ♥ - ♦ - ♣ - ♪ -  §2UlityWorld  §a- ♪ - ♣ - ♦ - ♥ - ☺"));
		
		sender.sendMessage(msg_index);
		
		
		
	}
	
	
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (args.length == 0) {
			
		}
		else {
			
			switch(args[0]) {
				
			
				case "help":
				default:
					if (args.length != 2)
						showIndex(sender, 1);
					else
						try {
							showIndex(sender, Integer.valueOf(args[1]));
						}
						catch(NumberFormatException e) {
							showIndex(sender, 1);
						}
						
						

			
			}
			
			
			
		}
		
		
		
		return true;
	}
	
}
