package pl.dyrtcraft.dyrtcraftlobby.shot.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import pl.DyrtCraft.DyrtCraftXP.api.BungeeInventory;
import pl.DyrtCraft.DyrtCraftXP.api.Kits;

import pl.dyrtcraft.dyrtcraftlobby.shot.DyrtCraftLobbyShot;

public class PlayerInteractListener implements Listener {

	DyrtCraftLobbyShot plugin;
	
	public PlayerInteractListener(DyrtCraftLobbyShot dyrtCraftLobbyTree) {
		plugin = dyrtCraftLobbyTree;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		try {
			if(Material.COMPASS != null);
			if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Kits.compass().getItemMeta().getDisplayName())) {
				e.getPlayer().openInventory(BungeeInventory.getInventory());
			}
		} catch(NullPointerException ex) {}
	}
	
}
