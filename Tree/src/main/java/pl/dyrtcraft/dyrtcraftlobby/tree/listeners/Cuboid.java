package pl.dyrtcraft.dyrtcraftlobby.tree.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import pl.dyrtcraft.dyrtcraftlobby.tree.DyrtCraftLobbyTree;
import pl.dyrtcraft.dyrtcraftlobby.tree.Util;

public class Cuboid implements Listener {

	DyrtCraftLobbyTree plugin;
	
	public Cuboid(DyrtCraftLobbyTree dyrtCraftLobbyTree) {
		plugin = dyrtCraftLobbyTree;
	}
	
	/*
	 *  TODO Flaga na ogien
	 */
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if(DyrtCraftLobbyTree.protect == true) {
			e.setCancelled(true);
			if(e.getPlayer().hasPermission("lobby.protect.set")) {
				e.getPlayer().sendMessage(Util.prefix() + ChatColor.GRAY + "Aby edytowac teren nalezy wylaczyc cuboid uzywajac /dclobby protect false");
			}
			return;
		}
		if(!e.getPlayer().hasPermission("lobby.interact")) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockBurn(BlockBurnEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockFade(BlockFadeEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if(DyrtCraftLobbyTree.protect == true) {
			e.setCancelled(true);
			if(e.getPlayer().hasPermission("lobby.protect.set")) {
				e.getPlayer().sendMessage(Util.prefix() + ChatColor.GRAY + "Aby edytowac teren nalezy wylaczyc cuboid uzywajac /dclobby protect false");
			}
			return;
		}
		if(!e.getPlayer().hasPermission("lobby.interact")) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onBlockSpread(BlockSpreadEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if(DyrtCraftLobbyTree.protect == true) {
			e.setCancelled(true);
			if(e.getPlayer().hasPermission("lobby.protect.set")) {
				e.getPlayer().sendMessage(Util.prefix() + ChatColor.GRAY + "Aby edytowac teren nalezy wylaczyc cuboid uzywajac /dclobby protect false");
			}
			return;
		}
		if(!e.getPlayer().hasPermission("lobby.interact")) {
			e.setCancelled(true);
		}
	}
	
}
