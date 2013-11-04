package me.avery246813579.HotPotato.Handlers;

import me.avery246813579.HotPotato.HotPotato;

public class ConfigurationHandler {

	private HotPotato plugin;
	
	/** Players **/
	private int maxPlayers = 16;
	private int minPlayersToStart  = 8;
	
	/** Game Options **/
	private boolean clearInventoryOnJoin = false; 
	private boolean canFlyInLobby = false;
	
	private boolean shootPlayerWhenRemoved = true;
	private boolean explosionWhenPlayersRemoved = true;
	private boolean fireworks = true;

	
	/** Developer Options **/
	private boolean debug = false;
	
	/** Timers **/
	private int lobbyTimer = 60;
	private int waitTimer = 10;
	private int bombTimer = 20;
	private int endTimer = 10;
	
	public ConfigurationHandler(HotPotato plugin){
		this.plugin = plugin;
	}
	
	public void loadConfig(){
		if(plugin.getConfig().contains("lobbyTimer")){
			this.setLobbyTimer(plugin.getConfig().getInt("lobbyTimer"));
		}
		if(plugin.getConfig().contains("waitTimer")){
			this.setWaitTimer(plugin.getConfig().getInt("waitTimer"));
		}
		if(plugin.getConfig().contains("bombTimer")){
			this.setBombTimer(plugin.getConfig().getInt("bombTimer"));
		}
		if(plugin.getConfig().contains("endTimer")){
			this.setEndTimer(plugin.getConfig().getInt("endTimer"));
		}
		if(plugin.getConfig().contains("maxPlayers")){
			this.setMaxPlayers(plugin.getConfig().getInt("maxPlayers"));
		}
		if(plugin.getConfig().contains("minPlayersToStart")){
			this.setMinPlayersToStart(plugin.getConfig().getInt("minPlayersToStart"));
		}
		if(plugin.getConfig().contains("clearInventoryOnJoin")){
			this.setClearInventoryOnJoin(plugin.getConfig().getBoolean("clearInventoryOnJoin"));
		}
		if(plugin.getConfig().contains("canFlyInLobby")){
			this.setCanFlyInLobby(plugin.getConfig().getBoolean("canFlyInLobby"));
		}
		if(plugin.getConfig().contains("debug")){
			this.setDebug(plugin.getConfig().getBoolean("debug"));
		}
		if(plugin.getConfig().contains("shootPlayerWhenRemoved")){
			this.setShootPlayerWhenRemoved(plugin.getConfig().getBoolean("shootPlayerWhenRemoved"));
		}
		if(plugin.getConfig().contains("explosionWhenPlayersRemoved")){
			this.setExplosionWhenPlayersRemoved(plugin.getConfig().getBoolean("explosionWhenPlayersRemoved"));
		}
		if(plugin.getConfig().contains("fireworks")){
			this.setFireworks(plugin.getConfig().getBoolean("fireworks"));
		}
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public int getMinPlayersToStart() {
		return minPlayersToStart;
	}

	public void setMinPlayersToStart(int minPlayersToStart) {
		this.minPlayersToStart = minPlayersToStart;
	}

	public boolean isClearInventoryOnJoin() {
		return clearInventoryOnJoin;
	}

	public void setClearInventoryOnJoin(boolean clearInventoryOnJoin) {
		this.clearInventoryOnJoin = clearInventoryOnJoin;
	}

	public boolean isCanFlyInLobby() {
		return canFlyInLobby;
	}

	public void setCanFlyInLobby(boolean canFlyInLobby) {
		this.canFlyInLobby = canFlyInLobby;
	}

	public int getLobbyTimer() {
		return lobbyTimer;
	}

	public void setLobbyTimer(int lobbyTimer) {
		this.lobbyTimer = lobbyTimer;
	}

	public int getWaitTimer() {
		return waitTimer;
	}

	public void setWaitTimer(int waitTimer) {
		this.waitTimer = waitTimer;
	}

	public int getEndTimer() {
		return endTimer;
	}

	public void setEndTimer(int endTimer) {
		this.endTimer = endTimer;
	}

	public int getBombTimer() {
		return bombTimer;
	}

	public void setBombTimer(int bombTimer) {
		this.bombTimer = bombTimer;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public boolean isShootPlayerWhenRemoved() {
		return shootPlayerWhenRemoved;
	}

	public void setShootPlayerWhenRemoved(boolean shootPlayerWhenRemoved) {
		this.shootPlayerWhenRemoved = shootPlayerWhenRemoved;
	}

	public boolean isExplosionWhenPlayersRemoved() {
		return explosionWhenPlayersRemoved;
	}

	public void setExplosionWhenPlayersRemoved(boolean explosionWhenPlayersRemoved) {
		this.explosionWhenPlayersRemoved = explosionWhenPlayersRemoved;
	}

	public boolean isFireworks() {
		return fireworks;
	}

	public void setFireworks(boolean fireworks) {
		this.fireworks = fireworks;
	}
}
