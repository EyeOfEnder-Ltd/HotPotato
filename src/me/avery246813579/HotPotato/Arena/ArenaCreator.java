package me.avery246813579.HotPotato.Arena;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.avery246813579.HotPotato.HotPotato;

public class ArenaCreator {

	private HotPotato plugin;
	
	public ArenaCreator (HotPotato plugin ){
		this.plugin = plugin;
	}
	
	public void createArena(Player player, String arenaName){
		String arena = arenaName.toLowerCase();
		
		if(!plugin.getFc().getArena().contains(arena)){
			   plugin.getFc().getArena().createSection(arena);
			   plugin.getFc().getArena().getConfigurationSection(arena).set("Disabled", false);
			   plugin.getFc().saveArena();
			   
			   plugin.sendMessage(player, "A new arena has been created named " + arena + ".");
		}else{
			plugin.sendMessage(player, "Arena is already created.");
		}
	}
	
	public void setLobbySpawn(Player player, String arenaName){
		String arena = arenaName.toLowerCase();
		
		if(plugin.getFc().getArena().contains(arena)){
			
			Location l = player.getLocation();
			
			plugin.getFc().getArena().getConfigurationSection(arena).set("lobby.World", l.getWorld().getName());
			plugin.getFc().getArena().getConfigurationSection(arena).set("lobby.x", l.getBlockX());
			plugin.getFc().getArena().getConfigurationSection(arena).set("lobby.y", l.getBlockY());
			plugin.getFc().getArena().getConfigurationSection(arena).set("lobby.z", l.getBlockZ());
			plugin.getFc().getArena().getConfigurationSection(arena).set("lobby.yaw", l.getYaw());
			plugin.getFc().getArena().getConfigurationSection(arena).set("lobby.pitch", l.getPitch());
			
			plugin.getFc().saveArena();
			
			plugin.sendMessage(player, "You have updated the lobby on " + arena + ".");
			
		}else{
			plugin.sendMessage(player, "Arena does not exist. To create a arena Do /HP Create (Name)");
		}
	}
	
	public void setSpawn(Player player, String arenaName){
		String arena = arenaName.toLowerCase();
		
		if(plugin.getFc().getArena().contains(arena)){
			
			Location l = player.getLocation();
			
			plugin.getFc().getArena().getConfigurationSection(arena).set("spawn.World", l.getWorld().getName());
			plugin.getFc().getArena().getConfigurationSection(arena).set("spawn.x", l.getBlockX());
			plugin.getFc().getArena().getConfigurationSection(arena).set("spawn.y", l.getBlockY());
			plugin.getFc().getArena().getConfigurationSection(arena).set("spawn.z", l.getBlockZ());
			plugin.getFc().getArena().getConfigurationSection(arena).set("spawn.yaw", l.getYaw());
			plugin.getFc().getArena().getConfigurationSection(arena).set("spawn.pitch", l.getPitch());
			
			plugin.getFc().saveArena();
			
			plugin.sendMessage(player, "You have updated " + arena + "s Spawn.");
			
		}else{
			plugin.sendMessage(player, "Arena does not exist. To create a arena Do /HP Create (Name)");
		}
	}
	
	public void setSpec(Player player, String arenaName){
		String arena = arenaName.toLowerCase();
		
		if(plugin.getFc().getArena().contains(arena)){
			
			Location l = player.getLocation();
			
			plugin.getFc().getArena().getConfigurationSection(arena).set("spec.World", l.getWorld().getName());
			plugin.getFc().getArena().getConfigurationSection(arena).set("spec.x", l.getBlockX());
			plugin.getFc().getArena().getConfigurationSection(arena).set("spec.y", l.getBlockY());
			plugin.getFc().getArena().getConfigurationSection(arena).set("spec.z", l.getBlockZ());
			plugin.getFc().getArena().getConfigurationSection(arena).set("spec.yaw", l.getYaw());
			plugin.getFc().getArena().getConfigurationSection(arena).set("spec.pitch", l.getPitch());
			
			plugin.getFc().saveArena();
			
			plugin.sendMessage(player, "You have updated " + arena + "s Spectate Spawn.");
			
		}else{
			plugin.sendMessage(player, "Arena does not exist. To create a arena Do /HP Create (Name)");
		}
	}
	
	public void setEnd(Player player, String arenaName){
		String arena = arenaName.toLowerCase();
		
		if(plugin.getFc().getArena().contains(arena)){
			
			Location l = player.getLocation();
			
			plugin.getFc().getArena().getConfigurationSection(arena).set("end.World", l.getWorld().getName());
			plugin.getFc().getArena().getConfigurationSection(arena).set("end.x", l.getBlockX());
			plugin.getFc().getArena().getConfigurationSection(arena).set("end.y", l.getBlockY());
			plugin.getFc().getArena().getConfigurationSection(arena).set("end.z", l.getBlockZ());
			plugin.getFc().getArena().getConfigurationSection(arena).set("end.yaw", l.getYaw());
			plugin.getFc().getArena().getConfigurationSection(arena).set("end.pitch", l.getPitch());
			
			plugin.getFc().saveArena();
			
			plugin.sendMessage(player, "You have updated " + arena + "s End.");
			
		}else{
			plugin.sendMessage(player, "Arena does not exist. To create a arena Do /HP Create (Name)");
		}
	}
	
	public void setArena(Player player, String arenaName){
		String arena = arenaName.toLowerCase();
		
		if(plugin.getFc().getArena().contains(arena)){
			if(plugin.getLocation1() != null){
				if(plugin.getLocation2() != null){
					
					Location l = plugin.getLocation1();
					Location l2 = plugin.getLocation2();
					
					plugin.getFc().getArena().getConfigurationSection(arena).set("Location1.world", l.getWorld().getName());
					plugin.getFc().getArena().getConfigurationSection(arena).set("Location1.x", l.getBlockX());
					plugin.getFc().getArena().getConfigurationSection(arena).set("Location1.y", 0);
					plugin.getFc().getArena().getConfigurationSection(arena).set("Location1.z", l.getBlockZ());
					plugin.getFc().getArena().getConfigurationSection(arena).set("Location1.yaw", l.getYaw());
					plugin.getFc().getArena().getConfigurationSection(arena).set("Location1.pitch", l.getPitch());
					
					plugin.getFc().getArena().getConfigurationSection(arena).set("Location2.world", l2.getWorld().getName());
					plugin.getFc().getArena().getConfigurationSection(arena).set("Location2.x", l2.getBlockX());
					plugin.getFc().getArena().getConfigurationSection(arena).set("Location2.y", 256);
					plugin.getFc().getArena().getConfigurationSection(arena).set("Location2.z", l2.getBlockZ());
					plugin.getFc().getArena().getConfigurationSection(arena).set("Location2.yaw", l.getYaw());
					plugin.getFc().getArena().getConfigurationSection(arena).set("Location2.pitch", l.getPitch());
					
					plugin.getFc().saveArena();
					
					plugin.setLocation1(null);
					plugin.setLocation2(null);
					
					plugin.sendMessage(player, "You have updated the Arena Location on " + arena + ".");
				}else{
					plugin.sendMessage(player, "Second Location is not set.");
				}
			}else{
				plugin.sendMessage(player, "First Position is not set.");
			}
		}else{
			plugin.sendMessage(player, "Arena does not exist. To create a arena Do /HP Create (Name)");
		}
	}
	
	public void setLobby(Player player, String arenaName){
		String arena = arenaName.toLowerCase();
		
		if(plugin.getFc().getArena().contains(arena)){
			if(plugin.getLocation1() != null){
				if(plugin.getLocation2() != null){
					
					Location l = plugin.getLocation1();
					Location l2 = plugin.getLocation2();
					
					plugin.getFc().getArena().getConfigurationSection(arena).set("Lobby1.world", l.getWorld().getName());
					plugin.getFc().getArena().getConfigurationSection(arena).set("Lobby1.x", l.getBlockX());
					plugin.getFc().getArena().getConfigurationSection(arena).set("Lobby1.y", 0);
					plugin.getFc().getArena().getConfigurationSection(arena).set("Lobby1.z", l.getBlockZ());
					plugin.getFc().getArena().getConfigurationSection(arena).set("Lobby1.yaw", l.getYaw());
					plugin.getFc().getArena().getConfigurationSection(arena).set("Lobby1.pitch", l.getPitch());
					
					plugin.getFc().getArena().getConfigurationSection(arena).set("Lobby2.world", l2.getWorld().getName());
					plugin.getFc().getArena().getConfigurationSection(arena).set("Lobby2.x", l2.getBlockX());
					plugin.getFc().getArena().getConfigurationSection(arena).set("Lobby2.y", 256);
					plugin.getFc().getArena().getConfigurationSection(arena).set("Lobby2.z", l2.getBlockZ());
					plugin.getFc().getArena().getConfigurationSection(arena).set("Lobby2.yaw", l.getYaw());
					plugin.getFc().getArena().getConfigurationSection(arena).set("Lobby2.pitch", l.getPitch());
					
					plugin.getFc().saveArena();
					
					plugin.setLocation1(null);
					plugin.setLocation2(null);
					
					plugin.sendMessage(player, "You have updated the Arena Location on " + arena + ".");
				}else{
					plugin.sendMessage(player, "Second Location is not set.");
				}
			}else{
				plugin.sendMessage(player, "First Position is not set.");
			}
		}else{
			plugin.sendMessage(player, "Arena does not exist. To create a arena Do /HP Create (Name)");
		}
	}
	
	public void setItemLocation(Player player, String arenaName){
		String arena = arenaName.toLowerCase();
	
		if(plugin.getFc().getArena().contains(arena)){
			
		}
	}
}