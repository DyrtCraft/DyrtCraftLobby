package pl.dyrtcraft.dyrtcraftlobby.shot.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import pl.dyrtcraft.dyrtcraftlobby.shot.DyrtCraftLobbyShot;
import pl.dyrtcraft.dyrtcraftlobby.shot.Util;

public class PlayerDropItemListener implements Listener {

	DyrtCraftLobbyShot plugin;
	
	public PlayerDropItemListener(DyrtCraftLobbyShot dyrtCraftLobbyTree) {
		plugin = dyrtCraftLobbyTree;
	}
	
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e) {
		if(DyrtCraftLobbyShot.protect == true && e.getPlayer().hasPermission("lobby.interact")) {
			e.setCancelled(true);
			if(e.getPlayer().isOp()) {
				e.getPlayer().sendMessage(Util.prefix() + ChatColor.GRAY + "Aby wyrzucac itemy nalezy wylaczyc cuboid uzywajac /dclobby protect false");
			}
		}
		if(DyrtCraftLobbyShot.protect == false && e.getPlayer().hasPermission("lobby.interact")) { return; }
		e.setCancelled(true);
	}
	
}
