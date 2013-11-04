package me.avery246813579.HotPotato;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import me.avery246813579.HotPotato.Arena.Arena;
import me.avery246813579.HotPotato.Arena.ArenaCreator;
import me.avery246813579.HotPotato.Game.GameItems;
import me.avery246813579.HotPotato.Game.GameManager;
import me.avery246813579.HotPotato.Handlers.ConfigurationHandler;
import me.avery246813579.HotPotato.Handlers.FileHandler;
import me.avery246813579.HotPotato.Listeners.PositionListener;
import me.avery246813579.HotPotato.Util.Permissions;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class HotPotato extends JavaPlugin{

	private Logger logger = Logger.getLogger("Minecraft");
	
	/** Classes **/
	private FileHandler fc = new FileHandler(this);
	private Permissions permissions = new Permissions(this);
	private ConfigurationHandler configHandler = new ConfigurationHandler(this);
	private GameItems gameItems = new GameItems(this);
	private ArenaCreator ac = new ArenaCreator(this);
	
	private Location Location1 = null;
	private Location Location2 = null;
	
	private List<Player> inArena = new ArrayList<Player>();
	private List<GameManager> activeArenas = new ArrayList<GameManager>();
	
	@Override
	public void onEnable() {
		/** Loads Everything **/
		fc.configSave();
		configHandler.loadConfig();
		
		/** Tells player that debug is enabled **/
		this.sendDebug("Debug is enabled. Disable it in the config.");
		
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new PositionListener(this), this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	/***********************************************************************************
	 * 
	 *                               Messages  
	 * 
	 **********************************************************************************/
	
	public void sendMessage(Player player, String Message){
		player.sendMessage(ChatColor.GOLD + "[" + ChatColor.BLUE + "Hot Potato" + ChatColor.GOLD + "] " + ChatColor.GRAY + Message);
	}
	
	public void sendConsole(String Message){
		this.logger.info("[Hot Potato] " + Message);
	}
	
	public void sendDebug(String Message){
		if(this.getConfigHandler().isDebug()){
			this.logger.info("[Hot Potato - DEBUG] " + Message);
		}else{
			return;
		}
	}
	
	public void sendArgs(Player player){
		this.sendMessage(player, "Incorrect Amount of Arguments.");
	}
	
	public String showBlockCoords(Location l)
	{
		return(ChatColor.LIGHT_PURPLE + "" + l.getBlockX() + ", " + l.getBlockY() + ", " + l.getBlockZ());
	}
	
	/***********************************************************************************
	 * 
	 *                               	Commands  
	 * 
	 **********************************************************************************/
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String CommandLabel, String[] args) {
		
		if(sender instanceof Player){
			
			Player player = (Player) sender;
			
			if(CommandLabel.equalsIgnoreCase("HotPotato") || CommandLabel.equalsIgnoreCase("HP")){
				
				if(args.length == 0){
				
					//Help Menu
					
				}else if(args.length == 1){
					
					if(args[0].equalsIgnoreCase("Set")){
						
						this.sendArgs(player);
						
					}
					
					if(args[0].equalsIgnoreCase("Join")){
						
						this.sendArgs(player);
						
					}
					
					if(args[0].equalsIgnoreCase("Countdown") || args[0].equalsIgnoreCase("C")){
						
						GameManager gm = this.getPlayersGame(player);
						
						if(gm != null){
							gm.startLobbyTimer();
							this.sendMessage(player, "Countdown has started.");
						}else{
							this.sendMessage(player, "You have to be in a arena to do this.");
						}
						
					}
					
				}else if(args.length == 2){
					
					if(args[0].equalsIgnoreCase("Join")){
						String arena = args[1].toLowerCase();
						
						GameManager gm = this.getGameManager(arena);
						
						if(activeArenas.contains(gm)){
						
							gm.joinArena(player);
						
						}else{
							if(this.getFc().getArena().contains(arena)){
								Arena arenaa = new Arena(this, arena);
								GameManager gmn = new GameManager(this, arenaa);
								gmn.joinArena(player);
								activeArenas.add(gmn);
							}else{
								this.sendMessage(player, "There is no arena called " + args[1] + ".");
							}
						}
					}
					
					if(args[0].equalsIgnoreCase("Create")){
						
						ac.createArena(player, args[1]);
						
					}
					
					if(args[0].equalsIgnoreCase("Set")){
						
						this.sendArgs(player);
						
					}
					
				}else if(args.length == 3){
				
					if(args[0].equalsIgnoreCase("Set") || args[0].equalsIgnoreCase("S")){
						
						if(args[1].equalsIgnoreCase("Lobby")){
							
							ac.setLobbySpawn(player, args[2]);
							
						}
						
						if(args[1].equalsIgnoreCase("Spawn")){
							
							ac.setSpawn(player, args[2]);
							
						}
						
						if(args[1].equalsIgnoreCase("End")){
							
							ac.setEnd(player, args[2]);
							
						}
						
						if(args[1].equalsIgnoreCase("Arena")){
							
							ac.setArena(player, args[2]);
							
						}
						
						if(args[1].equalsIgnoreCase("Spec")){
							
							ac.setSpec(player, args[2]);
							
						}else{
							
							this.sendMessage(player, "Creation type not reconized. Please check the bukkit page!");
							
						}
					}
					
				}
			}
		}else{
			this.sendConsole("You have to be In-Game to use Hot Potato Commands.");
		}
		
		return false;
		
	}
	
	/***********************************************************************************
	 * 
	 *                               Getters & Setters  
	 * 
	 **********************************************************************************/

	public GameManager getGameManager (String arena){
		for (int i = 0; i < activeArenas.size(); i++)
		{
			
			GameManager gm = activeArenas.get(i);
			
			if(gm.getArena().getArenaName().equalsIgnoreCase(arena)){
				return gm;
			}
		}
		return null;
	}
	
	public GameManager getPlayersGame (Player player){
		for(GameManager gm : activeArenas){
			if(gm.getArenaPlayers().contains(player)){
				return gm;
			}
		}
		return null;
	}
	
	public FileHandler getFc() {
		return fc;
	}

	public void setFc(FileHandler fc) {
		this.fc = fc;
	}

	public Permissions getPermissions() {
		return permissions;
	}

	public void setPermissions(Permissions permissions) {
		this.permissions = permissions;
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

	public List<Player> getInArena() {
		return inArena;
	}

	public void setInArena(List<Player> inArena) {
		this.inArena = inArena;
	}

	public ConfigurationHandler getConfigHandler() {
		return configHandler;
	}

	public void setConfigHandler(ConfigurationHandler configHandler) {
		this.configHandler = configHandler;
	}

	public GameItems getGameItems() {
		return gameItems;
	}

	public void setGameItems(GameItems gameItems) {
		this.gameItems = gameItems;
	}
}

