package me.nationwar;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.nationwar.config.ConfigManager;
import me.nationwar.config.OutputManager;
import me.nationwar.handlers.PlayerHitHandler;
import me.nationwar.handlers.PlayerJoinHandler;

public class PluginMain extends JavaPlugin{
	
	public static PluginMain plugin;

	public static Location testLoc;
	
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new PlayerJoinHandler(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerHitHandler(), this);
		
		plugin = this;
	}
	
	public void onDisable() {
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player)sender;
		ConfigManager config = ConfigManager.getInstance();
		
		if(label.equalsIgnoreCase("set")) {
			try {
				if(args[0].equalsIgnoreCase("green")) {
					player.sendMessage("setting");
					config.createSpawnConfig(player.getLocation(), "green");
					return true;
				}
				
				if(args[0].equalsIgnoreCase("red")) {
					player.sendMessage("setting");
					config.createSpawnConfig(player.getLocation(), "red");
					return true;
				}
				
				if(args[0].equalsIgnoreCase("blue")) {
					player.sendMessage("setting");
					config.createSpawnConfig(player.getLocation(), "blue");
					return true;
				}
				
				OutputManager outputMgr = OutputManager.getInstance();
				
				if(args[0].equalsIgnoreCase("out")) {
					if(args[1].equalsIgnoreCase("green")) {
						outputMgr.createOutputConfig(player.getLocation(), "green");
						return true;
					}
					
					if(args[1].equalsIgnoreCase("red")) {
						outputMgr.createOutputConfig(player.getLocation(), "red");
						return true;
					}
					
					if(args[1].equalsIgnoreCase("blue")) {
						outputMgr.createOutputConfig(player.getLocation(), "blue");
						return true;
					}
					
					player.sendMessage("set out green/red/blue");
				}
			}
			
			catch(IOException e) {}
			player.sendMessage("select greed/red/blue");
		}
		
		if(label.equalsIgnoreCase("read")) {

		}
		
		return false;
	}
}
