package pl.dyrtcraft.dyrtcraftlobby.shot;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import pl.DyrtCraft.DyrtCraftXP.api.DyrtCraftPlugin;
import pl.DyrtCraft.DyrtCraftXP.api.Kits;

import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.Cuboid;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.EntityDamageListener;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.FoodLevelChangeListener;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.InventoryListener;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.PlayerChangeServerListener;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.PlayerDropItemListener;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.PlayerInteractListener;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.PlayerJoinListener;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.PlayerKickListener;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.PlayerLoginListener;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.PlayerMoveListener;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.PlayerQuitListener;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.PlayerRespawnListener;

public class DyrtCraftLobbyShot extends JavaPlugin {
	
	private static DyrtCraftLobbyShot plugin;
	
	public static boolean protect;
	public static boolean whitelist;

	@Override
	public void onEnable() {
		getLogger().info("Uruchamianie DyrtCraftLobby \"Shot\" v" + getDescription().getVersion() + " by " + getDescription().getAuthors() + "...");
		
		if(!getServer().getPluginManager().isPluginEnabled("DyrtCraftXP")) {
			DyrtCraftLobbyShot.kickAll();
			
			plugin.getServer().getLogger().warning("\n \n \n \n \n");
			plugin.getServer().getLogger().warning("=========================");
			plugin.getServer().getLogger().warning("Wylaczanie serwera z powodu braku pluginu DyrtCraftXP!");
			plugin.getServer().getLogger().warning("=========================");
			plugin.getServer().getLogger().warning("Wylaczanie serwera z powodu braku pluginu DyrtCraftXP!");
			plugin.getServer().getLogger().warning("=========================");
			plugin.getServer().getLogger().warning("Wylaczanie serwera z powodu braku pluginu DyrtCraftXP!");
			plugin.getServer().getLogger().warning("=========================");
			plugin.getServer().getLogger().warning("\n");
			plugin.getServer().shutdown();
			return;
		}
		
		getLogger().info("Ustawianie domyslnych ustawien...");
		protect = true;
		DyrtCraftPlugin.sendMsgToOp(getServer().getConsoleSender().getName() + " zmienil ustawienia protect na true", 0);
		whitelist = false;
		DyrtCraftPlugin.sendMsgToOp(getServer().getConsoleSender().getName() + " zmienil ustawienia whitelist na false", 0);
		
		getLogger().info("Rejestrowanie listenerów...");
		getServer().getPluginManager().registerEvents(new Cuboid(this), this);
		getServer().getPluginManager().registerEvents(new EntityDamageListener(this), this);
		getServer().getPluginManager().registerEvents(new FoodLevelChangeListener(this), this);
		getServer().getPluginManager().registerEvents(new InventoryListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerChangeServerListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerDropItemListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerKickListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerLoginListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerMoveListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerRespawnListener(this), this);
		getLogger().info("Pomyslnie zarejestrowano listenery!");
		
		getCommand("dclobby").setExecutor(new DclobbyCommand(this));
	}
	
	@Override
	public void onDisable() {
		kickAll();
	}
	
	public static void kickAll() {
		try {
			for(Player player : plugin.getServer().getOnlinePlayers()) {
				player.kickPlayer(Util.kick());
			}
		} catch(NullPointerException ex) {}
	}
	
	public static void checkNotifications(Player player) {
		if(player.hasPermission("lobby.protect.set") && protect == false) {
			player.sendMessage(Util.prefix() + ChatColor.RED + "Ustawienie protect jest na \"false\"! Zmien je uzywajac /dclobby protect true");
		}
		if(player.hasPermission("lobby.whitelist.set") && whitelist == true) {
			player.sendMessage(Util.prefix() + ChatColor.RED + "Ustawienie whitelist jest na \"true\"! Zmien je uzywajac /dclobby whitelist false");
		}
	}
	
	public static void log(String msg) {
		plugin.getLogger().log(Level.INFO, msg);
	}
	
	public static void resetPlayer(Player player) {
		DyrtCraftLobbyShot.checkNotifications(player);
		
		player.performCommand("spawn");
		
		player.getWorld().setStorm(false);
		player.getWorld().setThundering(false);
		
		/*
		 * TODO: Fly off and GameMode.CREATIVE
		 */
		player.setExp(0);
		player.setGameMode(GameMode.CREATIVE);
		player.setAllowFlight(false);
		player.setLevel(0);
		
		player.getInventory().setHelmet(null);
		player.getInventory().setChestplate(null);
		player.getInventory().setLeggings(null);
		player.getInventory().setBoots(null);
		
		player.getInventory().clear();
		
		player.getInventory().setItem(0, Kits.compass());
		Kit.sendKit(player);
		
		player.removePotionEffect(PotionEffectType.SPEED);
		player.removePotionEffect(PotionEffectType.JUMP);
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
		player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 3));
		
		for(Player players : Bukkit.getOnlinePlayers()) {
			Scoreboard.setScoreboard(players);
		}
	}
	
}
