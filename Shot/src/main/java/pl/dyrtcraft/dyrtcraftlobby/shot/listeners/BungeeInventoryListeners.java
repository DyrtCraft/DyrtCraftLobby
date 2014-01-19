package pl.dyrtcraft.dyrtcraftlobby.shot.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import pl.dyrtcraft.dyrtcraftlobby.shot.DyrtCraftLobbyShot;
import pl.dyrtcraft.dyrtcraftlobby.shot.Shop;
import pl.dyrtcraft.dyrtcraftlobby.shot.utils.Lang;
import pl.dyrtcraft.events.InfoBookClickEvent;
import pl.dyrtcraft.events.ShopClickEvent;

public class BungeeInventoryListeners implements Listener {
	
	DyrtCraftLobbyShot plugin;
	
	public BungeeInventoryListeners(DyrtCraftLobbyShot plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onInfoBookClick(InfoBookClickEvent e) {
		e.getPlayer().sendMessage(Lang.prefix() + ChatColor.RED + "Zadne informacje nie sa dostepne - znajdujesz sie na serwerze Lobby!");
	}
	
	@EventHandler
	public void onShopClick(ShopClickEvent e) {
		Shop.show(e.getPlayer());
	}
	
}
