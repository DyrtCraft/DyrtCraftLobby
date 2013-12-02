package pl.dyrtcraft.dyrtcraftlobby.shot.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import pl.DyrtCraft.DyrtCraftXP.api.Bar;

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
			e.getPlayer().setPlayerListName(ChatColor.RED + e.getPlayer().getName());
			e.getPlayer().chat("/tps");
		} else {
			e.getPlayer().setPlayerListName(ChatColor.GOLD + e.getPlayer().getName());
		}
		
		Bar.removeBar(e.getPlayer());
		Bar.setMessage(e.getPlayer(), ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Witaj " + e.getPlayer().getName() + " na DyrtCraft Network!");
	}
	
}
