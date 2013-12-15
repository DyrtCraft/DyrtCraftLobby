package pl.dyrtcraft.dyrtcraftlobby.shot.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import pl.dyrtcraft.Server;
import pl.dyrtcraft.dyrtcraftlobby.shot.DyrtCraftLobbyShot;
import pl.dyrtcraft.dyrtcraftlobby.shot.utils.Lang;
import pl.dyrtcraft.events.PlayerChangeServerEvent;

public class PlayerChangeServerListener implements Listener {

	DyrtCraftLobbyShot plugin;
	PlayerQuitEvent quit;
	
	public PlayerChangeServerListener(DyrtCraftLobbyShot dyrtCraftLobbyTree) {
		plugin = dyrtCraftLobbyTree;
	}
	
	@EventHandler
	public void onPlayerChangeServer(PlayerChangeServerEvent e) {
		if(e.getServer() == Server.LOBBY) { // Gracz uzyl itemu "Lobby"
			e.setCancelled(true);
			e.getPlayer().sendMessage(Lang.prefix() + ChatColor.RED + "Juz znajdujesz sie na serwerze Lobby!");
		} else { // Gracz uzyl innego itemu
			Bukkit.broadcastMessage("§f<> §a" + e.getPlayer().getName() + " §aprzeszedl na serwer " + e.getServer().toString() + " §f <>");
		}
	}
	
}
