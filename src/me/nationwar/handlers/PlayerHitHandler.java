package me.nationwar.handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerHitHandler implements Listener{
	
	@EventHandler
	public void onHitPlayer(EntityDamageByEntityEvent event) {
		Player attacker = null;
		Player victim = null;
		
		if(event.getDamager() instanceof Player) {
			attacker = (Player)event.getDamager();
		}
		
		if(event.getEntity() instanceof Player) {
			victim = (Player)event.getEntity();
		}
		
		try {
			if(event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
				if(checkSameTeam(attacker, victim)) {
					event.setCancelled(true);
					attacker.sendMessage("°°Àº ÆÀ °ø°Ý" + attacker.getWorld().getName());
				}	
			}
		}
		catch(IOException e) {}
		
		proto_knockBack_player(victim, attacker, 0);

	}
	
	public void proto_knockBack_player(Player vic, Player att, double val) {
		vic.setVelocity(att.getLocation().getDirection().setY(0).normalize().multiply(val));
	}
	
	public boolean checkSameTeam(Player attacker, Player victim) throws IOException{
		String attacker_team = "", victim_team = "";
		
		File at = new File("plugins/NationWar/" + attacker.getName() + ".txt");
		File vi = new File("plugins/NationWar/" + victim.getName()   + ".txt");
		
		BufferedReader read = new BufferedReader(new FileReader(at));
		String s;
		
		while((s = read.readLine()) != null) {
			String[] cutter = s.split(":");
			
			if(cutter[0].equals("Nation")) {
				attacker_team = cutter[1];
				break;
			}
		} 
		
		read = new BufferedReader(new FileReader(vi));
		
		while((s = read.readLine()) != null) {
			String[] cutter = s.split(":");
			
			if(cutter[0].equals("Nation")) {
				victim_team = cutter[1];
				break;
			}
		}
		
		if(attacker_team.equals(victim_team)) {
			return true;
		}
		
		return false;
	}
}
