package me.avery246813579.HotPotato.Timers;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import me.avery246813579.HotPotato.HotPotato;
import me.avery246813579.HotPotato.Game.GameManager;

public class LobbyTimer implements Runnable{

	private HotPotato plugin;
	private GameManager gm;
	
	public LobbyTimer(HotPotato plugin, GameManager gm){
		this.plugin = plugin;
		this.gm = gm;
	}

	@Override
	public void run() {
		if(this.gm.isLobby()){
			int lobbyTimer = gm.getLobbyTimer();
			if(this.gm.getLobbyTimer() != -1){
				lobbyTimer--;
				for (Player p : gm.getArenaPlayers()) {
					p.setLevel(lobbyTimer);
					}
				gm.setLobbyTimer(lobbyTimer);
			}if (lobbyTimer == 10) {
				for (Player p : gm.getArenaPlayers()) {
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage(ChatColor.GOLD + "-=-=- -=-=-=- -=-=-=-=- -=-=-=- -=-=-");
					p.sendMessage("");
					p.sendMessage(ChatColor.BLUE + "        Game");
					p.sendMessage("");
					p.sendMessage(ChatColor.GOLD + "-=-=- -=-=-=- -=-=-=-=- -=-=-=- -=-=-");
					p.sendMessage("");
					p.sendMessage("");
					p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
					p.setLevel(lobbyTimer);
					int x = plugin.getConfigHandler().getLobbyTimer() / lobbyTimer;
					
					gm.setLobbyTimer(lobbyTimer);
				}
			}if (lobbyTimer == 9) {
				for (Player p : gm.getArenaPlayers()) {
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage(ChatColor.GOLD + "-=-=- -=-=-=- -=-=-=-=- -=-=-=- -=-=-");
					p.sendMessage("");
					p.sendMessage(ChatColor.BLUE + "        Game of");
					p.sendMessage("");
					p.sendMessage(ChatColor.GOLD + "-=-=- -=-=-=- -=-=-=-=- -=-=-=- -=-=-");
					p.sendMessage("");
					p.sendMessage("");
					p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
					p.setLevel(lobbyTimer);
					int x = plugin.getConfigHandler().getLobbyTimer() / lobbyTimer;
					
					gm.setLobbyTimer(lobbyTimer);
				}
			}if (lobbyTimer == 8) {
				for (Player p : gm.getArenaPlayers()) {
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage(ChatColor.GOLD + "-=-=- -=-=-=- -=-=-=-=- -=-=-=- -=-=-");
					p.sendMessage("");
					p.sendMessage(ChatColor.BLUE + "        Game of Hot Potato");
					p.sendMessage("");
					p.sendMessage(ChatColor.GOLD + "-=-=- -=-=-=- -=-=-=-=- -=-=-=- -=-=-");
					p.sendMessage("");
					p.sendMessage("");
					p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
					p.setLevel(lobbyTimer);
					int x = plugin.getConfigHandler().getLobbyTimer() / lobbyTimer;
					
					gm.setLobbyTimer(lobbyTimer);
				}
			}if (lobbyTimer == 7) {
				for (Player p : gm.getArenaPlayers()) {
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage(ChatColor.GOLD + "-=-=- -=-=-=- -=-=-=-=- -=-=-=- -=-=-");
					p.sendMessage("");
					p.sendMessage(ChatColor.BLUE + "            Starts");
					p.sendMessage("");
					p.sendMessage(ChatColor.GOLD + "-=-=- -=-=-=- -=-=-=-=- -=-=-=- -=-=-");
					p.sendMessage("");
					p.sendMessage("");
					p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
					p.setLevel(lobbyTimer);


					gm.setLobbyTimer(lobbyTimer);
				}
			}if (lobbyTimer == 6) {
				for (Player p : gm.getArenaPlayers()) {
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage(ChatColor.GOLD + "-=-=- -=-=-=- -=-=-=-=- -=-=-=- -=-=-");
					p.sendMessage("");
					p.sendMessage(ChatColor.BLUE + "            Starts in:");
					p.sendMessage("");
					p.sendMessage(ChatColor.GOLD + "-=-=- -=-=-=- -=-=-=-=- -=-=-=- -=-=-");
					p.sendMessage("");
					p.sendMessage("");
					p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
					p.setLevel(lobbyTimer);
					int x = plugin.getConfigHandler().getLobbyTimer() / lobbyTimer;
					
					gm.setLobbyTimer(lobbyTimer);
				}
			}if (lobbyTimer == 5) {
				for (Player p : gm.getArenaPlayers()) {
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage(ChatColor.GOLD + "-=-=- -=-=-=- -=-=-=-=- -=-=-=- -=-=-");
					p.sendMessage("");
					p.sendMessage(ChatColor.BLUE + "            Starts in: 5");
					p.sendMessage("");
					p.sendMessage(ChatColor.GOLD + "-=-=- -=-=-=- -=-=-=-=- -=-=-=- -=-=-");
					p.sendMessage("");
					p.sendMessage("");
					p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
					p.setLevel(lobbyTimer);
					int x = plugin.getConfigHandler().getLobbyTimer() / lobbyTimer;
					
					gm.setLobbyTimer(lobbyTimer);
				}
			}if (lobbyTimer == 4) {
				for (Player p : gm.getArenaPlayers()) {
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage(ChatColor.GOLD + "-=-=- -=-=-=- -=-=-=-=- -=-=-=- -=-=-");
					p.sendMessage("");
					p.sendMessage(ChatColor.BLUE + "            Starts in: 4");
					p.sendMessage("");
					p.sendMessage(ChatColor.GOLD + "-=-=- -=-=-=- -=-=-=-=- -=-=-=- -=-=-");
					p.sendMessage("");
					p.sendMessage("");
					p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
					p.setLevel(lobbyTimer);
					int x = plugin.getConfigHandler().getLobbyTimer() / lobbyTimer;
					
					gm.setLobbyTimer(lobbyTimer);
				}
			}if (lobbyTimer == 3) {
				for (Player p : gm.getArenaPlayers()) {
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage(ChatColor.GOLD + "-=-=- -=-=-=- -=-=-=-=- -=-=-=- -=-=-");
					p.sendMessage("");
					p.sendMessage(ChatColor.BLUE + "            Starts in: 3");
					p.sendMessage("");
					p.sendMessage(ChatColor.GOLD + "-=-=- -=-=-=- -=-=-=-=- -=-=-=- -=-=-");
					p.sendMessage("");
					p.sendMessage("");
					p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
					p.setLevel(lobbyTimer);

					
					gm.setLobbyTimer(lobbyTimer);
				}
			}if (lobbyTimer == 2) {
				for (Player p : gm.getArenaPlayers()) {
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage(ChatColor.GOLD + "-=-=- -=-=-=- -=-=-=-=- -=-=-=- -=-=-");
					p.sendMessage("");
					p.sendMessage(ChatColor.BLUE + "            Starts in: 2");
					p.sendMessage("");
					p.sendMessage(ChatColor.GOLD + "-=-=- -=-=-=- -=-=-=-=- -=-=-=- -=-=-");
					p.sendMessage("");
					p.sendMessage("");
					p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
					p.setLevel(lobbyTimer);
					
					gm.setLobbyTimer(lobbyTimer);
				}
			}if (lobbyTimer == 1) {
				for (Player p : gm.getArenaPlayers()) {
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage(ChatColor.GOLD + "-=-=- -=-=-=- -=-=-=-=- -=-=-=- -=-=-");
					p.sendMessage("");
					p.sendMessage(ChatColor.BLUE + "            Starts in: 1");
					p.sendMessage("");
					p.sendMessage(ChatColor.GOLD + "-=-=- -=-=-=- -=-=-=-=- -=-=-=- -=-=-");
					p.sendMessage("");
					p.sendMessage("");
					p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
					p.setLevel(lobbyTimer);					
					gm.setLobbyTimer(lobbyTimer);
				}
			}else if (lobbyTimer == 0){

				gm.startGame();
				
				for(Player p : gm.getArenaPlayers()){
					p.setLevel(0);
					p.setExp(0);
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 10);
				}				
				
				plugin.getServer().getScheduler().cancelTask(gm.getLobby());
				gm.setLobbyTimer(-1);
				gm.setLobby(false);			
			}
		}
	}
}
