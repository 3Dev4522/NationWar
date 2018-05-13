package me.nationwar.handlers;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import me.nationwar.PluginMain;
import me.nationwar.config.ConfigManager;

public class PlayerJoinHandler implements Listener{

	private ConfigManager configMG = ConfigManager.getInstance();
	private BukkitTask task;
	public int time = 0;
	
	@EventHandler
	public void onJoinPlayer(PlayerJoinEvent event) {
		
		try {
			if(!(configMG.createPlayerConfig(event.getPlayer()))) {	//플레이어가 입장하면 플레이어의 컨피그를 만들어줌
				return;
			}
		} 
		catch (IOException e) {}
	}
	
	@EventHandler
	public void onQuitPlayer(PlayerQuitEvent event) throws IOException {
		
	}
	
	@EventHandler
	public void onMoveEvent(PlayerMoveEvent event) {
		if(event.getPlayer().getLocation().getY() >= 110 &&
				event.getPlayer().getWorld().getName().equals("green_w")) {
			
			Vector vector = new Vector(0, 100, 0);
			
			event.getPlayer().teleport(PluginMain.testLoc);
			launch(event.getPlayer(), vector);
		}
	}
	
	@EventHandler
	public void launchReady(PlayerToggleSneakEvent event) {
		if(time >= 5) {
			Vector loc = new Vector(0, 100, 0);
			
			launch(event.getPlayer(), loc);
			event.getPlayer().sendMessage("launch!");
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 10, 1);
			
			task.cancel();
			time = 0;
			return;
		}
		
		if(task != null && !(event.isSneaking())) {	// 웅크리지 않고 있으면
			task.cancel();
			time = 0;
		}
		
		if(event.isSneaking() && !(event.getPlayer().isFlying())) {
			task = Bukkit.getScheduler().runTaskTimer(PluginMain.plugin, new Runnable() {
				
				public void run() {
					time++;
					
					event.getPlayer().playEffect(event.getPlayer().getLocation(), Effect.CLOUD, 20);
					event.getPlayer().sendMessage(time + "!");
					
					if(time >= 5) {
						event.getPlayer().playEffect(event.getPlayer().getLocation(), Effect.MOBSPAWNER_FLAMES, 0);
					}
				}
			}, 0L, 20L);
		}
	}
	
	public void launch(Player player, Vector vector) {
		player.setVelocity(vector);
	}
}
