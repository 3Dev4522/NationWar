package me.nationwar.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.Location;

public class OutputManager {
	
	private static OutputManager instance = new OutputManager();
	private File root = new File("plugins/NationWar/out");
	
	private OutputManager() {}
	
	public static OutputManager getInstance() {
		return instance;
	}
	
	
	//우주로 워프할때 로케이션을 지정하는 메소드
	public void createOutputConfig(Location loc, String name) throws IOException{
		File config = new File("plugins/NationWar/out/" + name + ".txt");
		
		if(!(root.exists())) {
			root.mkdir();
		}
		
		BufferedWriter write = new BufferedWriter(new FileWriter(config));
		
		write.append("World:" + loc.getWorld().getName() + "\r\n" + "X:" + loc.getX() + "\r\n" + "Y:" + loc.getY() + "\r\n" + "Z:" + loc.getZ());
		write.flush();
		write.close();
	}
	
	public void deleteOutputConfig(String name) {
		
	}
}
