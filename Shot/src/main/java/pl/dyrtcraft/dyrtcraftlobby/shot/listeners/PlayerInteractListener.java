package pl.dyrtcraft.dyrtcraftlobby.shot.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import pl.dyrtcraft.DyrtCraft;
import pl.dyrtcraft.dyrtcraftlobby.DCLobby;
import pl.dyrtcraft.dyrtcraftlobby.Setting;
import pl.dyrtcraft.dyrtcraftlobby.shot.DyrtCraftLobbyShot;
import pl.dyrtcraft.dyrtcraftlobby.shot.Shop;

public class PlayerInteractListener implements Listener {

	DyrtCraftLobbyShot plugin;
	
	public PlayerInteractListener(DyrtCraftLobbyShot dyrtCraftLobbyTree) {
		plugin = dyrtCraftLobbyTree;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		try {
			if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				// Sklep
				if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Sklep DyrtCraft Network")) {
					Shop.show(e.getPlayer());
				}
				// Compass
				if(e.getItem().getType() == Material.COMPASS) {
					if(e.getPlayer().isOp() && DCLobby.getSettings().getValue(Setting.PROTECT) == false) { return; }
					e.getPlayer().openInventory(DyrtCraft.getProxy().getServerChooserInventory(e.getPlayer()));
				}
			}
		} catch(NullPointerException ex) {}
	}
	
}
