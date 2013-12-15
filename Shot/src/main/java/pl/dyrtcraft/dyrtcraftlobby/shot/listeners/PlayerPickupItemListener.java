package pl.dyrtcraft.dyrtcraftlobby.shot.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import pl.dyrtcraft.dyrtcraftlobby.DCLobby;
import pl.dyrtcraft.dyrtcraftlobby.Setting;
import pl.dyrtcraft.dyrtcraftlobby.shot.DyrtCraftLobbyShot;

public class PlayerPickupItemListener implements Listener {

	DyrtCraftLobbyShot plugin;
	
	public PlayerPickupItemListener(DyrtCraftLobbyShot dyrtCraftLobbyShot) {
		plugin = dyrtCraftLobbyShot;
	}
	
	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent e) {
		if(DCLobby.getSettings().getValue(Setting.PROTECT) == false && e.getPlayer().isOp()) {
			return;
		} else {
			e.setCancelled(true);
		}
	}
	
}
