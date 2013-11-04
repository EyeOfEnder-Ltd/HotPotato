package me.avery246813579.HotPotato.Util;

import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;

import me.avery246813579.HotPotato.HotPotato;

public class Permissions {

	private HotPotato plugin;
	
	public Permissions (HotPotato plugin){
		this.plugin = plugin;
	}
	
	public Permission buildArena = new Permission("HotPotato.BuildArena");
	
	/*******************************************************************************
	 * 
	 *                           Enable Permissions
	 * 
	 *******************************************************************************/
	
	public void enablePermissions(){
		PluginManager pm = plugin.getServer().getPluginManager();
		
		pm.addPermission(buildArena);
	}
	
	/********************************************************************************
	 * 
	 *                          Disable Permissions
	 * 
	 ********************************************************************************/
	
	public void disablePermissions(){
		PluginManager pm = plugin.getServer().getPluginManager();
		
		pm.removePermission(buildArena);
	}
}
