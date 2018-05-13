package me.nationwar.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ConfigManager {
	
	private static ConfigManager instance = new ConfigManager();
	
	private ConfigManager() {}
	
	public static ConfigManager getInstance() {
		return instance;
	}
	
	/*
	public boolean readPlayerConfig(Player player) throws IOException {
		File playerConfig = new File("plugins/NationWar/" + player.getName() + ".txt");
		
		if(!playerConfig.exists()) {	// 플레이어의 데이터가 존재하지 않으면
			return false;
		}
	
		BufferedReader reader = new BufferedReader(new Reader(playerConfig));
		
		return true;
	}
	*/
	
	public void createSpawnConfig(Location loc, String team) throws IOException{
		File root = new File("plugins/NationWar/setting");
		File setting = new File("plugins/NationWar/setting/" + team + ".txt");
		
		if(!root.exists()) { // setting 폴더가 없으면
			root.mkdir();
		}
		
		BufferedWriter write = new BufferedWriter(new FileWriter(setting));
		
		write.append("World:" + loc.getWorld().toString() + "\r\n" 
				   + "X:" + loc.getX() + "\r\n"
				   + "Y:" + loc.getY() + "\r\n"
				   + "Z:" + loc.getZ());
		write.flush();
		write.close();
	}
	
	public boolean createPlayerConfig(Player player) throws IOException {
		File root = new File("plugins/NationWar");
		File playerConfig = new File("plugins/NationWar/" + player.getName() + ".txt");
		
		if(!root.exists()) {
			root.mkdir();
		}
		
		if(!playerConfig.exists()) {	// 플레이어의 데이터가 존재하지 않으면
			
			playerConfig.createNewFile();
			
			BufferedWriter write = new BufferedWriter(new FileWriter(playerConfig));
			
			write.append("UUID:" + player.getUniqueId().toString() + "\r\n" + "Nation:0" + "\r\n" + "job:0" + "\r\n");
			write.flush();
			write.close();
			
			return true;
		}
		
		return false;
	}
	
	public boolean deletePlayerConfig(Player player) {
		File playerConfig = new File("plugins/NationWar/" + player.getName() + ".txt");
		
		if(playerConfig.exists()) {	// 플레이어의 데이터가 존재하면
			playerConfig.delete();
			return true;
		}
		return false;
	}
	
	/*
	public String[] separation(String line) {
		
	}
	*/
}
