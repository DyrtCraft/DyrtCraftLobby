package pl.dyrtcraft.dyrtcraftlobby.tree.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import pl.dyrtcraft.dyrtcraftlobby.tree.DyrtCraftLobbyTree;

public class PlayerRespawnListener implements Listener {

	DyrtCraftLobbyTree plugin;
	
	public PlayerRespawnListener(DyrtCraftLobbyTree dyrtCraftLobbyTree) {
		plugin = dyrtCraftLobbyTree;
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		if(e.getPlayer().hasPermission("lobby.interact") && DyrtCraftLobbyTree.protect == false) { return; }
		DyrtCraftLobbyTree.resetPlayer(e.getPlayer());
	}
	
}
