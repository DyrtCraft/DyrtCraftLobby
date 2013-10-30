package pl.dyrtcraft.dyrtcraftlobby.tree.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import pl.dyrtcraft.dyrtcraftlobby.tree.DyrtCraftLobbyTree;
import pl.dyrtcraft.dyrtcraftlobby.tree.Util;

public class PlayerLoginListener implements Listener {

	DyrtCraftLobbyTree plugin;
	
	public PlayerLoginListener(DyrtCraftLobbyTree dyrtCraftLobbyTree) {
		plugin = dyrtCraftLobbyTree;
	}
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent e) {
		if(DyrtCraftLobbyTree.whitelist == true) {
			if(e.getPlayer().hasPermission("lobby.join")) {
				e.allow();
				return;
			} else {
				e.disallow(null, Util.kickMessage());
				return;
			}
		}
	}
	
}
