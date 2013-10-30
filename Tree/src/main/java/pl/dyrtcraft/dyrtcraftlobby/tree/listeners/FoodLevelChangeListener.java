package pl.dyrtcraft.dyrtcraftlobby.tree.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import pl.dyrtcraft.dyrtcraftlobby.tree.DyrtCraftLobbyTree;

public class FoodLevelChangeListener implements Listener {

	DyrtCraftLobbyTree plugin;
	
	public FoodLevelChangeListener(DyrtCraftLobbyTree dyrtCraftLobbyTree) {
		plugin = dyrtCraftLobbyTree;
	}
	
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}
	
}
