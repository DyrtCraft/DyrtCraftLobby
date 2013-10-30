package pl.dyrtcraft.dyrtcraftlobby.tree.listeners;

import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import pl.dyrtcraft.dyrtcraftlobby.tree.DyrtCraftLobbyTree;

public class PlayerMoveListener implements Listener {
	
	DyrtCraftLobbyTree plugin;
	
	public PlayerMoveListener(DyrtCraftLobbyTree dyrtCraftLobbyTree) {
		plugin = dyrtCraftLobbyTree;
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		if(e.getPlayer().hasPermission("lobby.interact") && DyrtCraftLobbyTree.protect == false) { return; }
		
		if(e.getTo().getBlockY() > 80) {
			e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
			DyrtCraftLobbyTree.resetPlayer(e.getPlayer());
		}
	}
	
}
