package pl.dyrtcraft.dyrtcraftlobby.tree.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import pl.DyrtCraft.DyrtCraftXP.events.PlayerChangeServerEvent;
import pl.dyrtcraft.dyrtcraftlobby.tree.DyrtCraftLobbyTree;
import pl.dyrtcraft.dyrtcraftlobby.tree.Util;

public class PlayerChangeServerListener implements Listener {

	DyrtCraftLobbyTree plugin;
	PlayerQuitEvent quit;
	
	public PlayerChangeServerListener(DyrtCraftLobbyTree dyrtCraftLobbyTree) {
		plugin = dyrtCraftLobbyTree;
	}
	
	@EventHandler
	public void onPlayerChangeServer(PlayerChangeServerEvent e) {
		if(e.getServer().equalsIgnoreCase("Lobby")) { // Gracz uzyl itemu "Lobby"
			e.setCancelled(true);
			e.getPlayer().sendMessage(Util.prefix() + ChatColor.RED + "Juz znajdujesz sie na serwerze Lobby!");
		} else { // Gracz uzyl innego itemu
			plugin.getServer().broadcastMessage("§f<> §a" + e.getPlayer().getName() + " §aprzeszedl na serwer " + e.getServer() + " §f <>");
		}
	}
	
}
