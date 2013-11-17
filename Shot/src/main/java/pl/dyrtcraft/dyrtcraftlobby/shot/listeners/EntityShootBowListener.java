package pl.dyrtcraft.dyrtcraftlobby.shot.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

import pl.dyrtcraft.dyrtcraftlobby.shot.DyrtCraftLobbyShot;

public class EntityShootBowListener implements Listener {
	
	DyrtCraftLobbyShot plugin;
	
	public EntityShootBowListener(DyrtCraftLobbyShot dyrtCraftLobbyShot) {
		plugin = dyrtCraftLobbyShot;
	}
	
	@EventHandler
	public void onEntityShootBow(EntityShootBowEvent e) {
		e.setCancelled(true);
	}
	
}
