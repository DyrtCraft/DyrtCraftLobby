package pl.dyrtcraft.dyrtcraftlobby.shot.listeners;

import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

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
		
		Vector v1 = e.getPlayer().getLocation().getDirection();
		v1.setY(30);
		e.getPlayer().setVelocity(v1.multiply(0.03));
		e.getPlayer().setFallDistance(0);
		e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ZOMBIE_INFECT, 0.5f, 1.8f);
		e.getPlayer().setAllowFlight(false);
	}
	
}
