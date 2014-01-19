package pl.dyrtcraft.dyrtcraftlobby.shot.listeners;

import java.util.UnknownFormatConversionException;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import pl.dyrtcraft.DyrtCraft;
import pl.dyrtcraft.dyrtcraftlobby.DCLobby;
import pl.dyrtcraft.dyrtcraftlobby.Setting;
import pl.dyrtcraft.dyrtcraftlobby.shot.DyrtCraftLobbyShot;
import pl.dyrtcraft.dyrtcraftlobby.shot.utils.Lang;

public class AsyncPlayerChatListener implements Listener {
	
	DyrtCraftLobbyShot plugin;
	
	public AsyncPlayerChatListener(DyrtCraftLobbyShot dyrtCraftLobbyShot) {
		plugin = dyrtCraftLobbyShot;
	}
	
	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
		if(!e.getPlayer().isOp() && DCLobby.getSettings().getValue(Setting.CHAT) == false) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(Lang.prefix() + Lang.chatOff());
			DyrtCraft.getUtils().sendNotify(e.getPlayer().getName() + ": '" + e.getMessage() + "'", false);
			return;
		}
		
		if(e.getPlayer().isOp()) {
			try {
				e.setFormat(ChatColor.RED + "%s" + ": " + ChatColor.WHITE + "%s");
			} catch(UnknownFormatConversionException ex) {}
		} else {
			try {
				e.setFormat(ChatColor.GRAY + "%s" + ": " + ChatColor.WHITE + "%s");
			} catch(UnknownFormatConversionException ex) {}
		}
	}
	
}
