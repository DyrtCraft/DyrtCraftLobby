package pl.dyrtcraft.dyrtcraftlobby.tree.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import pl.dyrtcraft.dyrtcraftlobby.tree.DyrtCraftLobbyTree;
import pl.dyrtcraft.dyrtcraftlobby.tree.Util;

public class PlayerDropItemListener implements Listener {

	DyrtCraftLobbyTree plugin;
	
	public PlayerDropItemListener(DyrtCraftLobbyTree dyrtCraftLobbyTree) {
		plugin = dyrtCraftLobbyTree;
	}
	
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e) {
		if(!DyrtCraftLobbyTree.protect == false && e.getPlayer().hasPermission("lobby.interact")) {
			e.setCancelled(true);
			if(e.getPlayer().hasPermission("lobby.protect.set")) {
				e.getPlayer().sendMessage(Util.prefix() + ChatColor.GRAY + "Aby wyrzucac itemy nalezy wylaczyc cuboid uzywajac /dclobby protect false");
			}
		}
		e.setCancelled(true);
	}
	
}
