package pl.dyrtcraft.dyrtcraftlobby.shot.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;

import pl.dyrtcraft.dyrtcraftlobby.shot.DyrtCraftLobbyShot;

public class InventoryListener implements Listener {

	DyrtCraftLobbyShot plugin;
	
	public InventoryListener(DyrtCraftLobbyShot dyrtCraftLobbyTree) {
		plugin = dyrtCraftLobbyTree;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		try {
			if(e.getWhoClicked().hasPermission("lobby.interact") && DyrtCraftLobbyShot.protect == false) { return; }
			e.setCancelled(true);
		} catch(NullPointerException ex) {}
	}
	
	@EventHandler
	public void onInventoryInteract(InventoryInteractEvent e) {
		try {
			if(!e.getWhoClicked().hasPermission("lobby.interact") && DyrtCraftLobbyShot.protect == false) { return; }
			e.setCancelled(true);
		} catch(NullPointerException ex) {}
	}
	
	@EventHandler
	public void onInventoryPickupItem(InventoryPickupItemEvent e) {
		e.setCancelled(true);
	}
	
}
