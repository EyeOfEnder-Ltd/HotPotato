package me.avery246813579.HotPotato.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import me.avery246813579.HotPotato.HotPotato;
import me.avery246813579.HotPotato.Arena.Arena;
import me.avery246813579.HotPotato.Timers.BombTimer;
import me.avery246813579.HotPotato.Timers.EndTimer;
import me.avery246813579.HotPotato.Timers.LobbyTimer;
import me.avery246813579.HotPotato.Timers.WaitTimer;

public class GameManager {

	/** Class **/
	private Arena arena;
	private HotPotato plugin;
	
	/** Game Info **/
	private boolean inLobby = true;
	
	/** Player Lists **/
	private List<Player> arenaPlayers = new ArrayList<Player>();
	private List<Player> specPlayers = new ArrayList<Player>();
	private List<Player> alivePlayers = new ArrayList<Player>();
	
	/** Players **/
	private Player potatoCarrier = null;
	private Player lastPotatoCarrier = null;
	
	/** Timers **/
	private int lobby;
	private int lobbyTimer;
	private boolean isLobby;
	
	private int wait;
	private int waitTimer;
	private boolean isWait;
	
	private int bomb;
	private int bombTimer;
	private boolean isBomb;
	
	private int end;
	private int endTimer;
	private boolean isEnd;
	
	/** Constructor **/
	
	public GameManager (HotPotato plugin, Arena arena){
		this.plugin = plugin;
		this.arena = arena;
		
		this.setLobbyTimer(plugin.getConfigHandler().getLobbyTimer());
		this.setWaitTimer(plugin.getConfigHandler().getWaitTimer());
		this.setBombTimer(plugin.getConfigHandler().getBombTimer());
		this.setEndTimer(plugin.getConfigHandler().getEndTimer());
	}
	
	public void joinArena(Player player){
		if(!arena.isDisabled()){
			if(arena.getArenaSpawn() != null && arena.getEndSpawn() != null && arena.getLobbySpawn() != null && arena.getSpecSpawn() != null){
				if(inLobby){
					if(!plugin.getInArena().contains(player)){
						if(plugin.getConfigHandler().getMaxPlayers() >= arenaPlayers.size()){
							if(!plugin.getConfigHandler().isClearInventoryOnJoin()){
								if(checkInventory(player)){
									addPlayer(player);
								}else{
									plugin.sendMessage(player, "You need to clear your inventory and armor before joining.");
								}
							}else{
								player.getInventory().clear();
								player.getInventory().setHelmet(null);
								player.getInventory().setChestplate(null);
								player.getInventory().setLeggings(null);
								player.getInventory().setBoots(null);
								addPlayer(player);
							}
						}else{
							plugin.sendMessage(player, "Arena is full. Try again later.");
						}
					}else{
						plugin.sendMessage(player, "You are already in a arena. To leave Do /HP Leave.");
					}
				}else{
					plugin.sendMessage(player, "Game in progress! Try again later!");
				}
			}else{
				
				if(arena.getArenaSpawn() != null){
					plugin.sendMessage(player, "Spawn");
				}
				
				if(arena.getEndSpawn() != null){
					plugin.sendMessage(player, "End");
				}
				
				if(arena.getLobbySpawn() != null){
					plugin.sendMessage(player, "Lobby");
				}
				
				if(arena.getSpecSpawn() != null){
					plugin.sendMessage(player, "Spec");
				}
			
				plugin.sendMessage(player, "Not all Positions are set! Contact admin to check the arena config file!");
			
				
			}
		}else{
			plugin.sendMessage(player, "Arena is disabled. Contact a admin!");
		}
	}
	
	public void joinQuere(){
		
	}
	
	public boolean checkInventory(Player player){
		boolean isEmpty = true;
		
		for (ItemStack itemStack : player.getInventory().getContents()) {
			if(itemStack != null){
				if(player.getInventory().getChestplate() != null || player.getInventory().getHelmet() != null || player.getInventory().getLeggings() != null || player.getInventory().getBoots() != null){
					isEmpty = false;
				}
			}
		}
		
		if(isEmpty == true){
			return true;
		}else{
			return false;
		}
	}
	
	public void leaveArena(Player player){
		player.setAllowFlight(true);
		player.setFlySpeed(0.1F);
		player.setFlying(true);
		
		this.clearPotionEffects(player);
	}
	
	public void kickAll(){
		
		stopArena();
	}
	
	public void addPlayer(Player player){	
		
		/** Add player to arraylists **/
		arenaPlayers.add(player);
		alivePlayers.add(player);
		plugin.getInArena().add(player);
		
		/** Resets Everything **/
		player.setGameMode(GameMode.ADVENTURE);
		player.setHealth(20.0);
		player.setFoodLevel(20);
		player.setFireTicks(0);
		player.setExp(0);
		player.setLevel(0);
		this.clearPotionEffects(player);
		
		/** Changes players gamemode **/
		//Checks if you can fly in spawn.
		if(plugin.getConfigHandler().isCanFlyInLobby()){
			player.setAllowFlight(true);
			player.setFlySpeed(0.1F);
			player.setFlying(true);
		}else{
			player.setAllowFlight(false);
			player.setFlySpeed(0.1F);
			player.setFlying(false);	
		}	
		
		/** Teleports player to Lobby **/
		this.teleport(player, arena.getLobbySpawn());
		
		/** Players a sound **/
		player.playSound(player.getLocation(), Sound.LEVEL_UP , 1, 10);
		
		/** Sends Message on Join **/
		for(Player players : this.getArenaPlayers()){
			players.sendMessage(ChatColor.GREEN + "> Join < " + ChatColor.BLUE + player.getName() +  ChatColor.GRAY + " has joined the arena. (" + this.getArenaPlayers().size() + "/" + plugin.getConfigHandler().getMaxPlayers() + ")");
		}
		
		/** Checks if timer can start **/
		this.checkLobbyStatus();
	}
	
	public void checkLobbyStatus(){
		if(!this.isLobby){
			if(this.getArenaPlayers().size() >= plugin.getConfigHandler().getMinPlayersToStart()){
				if(!this.isLobby){
					startLobbyTimer();
				}
			}
		}
	}
	
	public void startLobbyTimer(){
		this.setLobby(true);
		this.lobby = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new LobbyTimer(plugin, this), 20L, 20L);
		this.tellArena("Lobby Timer has started.");
	}
	
	public void startGame(){
		if(!arena.isDisabled()){
			if(!this.arenaPlayers.isEmpty()){
				if(this.arenaPlayers.size() != 1){
					
					for(Player player : this.getArenaPlayers()){
						/** Teleports player **/
						player.teleport(arena.getArenaSpawn());
						
						/** Adds player to alive count **/
						this.alivePlayers.add(player);
						
						/** Changes game to out of lobby **/
						this.inLobby = false;
						
						/** Sends a message **/
						player.sendMessage(ChatColor.GOLD + "-=-=- -=-=-=- -=-=-=-=- -=-=-=- -=-=-");
						player.sendMessage("");
						player.sendMessage(ChatColor.BLUE + "       Welcome to Hot Potato");
						player.sendMessage(ChatColor.BLUE + "You have " + plugin.getConfigHandler().getWaitTimer() + " seconds to spread out.");
						player.sendMessage(ChatColor.BLUE + "        Use it wisly. Good Luck!");
						player.sendMessage("");
						player.sendMessage(ChatColor.GOLD + "-=-=- -=-=-=- -=-=-=-=- -=-=-=- -=-=-");
						
						/** Starts wait timer **/
						startWaitTimer();
					}
					
				}else{
					notEnoughPlayers();
				}
			}else{
				this.stopArena();
			}
		}else{
			this.kickAll();
		}
	}
	
	public void notEnoughPlayers(){
		this.tellArena("Not enough players to start game. Please join back later.");
		this.kickAll();
	}
	
	public void startWaitTimer(){
		if(!this.inLobby){
			if(!this.alivePlayers.isEmpty()){
				this.isWait = true;
				this.wait = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new WaitTimer(plugin, this), 20L, 20L);
			}else{
				this.tellArena("No one is alive! Game reset.");
				this.kickAll();
			}
		}else{
			this.tellArena("Error in game. Please check console.");
			kickAll();
		}
	}
	
	public void firstStart(){
		if(this.getPotatoCarrier() == null){
			this.tellArena("Game has been started. Good luck!");
			
			for(Player player : this.getArenaPlayers()){
				/** Adds potion effect **/
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000000, 3));
				
				/** Plays a sound **/
				player.playSound(player.getLocation(), Sound.ANVIL_LAND, 10, 10);
				
				/** Resets Exp **/
				player.setLevel(0);
				player.setExp(0);
				
				/** Enabled bomb timer **/
				this.setBomb(true);
			}
			
			randomPick();
		}else{
			return;
		}
	}
	
	public void randomPick(){
		if(this.getPotatoCarrier() == null){
			if(this.getAlivePlayers().size() > 1){
				
				/** Picks random player **/
				Random generator = new Random();
				int randoms = generator.nextInt(this.getAlivePlayers().size()) + 0;
				Player player = this.getArenaPlayers().get(randoms);
				
				this.tellArena(ChatColor.BLUE + player.getName() + ChatColor.GRAY + " has recived the potato.");
				
				potatoRecieved(player);
				
				/** Resets the time **/
				this.setBombTimer(plugin.getConfigHandler().getBombTimer());
				
			}else{
				this.gameWon();
				this.potatoRecieved(this.getAlivePlayers().get(0));
			}
		}else{
			return;
		}
	}
	
	public void potatoRecieved(Player player){
		if(this.alivePlayers.contains(player)){
			/** Adds pototo player **/
			this.setPotatoCarrier(player);
			
			for(Player players : this.getAlivePlayers()){
				players.playSound(players.getLocation(), Sound.COW_HURT, 10, 10);
			}
			
			/** Adds the potato to the players inventory **/
			plugin.getGameItems().addPotato(player);
			
			this.bomb = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new BombTimer(plugin, this), 20L, 20L);
			
			if(this.getLastPotatoCarrier() != null){
				this.tellArena(ChatColor.BLUE + this.getLastPotatoCarrier().getName() + ChatColor.GRAY + " has given " + ChatColor.RED + player.getName() + ChatColor.GRAY + " the potato.");
			}
			
		}else{
			return;
		}
	}
	
	public void removePlayer(Player player){
		if(this.alivePlayers.contains(player)){
			/** Clears players inventory **/
			plugin.getGameItems().removePotato(player);
			
			/** Remove from the alive list **/
			this.getAlivePlayers().remove(player);
			
			/** Clears a explosion **/
			if(plugin.getConfigHandler().isExplosionWhenPlayersRemoved()){
				player.getWorld().createExplosion(player.getLocation(), 0F);
			}
			
			/** Shoot the player in the air **/
			if(plugin.getConfigHandler().isShootPlayerWhenRemoved()){
				player.setVelocity(new Vector(0, 5, 0));
			}
			
			/** Tells arena the player has been removed **/
			this.tellArena(ChatColor.BLUE + player.getName() + ChatColor.GRAY + " has blown up!");

			/** Plays a sound **/
			for(Player players : this.getArenaPlayers()){
				player.playSound(players.getLocation(), Sound.ENDERDRAGON_DEATH, 10L, 10L);
			}
			
			/** Adds to spectater **/
			this.addSpectator(player);
			
			/** Rechooses player **/
			this.randomPick();
		}else{
			return;
		}
	}
	
	public void addSpectator(Player player){
		this.specPlayers.add(player);
		player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1000000, 5));
		plugin.sendMessage(player, "You are now a spectater.");
		player.setAllowFlight(true);
		player.setFlySpeed(0.1F);
		player.setFlying(true);
	}
	
	public void gameWon(){
		this.tellArena(ChatColor.BLUE + " has won the game!");
		
		this.startEndTimer();
	}
	
	public void startEndTimer(){
		this.setEnd(true);
		this.end = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new EndTimer(plugin, this), 20L, 20L);
	}
	
	public void gameOver(){
		this.stopArena();
	}
	
	public void stopArena(){
		
	}
	
	/***********************************************************************************
	 * 
	 *                               	Helper Methods  
	 * 
	 **********************************************************************************/
	
	public void clearPotionEffects(Player player)
	{
		for (PotionEffect effect : player.getActivePotionEffects())
		{
			player.removePotionEffect(effect.getType());
		}
	}
	
	public void teleport(Player p, Location loc) {
		p.teleport(loc.clone().add(0.5D, 1.0D, 0.5D));
	}
	
	public void tellArena(String message){
		for(Player player : this.getArenaPlayers()){
			 plugin.sendMessage(player, message);
		}
	}
	
	/***********************************************************************************
	 * 
	 *                               	Getters And Setters  
	 * 
	 **********************************************************************************/

	public Arena getArena() {
		return arena;
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}

	public List<Player> getArenaPlayers() {
		return arenaPlayers;
	}

	public void setArenaPlayers(List<Player> arenaPlayers) {
		this.arenaPlayers = arenaPlayers;
	}

	public List<Player> getSpecPlayers() {
		return specPlayers;
	}

	public void setSpecPlayers(List<Player> specPlayers) {
		this.specPlayers = specPlayers;
	}

	public List<Player> getAlivePlayers() {
		return alivePlayers;
	}

	public void setAlivePlayers(List<Player> alivePlayers) {
		this.alivePlayers = alivePlayers;
	}

	public int getLobby() {
		return lobby;
	}

	public void setLobby(int lobby) {
		this.lobby = lobby;
	}

	public int getLobbyTimer() {
		return lobbyTimer;
	}

	public void setLobbyTimer(int lobbyTimer) {
		this.lobbyTimer = lobbyTimer;
	}

	public int getBomb() {
		return bomb;
	}

	public void setBomb(int bomb) {
		this.bomb = bomb;
	}

	public int getBombTimer() {
		return bombTimer;
	}

	public void setBombTimer(int bombTimer) {
		this.bombTimer = bombTimer;
	}

	public boolean isBomb() {
		return isBomb;
	}

	public void setBomb(boolean isBomb) {
		this.isBomb = isBomb;
	}

	public Player getPotatoCarrier() {
		return potatoCarrier;
	}

	public void setPotatoCarrier(Player potatoCarrier) {
		this.potatoCarrier = potatoCarrier;
	}

	public Player getLastPotatoCarrier() {
		return lastPotatoCarrier;
	}

	public void setLastPotatoCarrier(Player lastPotatoCarrier) {
		this.lastPotatoCarrier = lastPotatoCarrier;
	}

	public boolean isLobby() {
		return isLobby;
	}

	public void setLobby(boolean isLobby) {
		this.isLobby = isLobby;
	}

	public int getWait() {
		return wait;
	}

	public void setWait(int wait) {
		this.wait = wait;
	}

	public int getWaitTimer() {
		return waitTimer;
	}

	public void setWaitTimer(int waitTimer) {
		this.waitTimer = waitTimer;
	}

	public boolean isWait() {
		return isWait;
	}

	public void setWait(boolean isWait) {
		this.isWait = isWait;
	}

	public boolean isInLobby() {
		return inLobby;
	}

	public void setInLobby(boolean inLobby) {
		this.inLobby = inLobby;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getEndTimer() {
		return endTimer;
	}

	public void setEndTimer(int endTimer) {
		this.endTimer = endTimer;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}
}
