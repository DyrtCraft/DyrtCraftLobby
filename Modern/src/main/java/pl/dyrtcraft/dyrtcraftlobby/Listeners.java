package pl.dyrtcraft.dyrtcraftlobby;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import pl.DyrtCraft.DyrtCraftXP.api.BungeeInventory;

public class Listeners implements Listener {

	DyrtCraftLobby plugin;
	
	public Listeners(DyrtCraftLobby dyrtCraftLobby) {
		plugin = dyrtCraftLobby;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDamage(EntityDamageEvent e) {
		e.setCancelled(true);
	}
	
	// Niszczenie blocku (cuboid)
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = (Player) e.getPlayer();
		if(p.isOp() && plugin.protect == false) {
			// To co ma sie wykonac na trybie gry kreatywnym
		} else {
			// Jezeli gracz nie jest OP, oraz nie posiadam GameMode Creative
			e.setCancelled(true);
		}
	}
	
	// Stawianie bloku (cuboid)
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockPlace(BlockPlaceEvent e) {
		Player p = (Player) e.getPlayer();
		if(p.isOp() && plugin.protect == false) {
			// To co ma sie wykonac na trybie gry kreatywnym
		} else {
			// Jezeli gracz nie jest OP, oraz nie posiadam GameMode Creative
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		try {
			Player p = (Player) e.getWhoClicked();
			if(p.isOp() && plugin.protect == false) {
				// To co ma sie wykonac na trybie gry kreatywnym
			} else {
				// Jezeli gracz nie jest OP, oraz nie posiadam GameMode Creative
				e.setCancelled(true);
			}
		} catch(NullPointerException ex) {}
	}
	
	@EventHandler
	public void onInventoryPickupItem(InventoryPickupItemEvent e) {
		e.setCancelled(true);
	}
	
	// Dropienie itemu
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e) {
		Player p = (Player) e.getPlayer();
		if(p.isOp() && plugin.protect == false) {
			// To co ma sie wykonac na trybie gry kreatywnym
		} else {
			// Jezeli gracz nie jest OP, oraz nie posiadam GameMode Creative
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		try {
			if(e.getItem().getType() == Material.COMPASS) {
				BungeeInventory.showInventory(e.getPlayer());
			}
			// Dywan
			if(e.getClickedBlock().getType() == Material.CARPET) {
				if(e.getPlayer().isOp() && plugin.protect == true) {
					// To co ma sie wykonac na trybie gry kreatywnym
				} else {
					// Jezeli gracz nie jest OP, oraz nie posiada GameMode Creative
					e.setCancelled(true);
				}
			}
		} catch(NullPointerException ex) {}
	}
	
	// Dolaczanie na serwer
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent e) {
		e.setJoinMessage("§f<> §a" + e.getPlayer().getName() + " §adolaczyl do Lobby§f <>");
		try {
			resetPlayer(e.getPlayer());
		} catch(NoSuchMethodException ex) {} catch(NoSuchMethodError ex) {}
		
		// Fireworks
		Firework fw = (Firework) e.getPlayer().getWorld().spawn(e.getPlayer().getLocation(), Firework.class);
		
		FireworkMeta fwMeta = fw.getFireworkMeta();
		fwMeta.addEffect(FireworkEffect.builder()
						.flicker(true)
						.trail(true)
						.with(Type.CREEPER)
						.withColor(Color.GREEN)
						.withFade(Color.GREEN)
						.build());
		fwMeta.setPower(1);
		fw.setFireworkMeta(fwMeta);
		
		if(e.getPlayer().isOp() || e.getPlayer().hasPermission("essentials.gc")) {
			e.getPlayer().sendMessage(ChatColor.AQUA + "[DCLobby] Witaj " + e.getPlayer().getName() + ChatColor.AQUA + " ponownie na serwerze!");
			if(e.getPlayer().hasPermission("essentials.gc")) {
				e.getPlayer().sendMessage(ChatColor.AQUA + "[DCLobby] Obecnia ilosc TPS i statystki serwera:");
				e.getPlayer().chat("/gc");
			}
		}
	}
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent e) {
		e.setLeaveMessage("§f<> §a" + e.getPlayer().getName() + " §awyszedl z Lobby§f <>");
		
		Firework fw = (Firework) e.getPlayer().getWorld().spawn(e.getPlayer().getLocation(), Firework.class);
		
		FireworkMeta fwMeta = fw.getFireworkMeta();
		fwMeta.addEffect(FireworkEffect.builder()
						.flicker(true)
						.trail(true)
						.with(Type.CREEPER)
						.withColor(Color.RED)
						.withFade(Color.RED)
						.build());
		fwMeta.setPower(1);
		fw.setFireworkMeta(fwMeta);
	}
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent e) {
		if(plugin.prace == true) {
			if(e.getPlayer().isOp() || e.getPlayer().hasPermission("dyrtcraft.lobby.join")) {
				e.allow();
				return;
			} else {
				e.disallow(null, plugin.getKickMessage());
				return;
			}
		}
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		if(e.getPlayer().isOp() && plugin.protect == false) { return; }
		// Teleportacja na dole mapy
		if(e.getTo().getBlockY() < 60 || e.getTo().getBlockY() > 90) {
			e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
			try {
				resetPlayer(e.getPlayer());
			} catch(NoSuchMethodException ex) {} catch(NoSuchMethodError ex) {}
		}
		if(e.getTo().getBlockX() < -40 || e.getTo().getBlockX() > 40) {
			e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
			try {
				resetPlayer(e.getPlayer());
			} catch(NoSuchMethodException ex) {} catch(NoSuchMethodError ex) {}
		}
		if(e.getTo().getBlockZ() < -40 || e.getTo().getBlockZ() > 40) {
			e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
			try {
				resetPlayer(e.getPlayer());
			} catch(NoSuchMethodException ex) {} catch(NoSuchMethodError ex) {}
		}
	}
	
	// Respawn
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		if(e.getPlayer().isOp() && plugin.protect == false) { return; }
		
		try {
			resetPlayer(e.getPlayer());
		} catch (NoSuchMethodException ex) {} catch(NoSuchMethodError ex) {}
	}
	
	// Quit
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent e) {
		e.setQuitMessage("§f<> §a" + e.getPlayer().getName() + " §awyszedl z Lobby§f <>");
		
		Firework fw = (Firework) e.getPlayer().getWorld().spawn(e.getPlayer().getLocation(), Firework.class);
		
		FireworkMeta fwMeta = fw.getFireworkMeta();
		fwMeta.addEffect(FireworkEffect.builder()
						.flicker(true)
						.trail(true)
						.with(Type.CREEPER)
						.withColor(Color.RED)
						.withFade(Color.RED)
						.build());
		fwMeta.setPower(1);
		fw.setFireworkMeta(fwMeta);
	}
	
	public void resetPlayer(Player player) throws NoSuchMethodException {
		if(player.isOp()) {
			if(plugin.prace == true) {
				player.sendMessage(ChatColor.AQUA + "[DCLobby] " + ChatColor.RED + "Prace techniczne sa ustawione na \"true\"! Zmien je uzywajac /dclobby prace false");
			}
			if(plugin.protect == false) {
				player.sendMessage(ChatColor.AQUA + "[DCLobby] " + ChatColor.RED + "Ustawienia zabezpieczen sa ustawione na \"false\"! Zmien je uzywajac /dclobby protect true.");
			}
		}
		player.performCommand("spawn");
		
		player.getWorld().setStorm(false);
		player.getWorld().setThundering(false);
		player.getWorld().setTime(8);
		
		player.setAllowFlight(true);
		player.setExp(0);
		player.setGameMode(GameMode.CREATIVE);
		player.setLevel(0);
		
		player.getInventory().setHelmet(null);
		player.getInventory().setChestplate(null);
		player.getInventory().setLeggings(null);
		player.getInventory().setBoots(null);
		
		player.getInventory().clear();
		
		player.getInventory().setItem(0, Kity.compass());
		player.getInventory().setItem(1, Kity.ksiazka1());
		player.getInventory().setItem(2, Kity.ksiazka2());
		
		player.removePotionEffect(PotionEffectType.SPEED);
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
		
		Scoreboard.setScoreboard(player);
	}
	
}
