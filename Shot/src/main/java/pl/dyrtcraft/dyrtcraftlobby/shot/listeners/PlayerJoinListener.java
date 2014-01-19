package pl.dyrtcraft.dyrtcraftlobby.shot.listeners;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import pl.dyrtcraft.DyrtCraft;
import pl.dyrtcraft.dyrtcraftlobby.DCLobby;
import pl.dyrtcraft.dyrtcraftlobby.shot.DyrtCraftLobbyShot;

public class PlayerJoinListener implements Listener {

	DyrtCraftLobbyShot plugin;
	private ArrayList<String> players = new ArrayList<String>();
	
	public PlayerJoinListener(DyrtCraftLobbyShot dyrtCraftLobbyTree) {
		plugin = dyrtCraftLobbyTree;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		e.setJoinMessage("§f<> §a" + e.getPlayer().getName() + " §adolaczyl do Lobby§f <>");
		
		DCLobby.getPlayer(e.getPlayer()).reset();
		
		if(!players.contains(e.getPlayer().getName())) {
			players.add(e.getPlayer().getName());
			DyrtCraft.getMember(e.getPlayer()).addXp(1, "Witaj ponownie w DyrtCraft Network");
		} else {
			e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "Czesc " + e.getPlayer().getName() + "." +
					" Dzisiaj juz dostales/as dzienne punkty XP! " + ChatColor.UNDERLINE + "Wróc po nowe ponownie jutro" +
					ChatColor.RESET + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + " :)");
		}
	}
	
}
