package pl.dyrtcraft.dyrtcraftlobby.shot.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import pl.DyrtCraft.DyrtCraftXP.events.PlayerChangeServerEvent;

import pl.dyrtcraft.dyrtcraftlobby.shot.DyrtCraftLobbyShot;
import pl.dyrtcraft.dyrtcraftlobby.shot.Util;

public class PlayerChangeServerListener implements Listener {

	DyrtCraftLobbyShot plugin;
	PlayerQuitEvent quit;
	
	public PlayerChangeServerListener(DyrtCraftLobbyShot dyrtCraftLobbyTree) {
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
