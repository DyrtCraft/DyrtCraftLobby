package pl.dyrtcraft.dyrtcraftlobby.tree.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;

import pl.dyrtcraft.dyrtcraftlobby.tree.DyrtCraftLobbyTree;

public class InventoryListener implements Listener {

	DyrtCraftLobbyTree plugin;
	
	public InventoryListener(DyrtCraftLobbyTree dyrtCraftLobbyTree) {
		plugin = dyrtCraftLobbyTree;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		try {
			if(!e.getWhoClicked().hasPermission("lobby.interact") && DyrtCraftLobbyTree.protect == false) {
				e.setCancelled(true);
			}
		} catch(NullPointerException ex) {}
	}
	
	@EventHandler
	public void onInventoryInteract(InventoryInteractEvent e) {
		try {
			if(!e.getWhoClicked().hasPermission("lobby.interact") && DyrtCraftLobbyTree.protect == false) {
				e.setCancelled(true);
			}
		} catch(NullPointerException ex) {}
	}
	
	@EventHandler
	public void onInventoryPickupItem(InventoryPickupItemEvent e) {
		e.setCancelled(true);
	}
	
}
