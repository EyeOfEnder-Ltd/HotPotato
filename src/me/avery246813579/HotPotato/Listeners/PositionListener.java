package me.avery246813579.HotPotato.Listeners;

import me.avery246813579.HotPotato.HotPotato;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PositionListener implements Listener {

	private HotPotato plugin;
	
	public PositionListener(HotPotato plugin){
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		
		Player player = event.getPlayer();
		if(player.hasPermission(plugin.getPermissions().buildArena)){
			//Setting Position 1
			if(event.getAction() == Action.LEFT_CLICK_BLOCK && player.getItemInHand().getType() == Material.STICK){
				
				try{
					plugin.setLocation1(event.getClickedBlock().getLocation());
					plugin.sendMessage(player, "Position 1: " + plugin.showBlockCoords(plugin.getLocation1()));
					event.setCancelled(true);
				}catch(Exception e){
					plugin.sendMessage(player, "Plugin Failed To Create Waypoint!");
					plugin.sendConsole("Plugin Failed To Create Waypoint!");
				}
			}
			
			if(event.getAction() == Action.RIGHT_CLICK_BLOCK && player.getItemInHand().getType() == Material.STICK){
				
				try{
					plugin.setLocation2(event.getClickedBlock().getLocation());
					plugin.sendMessage(player, "Position 2: " + plugin.showBlockCoords(plugin.getLocation2()));
					event.setCancelled(true);
				}catch(Exception e){
					plugin.sendMessage(player, "Plugin Failed To Create Waypoint!");
					plugin.sendConsole("Plugin Failed To Create Waypoint!");
				}
			}
		}
	}
}
