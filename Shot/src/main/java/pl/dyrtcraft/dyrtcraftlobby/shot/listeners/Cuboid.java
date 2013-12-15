package pl.dyrtcraft.dyrtcraftlobby.shot.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import pl.dyrtcraft.dyrtcraftlobby.DCLobby;
import pl.dyrtcraft.dyrtcraftlobby.Setting;
import pl.dyrtcraft.dyrtcraftlobby.shot.DyrtCraftLobbyShot;
import pl.dyrtcraft.dyrtcraftlobby.shot.utils.Lang;

public class Cuboid implements Listener {

	DyrtCraftLobbyShot plugin;
	
	public Cuboid(DyrtCraftLobbyShot dyrtCraftLobbyTree) {
		plugin = dyrtCraftLobbyTree;
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if(DCLobby.getSettings().getValue(Setting.PROTECT) == true) {
			e.setCancelled(true);
			if(e.getPlayer().isOp()) {
				e.getPlayer().sendMessage(Lang.prefix() + ChatColor.GRAY + "Aby edytowac teren nalezy wylaczyc cuboid uzywajac /dclobby protect false");
			}
			return;
		}
		if(!e.getPlayer().isOp()) {
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
		if(DCLobby.getSettings().getValue(Setting.PROTECT) == true) {
			e.setCancelled(true);
			if(e.getPlayer().isOp()) {
				e.getPlayer().sendMessage(Lang.prefix() + ChatColor.GRAY + "Aby edytowac teren nalezy wylaczyc cuboid uzywajac /dclobby protect false");
			}
			return;
		}
		if(!e.getPlayer().isOp()) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onBlockSpread(BlockSpreadEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onExplostionPrime(ExplosionPrimeEvent e) {
		e.setCancelled(true);
		e.setFire(false);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if(DCLobby.getSettings().getValue(Setting.PROTECT) == true) {
			e.setCancelled(true);
			if(e.getPlayer().isOp()) {
				e.getPlayer().sendMessage(Lang.prefix() + ChatColor.GRAY + "Aby edytowac teren nalezy wylaczyc cuboid uzywajac /dclobby protect false");
			}
			return;
		}
		if(!e.getPlayer().isOp()) {
			e.setCancelled(true);
		}
	}
	
}
