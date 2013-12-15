package pl.dyrtcraft.dyrtcraftlobby.shot;

import java.util.logging.Level;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.lenis0012.bukkit.factories.GhostFactory;

import pl.dyrtcraft.DyrtCraft;
import pl.dyrtcraft.dyrtcraftlobby.DCLobby;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.AsyncPlayerChatListener;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.Cuboid;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.Entity;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.FoodLevelChangeListener;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.InventoryListener;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.PlayerChangeServerListener;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.PlayerDeathListener;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.PlayerDropItemListener;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.PlayerInteractListener;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.PlayerJoinListener;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.PlayerKickListener;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.PlayerLoginListener;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.PlayerMoveListener;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.PlayerPickupItemListener;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.PlayerQuitListener;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.PlayerRespawnListener;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.PlayerToggleFlightListener;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.PotionSplashListener;
import pl.dyrtcraft.dyrtcraftlobby.shot.listeners.SignChangeListener;

public class DyrtCraftLobbyShot extends JavaPlugin {
	
	private static DyrtCraftLobbyShot plugin;
	private static GhostFactory ghost;
	
	@Override
	public void onEnable() {
		getLogger().info("Uruchamianie DyrtCraftLobby \"Shot\" v" + getDescription().getVersion() + " by " + getDescription().getAuthors() + "...");
		long time = System.currentTimeMillis();
		
		try {
			plugin = this;
			ghost = new GhostFactory(this, true);
			
			checkDyrtCraftXP();
			saveDefaultConfig();
			
			getLogger().info("Ladowanie BarAPI by confuser...");
			getServer().getPluginManager().registerEvents(new BarAPI(), this);
			getLogger().info("Zaladowano BarAPI by confuser!");
			
			getLogger().info("Ladowanie automatycznych wiadomosci...");
			DCLobby.getBroadcaster().setInterval(getConfig().getInt("interval"));
			DCLobby.getPlugin().loadBroadcasts();
			getLogger().info("Ustawiono automatyczne wiadomosci (" + DCLobby.getBroadcaster().getMessages().size() + ")!");
			
			getLogger().info("Ladowanie zablokowanych nicków uzytkowników...");
			DCLobby.getPlugin().loadNicknames();
			getLogger().info("Zaladowano zablokowane nicki uzytkowników (" + DCLobby.getPlugin().getNicknames().size() + ")!");
			
			getLogger().info("Ustawianie domyslnych ustawien...");
			DCLobby.getSettings().setDefault();
			
			getLogger().info("Rejestrowanie listenerów...");
			long listTime = System.currentTimeMillis();
			registerListeners();
			long finListTime = System.currentTimeMillis() - listTime;
			getLogger().info("Pomyslnie zarejestrowano listenery! (" + finListTime + " ms)");
			
			getLogger().info("Rejestrowanie komend...");
			getCommand("dclobby").setExecutor(new DclobbyCommand(this));
			getLogger().info("Zarejestrowano komendy! " + getDescription().getCommands());
			
			getLogger().info("Resetowanie graczy online...");
			int players = 0;
			for(Player player : Bukkit.getOnlinePlayers()) {
				DCLobby.getPlayer(player).reset();
				players = players + 1;
			}
			getLogger().info("Zresetowano wszystkich graczy online (" + players + ")!");
			
			DCLobby.getBroadcaster().schedule();
		} catch(Exception ex) {
			DCLobby.getServer().kickAll();
			
			Bukkit.getLogger().log(Level.SEVERE, "\n");
			getLogger().log(Level.SEVERE, "===============================================");
			getLogger().log(Level.SEVERE, "Wylaczanie serwera - Blad podczas uruchamiania!");
			getLogger().log(Level.SEVERE, "===============================================");
			getLogger().log(Level.SEVERE, "Wylaczanie serwera - Blad podczas uruchamiania!");
			getLogger().log(Level.SEVERE, "===============================================");
			getLogger().log(Level.SEVERE, "Wylaczanie serwera - Blad podczas uruchamiania!");
			getLogger().log(Level.SEVERE, "===============================================");
			Bukkit.getLogger().log(Level.SEVERE, "\n");
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
			
			ex.printStackTrace();
			Bukkit.shutdown();
			return;
		}
		
		long finTime = System.currentTimeMillis() - time;
		getLogger().info("DyrtCraftLobby \"Shot\" v" + getDescription().getVersion() + " by " + getDescription().getAuthors() + " zostal zaladowany! (" + finTime + " ms)");
		DyrtCraft.getUtils().sendNotify("DyrtCraftLobby \"Shot\" zostal zaladowany w " + finTime + " ms", false);
	}
	
	@Override
	public void onDisable() {
		DCLobby.getServer().kickAll();
	}
	
	public static DyrtCraftLobbyShot get() {
		return plugin;
	}
	
	public static GhostFactory getGhosts() {
		return ghost;
	}
	
	public static void log(String msg) {
		plugin.getLogger().log(Level.INFO, msg);
	}
	
	private void checkDyrtCraftXP() {
		if(Bukkit.getPluginManager().getPlugin("DyrtCraftXP") == null) {
			DCLobby.getServer().kickAll();
			
			Bukkit.getLogger().log(Level.SEVERE, "\n");
			getLogger().log(Level.SEVERE, "===============================================");
			getLogger().log(Level.SEVERE, "Wylaczanie serwera - braku pluginu DyrtCraftXP!");
			getLogger().log(Level.SEVERE, "===============================================");
			getLogger().log(Level.SEVERE, "Wylaczanie serwera - braku pluginu DyrtCraftXP!");
			getLogger().log(Level.SEVERE, "===============================================");
			getLogger().log(Level.SEVERE, "Wylaczanie serwera - braku pluginu DyrtCraftXP!");
			getLogger().log(Level.SEVERE, "===============================================");
			Bukkit.getLogger().log(Level.SEVERE, "\n");
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
			
			Bukkit.shutdown();
			return;
		}
	}
	
	private void registerListeners() {
		getServer().getPluginManager().registerEvents(new Shop(), this);
		getServer().getPluginManager().registerEvents(new AsyncPlayerChatListener(this), this);
		getServer().getPluginManager().registerEvents(new Cuboid(this), this);
		getServer().getPluginManager().registerEvents(new Entity(this), this);
		getServer().getPluginManager().registerEvents(new FoodLevelChangeListener(this), this);
		getServer().getPluginManager().registerEvents(new InventoryListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerChangeServerListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerDropItemListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerKickListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerLoginListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerMoveListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerPickupItemListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerRespawnListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerToggleFlightListener(this), this);
		getServer().getPluginManager().registerEvents(new PotionSplashListener(this), this);
		getServer().getPluginManager().registerEvents(new SignChangeListener(this), this);
	}
	
}
