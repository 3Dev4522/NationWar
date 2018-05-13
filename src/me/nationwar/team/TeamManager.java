package me.nationwar.team;

import org.bukkit.entity.Player;

public class TeamManager {
	
	private static TeamManager instance = new TeamManager();
	
	private TeamManager() {}
	
	public static TeamManager getInstace() {
		return instance;
	}
	
	public boolean addPlayer(Player player) {
		return true;
	}
	
	public boolean delPlayer(Player player) {
		return true;
	}
}
