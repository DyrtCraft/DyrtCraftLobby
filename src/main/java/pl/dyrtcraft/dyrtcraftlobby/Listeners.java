package pl.dyrtcraft.dyrtcraftlobby;

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
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import pl.DyrtCraft.DyrtCraftXP.api.BungeeInventory;

public class Listeners implements Listener {

	DyrtCraftLobby plugin;
	
	public Listeners(DyrtCraftLobby dyrtCraftLobby) {
		plugin = dyrtCraftLobby;
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		e.setCancelled(true);
	}
	
	// Niszczenie blocku (cuboid)
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = (Player) e.getPlayer();
		if(p.getGameMode() == GameMode.CREATIVE && p.isOp()) {
			// To co ma sie wykonac na trybie gry kreatywnym
		} else {
			// Jezeli gracz nie jest OP, oraz nie posiadam GameMode Creative
			e.setCancelled(true);
		}
	}
	
	// Stawianie bloku (cuboid)
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Player p = (Player) e.getPlayer();
		if(p.getGameMode() == GameMode.CREATIVE && p.isOp()) {
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
			if(p.getGameMode() == GameMode.CREATIVE && p.isOp()) {
				// To co ma sie wykonac na trybie gry kreatywnym
			} else {
				// Jezeli gracz nie jest OP, oraz nie posiadam GameMode Creative
				e.setCancelled(true);
			}
		} catch(NullPointerException ex) {}
	}
	
	// Dropienie itemu
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e) {
		Player p = (Player) e.getPlayer();
		if(p.getGameMode() == GameMode.CREATIVE && p.isOp()) {
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
				if(e.getPlayer().getGameMode() == GameMode.CREATIVE && e.getPlayer().isOp()) {
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
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		// Teleportacja na dole mapy
		if(e.getTo().getBlockY() < 60) {
			e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
			try {
				resetPlayer(e.getPlayer());
			} catch(NoSuchMethodException ex) {} catch(NoSuchMethodError ex) {}
		}
		// Teleportacja na gorze mapy
		if(e.getTo().getBlockY() > 90) {
			e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
			try {
				resetPlayer(e.getPlayer());
			} catch(NoSuchMethodException ex) {} catch(NoSuchMethodError ex) {}
		}
	}
	
	// Respawn
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
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
	
	// Pogoda
	public void onWeatherChange(WeatherChangeEvent e) {
		e.getWorld().setWeatherDuration(0);
		e.setCancelled(true);
	}
	
	public static void resetPlayer(Player player) throws NoSuchMethodException {
		player.performCommand("spawn");
		
		player.setAllowFlight(true);
		player.setExp(0);
		player.setFoodLevel(20);
		player.setGameMode(GameMode.ADVENTURE);
		player.setHealth(20.0);
		player.setLevel(0);
		
		player.getInventory().setHelmet(null);
		player.getInventory().setChestplate(null);
		player.getInventory().setLeggings(null);
		player.getInventory().setBoots(null);
		
		player.getInventory().clear();
		
		player.getInventory().addItem(Kity.compass());
		player.getInventory().addItem(Kity.ksiazka1());
		player.getInventory().addItem(Kity.ksiazka2());
		
		player.removePotionEffect(PotionEffectType.SPEED);
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
		
		Scoreboard.setScoreboard(player);
	}
	
}
