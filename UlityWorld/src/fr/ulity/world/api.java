package fr.ulity.world;

import java.io.InputStream;

import javax.annotation.Nullable;

import org.bukkit.plugin.Plugin;

import fr.ulity.core.bukkit.Api;
import fr.ulity.core.bukkit.CommandManager;


public class api {
	static Plugin _plugin;
	public static Api apiClass;
	public static fr.ulity.core.bukkit.Lang lang;
	public static fr.ulity.core.utils.IndexUtils utils;
	public static fr.ulity.core.bukkit.animations.IndexAnimations animations;
	public static fr.ulity.core.bukkit.utils.Permissions permissions;
	public static fr.ulity.core.bukkit.Temp temp;
	public static fr.ulity.core.bukkit.Config config;
	public static fr.ulity.core.utils.Text text;


	static {
		System.out.println("§eLoading API ... §8(EN) §e/ Chargement de l'API §8(FR)");
	}
	
	@SuppressWarnings("static-access")
	static void initialize (Plugin plugin) {
		if (!plugin.getServer().getPluginManager().isPluginEnabled("UlityCore")) {
			plugin.getPluginLoader().disablePlugin(plugin);
			System.out.println("§c---> UlityCore is missing §8(EN) §c!!\n§c---> UlityCore est absent §8(FR) §e!!");
		}
		else {
			_plugin = plugin;
			apiClass = fr.ulity.core.bukkit.MainBukkit.api;
			lang = new fr.ulity.core.bukkit.Lang();
			utils = new fr.ulity.core.utils.IndexUtils();
			animations = new fr.ulity.core.bukkit.animations.IndexAnimations();
			permissions = new fr.ulity.core.bukkit.utils.Permissions();
			temp = new fr.ulity.core.bukkit.Temp();
			text = new fr.ulity.core.utils.Text();
			
			InputStream langToImport = null;
			
			try {
				langToImport = api.class.getResourceAsStream("language.yml");
			}
			catch (NullPointerException e) {
				e.printStackTrace();
			}
			
			if (langToImport != null) 
				lang.importFromFile(langToImport);
			
		}
	}
	
	static boolean isInitialized () {
		if (_plugin != null)
			return true;
		else {
			System.out.println("§c---> Oooh, error: API not initialized §8(EN) §c!!\n§c---> Oooh, erreur: l'API n'a pas été initializée §8(FR)");
			return false;
		}
	}
	
	
	public static fr.ulity.core.bukkit.Config config (@Nullable String name) {
		if (!isInitialized()) 
			return null;
		if (name == null) 
			return new fr.ulity.core.bukkit.Config();
		else 
			return new fr.ulity.core.bukkit.Config(name);
	}

	public static CommandManager commandManager (String CommandName) {
		if (!isInitialized()) 
			return null;
		return new fr.ulity.core.bukkit.CommandManager(_plugin.getName(), CommandName);
	}
	
	public static CommandManager commandManager () {
		if (!isInitialized()) 
			return null;
		return new fr.ulity.core.bukkit.CommandManager(_plugin.getName());
	}

}
 