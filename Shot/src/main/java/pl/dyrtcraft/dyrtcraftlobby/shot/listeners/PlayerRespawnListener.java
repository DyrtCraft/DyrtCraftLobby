package pl.dyrtcraft.dyrtcraftlobby.shot.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import pl.dyrtcraft.dyrtcraftlobby.DCLobby;
import pl.dyrtcraft.dyrtcraftlobby.shot.DyrtCraftLobbyShot;

public class PlayerRespawnListener implements Listener {

	DyrtCraftLobbyShot plugin;
	
	public PlayerRespawnListener(DyrtCraftLobbyShot dyrtCraftLobbyTree) {
		plugin = dyrtCraftLobbyTree;
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		DCLobby.getPlayer(e.getPlayer()).reset();
	}
	
}
