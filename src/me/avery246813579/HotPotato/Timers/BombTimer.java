package me.avery246813579.HotPotato.Timers;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import me.avery246813579.HotPotato.HotPotato;
import me.avery246813579.HotPotato.Game.GameManager;

public class BombTimer implements Runnable{

	private HotPotato plugin;
	private GameManager gm;
	
	public BombTimer(HotPotato plugin, GameManager gm){
		this.plugin = plugin;
		this.gm = gm;
	}

	public void run() {
		if(gm.isBomb()){
			int timer = gm.getBombTimer();
			
			if(timer != 0){
				timer--;
				gm.setLobbyTimer(timer);
				
				for (Player p : gm.getArenaPlayers()) {
					p.setLevel(timer);
				}
			}else{
				gm.removePlayer(gm.getPotatoCarrier());
				
				for(Player p : gm.getArenaPlayers()){
					p.setLevel(0);
					p.setExp(0);
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 10);
				}				
				
				plugin.getServer().getScheduler().cancelTask(gm.getBomb());
				gm.setBombTimer(-1);
			}
		}
	}
}