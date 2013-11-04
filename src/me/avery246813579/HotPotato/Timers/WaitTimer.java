package me.avery246813579.HotPotato.Timers;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import me.avery246813579.HotPotato.HotPotato;
import me.avery246813579.HotPotato.Game.GameManager;

public class WaitTimer implements Runnable{

	private HotPotato plugin;
	private GameManager gm;
	
	public WaitTimer(HotPotato plugin, GameManager gm){
		this.plugin = plugin;
		this.gm = gm;
	}

	@Override
	public void run() {
		if(gm.isWait()){
			int timer = gm.getWaitTimer();
			
			if(timer != 0){
				timer--;
				gm.setWaitTimer(timer);
			
				for (Player p : gm.getArenaPlayers()) {
					p.setLevel(timer);
				}
			}else if(timer == 5){
				for (Player p : gm.getArenaPlayers()) {
			
					p.sendMessage(ChatColor.GOLD + "-=- -=-=- -=-=-=- " + ChatColor.BLUE + "Game starts in : 5 " + ChatColor.GOLD + "-=-=-=- -=-=- -=-");
					
					p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
					p.setLevel(timer);					
					gm.setLobbyTimer(timer);
				}
			}else if(timer == 4){
				for (Player p : gm.getArenaPlayers()) {
			
					p.sendMessage(ChatColor.GOLD + "-=- -=-=- -=-=-=- " + ChatColor.BLUE + "Game starts in : 4 " + ChatColor.GOLD + "-=-=-=- -=-=- -=-");
					
					p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
					p.setLevel(timer);					
					gm.setLobbyTimer(timer);
				}
			}else if(timer == 3){
				for (Player p : gm.getArenaPlayers()) {
			
					p.sendMessage(ChatColor.GOLD + "-=- -=-=- -=-=-=- " + ChatColor.BLUE + "Game starts in : 3 " + ChatColor.GOLD + "-=-=-=- -=-=- -=-");
					
					p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
					p.setLevel(timer);					
					gm.setLobbyTimer(timer);
				}
			}else if(timer == 2){
				for (Player p : gm.getArenaPlayers()) {
			
					p.sendMessage(ChatColor.GOLD + "-=- -=-=- -=-=-=- " + ChatColor.BLUE + "Game starts in : 2 " + ChatColor.GOLD + "-=-=-=- -=-=- -=-");
					
					p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
					p.setLevel(timer);					
					gm.setLobbyTimer(timer);
				}
			}else if(timer == 1){
				for (Player p : gm.getArenaPlayers()) {
			
					p.sendMessage(ChatColor.GOLD + "-=- -=-=- -=-=-=- " + ChatColor.BLUE + "Game starts in : 1 " + ChatColor.GOLD + "-=-=-=- -=-=- -=-");
					
					p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
					p.setLevel(timer);					
					gm.setLobbyTimer(timer);
				}
			}else{
				
				gm.firstStart();
				
				for(Player p : gm.getArenaPlayers()){
					p.setLevel(0);
					p.setExp(0);
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 10);
				}	
				
				plugin.getServer().getScheduler().cancelTask(gm.getWait());
				gm.setWaitTimer(-1);
				gm.setWait(false);		
			}
		}
	}
}
