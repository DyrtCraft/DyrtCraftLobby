package pl.dyrtcraft.dyrtcraftlobby.shot.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import pl.dyrtcraft.dyrtcraftlobby.shot.DyrtCraftLobbyShot;

public class SignChangeListener implements Listener {
	
	DyrtCraftLobbyShot plugin;
	
	public SignChangeListener(DyrtCraftLobbyShot plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		e.setLine(0, e.getLine(0).replace("&", "§"));
		e.setLine(1, e.getLine(1).replace("&", "§"));
		e.setLine(2, e.getLine(2).replace("&", "§"));
		e.setLine(3, e.getLine(3).replace("&", "§"));
	}
	
}
