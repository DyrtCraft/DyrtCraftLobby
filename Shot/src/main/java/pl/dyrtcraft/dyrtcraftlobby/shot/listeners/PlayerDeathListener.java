package pl.dyrtcraft.dyrtcraftlobby.shot.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import pl.dyrtcraft.dyrtcraftlobby.shot.DyrtCraftLobbyShot;

public class PlayerDeathListener implements Listener {
	
	DyrtCraftLobbyShot plugin;
	
	public PlayerDeathListener(DyrtCraftLobbyShot dyrtCraftLobbyShot) {
		plugin = dyrtCraftLobbyShot;
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		e.setDeathMessage(null);
		DyrtCraftLobbyShot.resetPlayer(e.getEntity());
	}
	
}
