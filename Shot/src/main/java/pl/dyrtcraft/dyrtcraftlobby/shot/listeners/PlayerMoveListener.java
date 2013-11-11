package pl.dyrtcraft.dyrtcraftlobby.shot.listeners;

import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import pl.dyrtcraft.dyrtcraftlobby.shot.DyrtCraftLobbyShot;

public class PlayerMoveListener implements Listener {
	
	DyrtCraftLobbyShot plugin;
	
	public PlayerMoveListener(DyrtCraftLobbyShot dyrtCraftLobbyTree) {
		plugin = dyrtCraftLobbyTree;
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		if(e.getPlayer().hasPermission("lobby.interact") && DyrtCraftLobbyShot.protect == false) { return; }
		
		if(e.getTo().getBlockY() < 60 || e.getTo().getBlockY() > 90) {
			e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
			DyrtCraftLobbyShot.resetPlayer(e.getPlayer());
		}
		if(e.getTo().getBlockX() < -40 || e.getTo().getBlockX() > 40) {
			e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
			DyrtCraftLobbyShot.resetPlayer(e.getPlayer());
		}
		if(e.getTo().getBlockZ() < -40 || e.getTo().getBlockZ() > 40) {
			e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
			DyrtCraftLobbyShot.resetPlayer(e.getPlayer());
		}
	}
	
}
