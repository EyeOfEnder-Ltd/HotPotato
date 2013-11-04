package me.avery246813579.HotPotato.Arena;

import org.bukkit.Location;
import org.bukkit.World;

import me.avery246813579.HotPotato.HotPotato;

public class Arena {
	
	private HotPotato plugin;
	
	private boolean disabled = false;
	
	private String arenaName = null;
	private Location lobbySpawn = null;
	private Location arenaSpawn = null;
	private Location specSpawn = null;
	private Location endSpawn = null;
	private Location Location1 = null;
	private Location Location2 = null;
	
	public Arena ( HotPotato plugin, String arenaName){
		this.plugin = plugin;
		this.arenaName = arenaName;
		
		loadArena(arenaName);
	}
	
	public void loadArena(String ArenaUppercase){
		String Arena = ArenaUppercase.toLowerCase();

		/******************************************************************************************
		 * 
		 * 									Loading Lobby
		 * 
		 ******************************************************************************************/
		World lobbyWorld = plugin.getServer().getWorld(plugin.getFc().getArena().getConfigurationSection(Arena).getString("lobby.World"));
		
		if(lobbyWorld != null){

			String y =  plugin.getFc().getArena().getConfigurationSection(Arena).getString("lobby.yaw");
			String p = plugin.getFc().getArena().getConfigurationSection(Arena).getString("lobby.pitch");
			
			float yaw = (float)Float.parseFloat(y);
			float pitch = (float)Float.parseFloat(p);
			
			lobbySpawn = new Location(lobbyWorld, plugin.getFc().getArena().getConfigurationSection(Arena).getInt("lobby.x"), plugin.getFc().getArena().getConfigurationSection(Arena).getInt("lobby.y"), plugin.getFc().getArena().getConfigurationSection(Arena).getInt("lobby.z"), yaw, pitch);

		}
		
		World spawnWorld = plugin.getServer().getWorld(plugin.getFc().getArena().getConfigurationSection(Arena).getString("spawn.World"));
		
		if(spawnWorld != null){

			String y =  plugin.getFc().getArena().getConfigurationSection(Arena).getString("spawn.yaw");
			String p = plugin.getFc().getArena().getConfigurationSection(Arena).getString("spawn.pitch");
			
			float yaw = (float)Float.parseFloat(y);
			float pitch = (float)Float.parseFloat(p);
			
			arenaSpawn = new Location(spawnWorld, plugin.getFc().getArena().getConfigurationSection(Arena).getInt("spawn.x"), plugin.getFc().getArena().getConfigurationSection(Arena).getInt("spawn.y"), plugin.getFc().getArena().getConfigurationSection(Arena).getInt("spawn.z"), yaw, pitch);

		}
		
		World specWorld = plugin.getServer().getWorld(plugin.getFc().getArena().getConfigurationSection(Arena).getString("spec.World"));
		
		if(specWorld != null){

			String y =  plugin.getFc().getArena().getConfigurationSection(Arena).getString("spec.yaw");
			String p = plugin.getFc().getArena().getConfigurationSection(Arena).getString("spec.pitch");
			
			float yaw = (float)Float.parseFloat(y);
			float pitch = (float)Float.parseFloat(p);
			
			specSpawn = new Location(spawnWorld, plugin.getFc().getArena().getConfigurationSection(Arena).getInt("spec.x"), plugin.getFc().getArena().getConfigurationSection(Arena).getInt("spec.y"), plugin.getFc().getArena().getConfigurationSection(Arena).getInt("spec.z"), yaw, pitch);

		}
		
		World endWorld = plugin.getServer().getWorld(plugin.getFc().getArena().getConfigurationSection(Arena).getString("spec.World"));
		
		if(endWorld != null){

			String y =  plugin.getFc().getArena().getConfigurationSection(Arena).getString("end.yaw");
			String p = plugin.getFc().getArena().getConfigurationSection(Arena).getString("end.pitch");
			
			float yaw = (float)Float.parseFloat(y);
			float pitch = (float)Float.parseFloat(p);
			
			endSpawn = new Location(spawnWorld, plugin.getFc().getArena().getConfigurationSection(Arena).getInt("end.x"), plugin.getFc().getArena().getConfigurationSection(Arena).getInt("end.y"), plugin.getFc().getArena().getConfigurationSection(Arena).getInt("end.z"), yaw, pitch);

		}
		
		//setLocation1(new Location(spawnWorld, plugin.getFc().getArena().getConfigurationSection(Arena).getInt("Location1.x"), plugin.getFc().getArena().getConfigurationSection(Arena).getInt("Location1.y"), plugin.getFc().getArena().getConfigurationSection(Arena).getInt("Location1.z")));
		//setLocation2(new Location(spawnWorld, plugin.getFc().getArena().getConfigurationSection(Arena).getInt("Location1.x"), plugin.getFc().getArena().getConfigurationSection(Arena).getInt("Location1.y"), plugin.getFc().getArena().getConfigurationSection(Arena).getInt("Location1.z")));
	}
	
	
	/*****************************************************************************************
	 * 
	 *                                 /** Getters & Setters **\
	 * 
	 *****************************************************************************************/

	public Location getSpecSpawn() {
		return specSpawn;
	}
	
	public void setSpecSpawn(Location specSpawn) {
		this.specSpawn = specSpawn;
	}
	
	public Location getArenaSpawn() {
		return arenaSpawn;
	}
	
	public void setArenaSpawn(Location arenaSpawn) {
		this.arenaSpawn = arenaSpawn;
	}
	
	public Location getLobbySpawn() {
		return lobbySpawn;
	}
	
	public void setLobbySpawn(Location lobbySpawn) {
		this.lobbySpawn = lobbySpawn;
	}
	
	public String getArenaName() {
		return arenaName;
	}
	
	public void setArenaName(String arenaName) {
		this.arenaName = arenaName;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public Location getEndSpawn() {
		return endSpawn;
	}

	public void setEndSpawn(Location endSpawn) {
		this.endSpawn = endSpawn;
	}

	public Location getLocation1() {
		return Location1;
	}

	public void setLocation1(Location location1) {
		Location1 = location1;
	}

	public Location getLocation2() {
		return Location2;
	}

	public void setLocation2(Location location2) {
		Location2 = location2;
	}
}
