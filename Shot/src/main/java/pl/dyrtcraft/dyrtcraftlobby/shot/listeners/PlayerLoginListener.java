package pl.dyrtcraft.dyrtcraftlobby.shot.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import pl.DyrtCraft.DyrtCraftXP.api.DyrtCraftPlugin;

import pl.dyrtcraft.dyrtcraftlobby.shot.DyrtCraftLobbyShot;
import pl.dyrtcraft.dyrtcraftlobby.shot.Util;

public class PlayerLoginListener implements Listener {

	DyrtCraftLobbyShot plugin;
	
	public PlayerLoginListener(DyrtCraftLobbyShot dyrtCraftLobbyTree) {
		plugin = dyrtCraftLobbyTree;
	}
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent e) {
		if(DyrtCraftLobbyShot.whitelist == true) {
			if(e.getPlayer().hasPermission("lobby.join")) {
				e.allow();
				return;
			} else {
				e.disallow(null, Util.kickMessage());
				DyrtCraftPlugin.sendMsgToOp(e.getPlayer().getName() + " nie polaczyl sie z serwerem: Ustawienie whitelist jest na \"true\"", 0);
				return;
			}
		}
	}
	
}
