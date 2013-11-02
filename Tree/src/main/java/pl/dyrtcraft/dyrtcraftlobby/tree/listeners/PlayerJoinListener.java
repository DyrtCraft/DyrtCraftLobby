package pl.dyrtcraft.dyrtcraftlobby.tree.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import pl.dyrtcraft.dyrtcraftlobby.tree.DyrtCraftLobbyTree;

public class PlayerJoinListener implements Listener {

	DyrtCraftLobbyTree plugin;
	
	public PlayerJoinListener(DyrtCraftLobbyTree dyrtCraftLobbyTree) {
		plugin = dyrtCraftLobbyTree;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		e.setJoinMessage("§f<> §a" + e.getPlayer().getName() + " §adolaczyl do Lobby§f <>");
		
		DyrtCraftLobbyTree.resetPlayer(e.getPlayer());
		
		if(e.getPlayer().hasPermission("essentials.gc") && plugin.getServer().getPluginManager().isPluginEnabled("EssentialsSpawn")) {
			e.getPlayer().performCommand("gc");
		}
	}
	
}
