package pl.dyrtcraft.dyrtcraftlobby.tree.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import pl.DyrtCraft.DyrtCraftXP.api.BungeeInventory;
import pl.dyrtcraft.dyrtcraftlobby.tree.DyrtCraftLobbyTree;

public class PlayerInteractListener implements Listener {

	DyrtCraftLobbyTree plugin;
	
	public PlayerInteractListener(DyrtCraftLobbyTree dyrtCraftLobbyTree) {
		plugin = dyrtCraftLobbyTree;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		try {
			if(e.getItem().getType() == Material.COMPASS) {
				e.getPlayer().openInventory(BungeeInventory.getInventory());
			}
		} catch(NullPointerException ex) {}
	}
	
}
