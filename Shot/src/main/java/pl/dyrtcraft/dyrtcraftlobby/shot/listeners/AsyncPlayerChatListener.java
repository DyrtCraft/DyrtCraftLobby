package pl.dyrtcraft.dyrtcraftlobby.shot.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import pl.DyrtCraft.DyrtCraftXP.api.DyrtCraftPlugin;
import pl.dyrtcraft.dyrtcraftlobby.shot.DyrtCraftLobbyShot;
import pl.dyrtcraft.dyrtcraftlobby.shot.Util;

public class AsyncPlayerChatListener implements Listener {
	
	DyrtCraftLobbyShot plugin;
	
	public AsyncPlayerChatListener(DyrtCraftLobbyShot dyrtCraftLobbyShot) {
		plugin = dyrtCraftLobbyShot;
	}
	
	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
		if(!e.getPlayer().isOp() && DyrtCraftLobbyShot.chat == false) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(Util.prefix() + Util.chatOff());
			DyrtCraftPlugin.sendMsgToOp(e.getPlayer().getName() + " próbowal napisac '" + e.getMessage() + "'", 0);
			return;
		}
		
		if(e.getPlayer().isOp()) {
			e.setFormat(ChatColor.RED + e.getPlayer().getName() + ": " + ChatColor.WHITE + e.getMessage());
		} else {
			e.setFormat(ChatColor.GRAY + e.getPlayer().getName() + ": " + ChatColor.WHITE + e.getMessage());
		}
	}
	
}
