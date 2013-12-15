package pl.dyrtcraft.dyrtcraftlobby.shot.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import pl.dyrtcraft.dyrtcraftlobby.DCLobby;
import pl.dyrtcraft.dyrtcraftlobby.Setting;
import pl.dyrtcraft.dyrtcraftlobby.shot.DyrtCraftLobbyShot;

public class PlayerToggleFlightListener implements Listener {
	
	DyrtCraftLobbyShot plugin;
	
	public PlayerToggleFlightListener(DyrtCraftLobbyShot plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerToggleFlight(PlayerToggleFlightEvent e) {
		if(DCLobby.getSettings().getValue(Setting.PROTECT) == false && e.getPlayer().isOp()) { return; }
		
		e.getPlayer().setAllowFlight(false);
	}
	
}
