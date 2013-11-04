package me.avery246813579.HotPotato.Game;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.avery246813579.HotPotato.HotPotato;

public class GameItems {

	public GameItems (HotPotato plugin){
	}
	
	public ItemStack hotPotato() {
		ItemStack is = new ItemStack(Material.POTATO_ITEM);
		ItemMeta im = is.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.DARK_RED + "Right click a player to give away.");
		im.setLore(lore);
		im.setDisplayName("Hot Potato");
		im.addEnchant(Enchantment.KNOCKBACK, 1, true);
		is.setItemMeta(im);
		return is;
	}
	
	public void addPotato(Player player){
		/** Adds the potato **/
		player.getInventory().addItem(hotPotato());
		
		/** Adds the tNT **/
		ItemStack tnt = new ItemStack(Material.TNT);	
		player.getInventory().setHelmet(tnt);
		
		/** Adds extra speed effects **/
		this.clearPotionEffects(player);
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000000, 4));
	}
	
	public void removePotato(Player player){
		/** Removes the potato **/
		player.getInventory().remove(Material.POTATO);
		
		/** Sets the players helmet to air **/
		player.getInventory().getHelmet().setType(Material.AIR);
		
		/** Removes extra speed effects **/
		this.clearPotionEffects(player);
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000000, 3));
	}
	
	public void clearPotionEffects(Player player)
	{
		for (PotionEffect effect : player.getActivePotionEffects())
		{
			player.removePotionEffect(effect.getType());
		}
	}
}
