package pl.dyrtcraft.dyrtcraftlobby.shot.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import pl.dyrtcraft.dyrtcraftlobby.shot.DyrtCraftLobbyShot;

public class PlayerJoinListener implements Listener {

	DyrtCraftLobbyShot plugin;
	
	public PlayerJoinListener(DyrtCraftLobbyShot dyrtCraftLobbyTree) {
		plugin = dyrtCraftLobbyTree;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		e.setJoinMessage("§f<> §a" + e.getPlayer().getName() + " §adolaczyl do Lobby§f <>");
		
		DyrtCraftLobbyShot.resetPlayer(e.getPlayer());
		
		if(e.getPlayer().isOp()) {
			e.getPlayer().chat("/tps");
		}
	}
	
}
