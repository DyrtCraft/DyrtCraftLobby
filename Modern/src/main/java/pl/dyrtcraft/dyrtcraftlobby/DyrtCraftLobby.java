package pl.dyrtcraft.dyrtcraftlobby;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class DyrtCraftLobby extends JavaPlugin {
	
	public boolean prace;
	public boolean protect;

	public void onEnable() {
		if(!(getServer().getPluginManager().isPluginEnabled("DyrtCraftXP"))) {
			getLogger().warning("[DCLobby] Do poprawnego dzialania tego pluginu potrzeby jest plugin DyrtCraftXP!");
			getLogger().warning("[DCLobby] Auto wylaczanie...");
			shutdownServer();
		}
		getCommand("dclobby").setExecutor(new pl.dyrtcraft.dyrtcraftlobby.Commands(this));
		
		getServer().getLogger().info("[DCLobby] Zdejmowanie prac technicznych...");
		prace = false;
		getServer().getLogger().info("[DCLobby] Prace techniczne zdjete!");
		
		getServer().getLogger().info("[DCLobby] Zabezpieczanie terenu...");
		protect = true;
		getServer().getLogger().info("[DCLobby] Teren zabezpieczony!");
		
		getServer().getLogger().info("[DCLobby] Rejestrowanie listenerów (sluchaczy)...");
		getServer().getPluginManager().registerEvents(new pl.dyrtcraft.dyrtcraftlobby.Listeners(this), this);
		getServer().getLogger().info("[DCLobby] Zarejestowano listenery (sluchacze)!");
	}
	
	private void shutdownServer() {
		getServer().getLogger().warning("\n \n \n \n \n");
		getServer().getLogger().warning("=========================");
		getServer().getLogger().warning("Wylaczanie serwera z powodu braku pluginu DyrtCraftXP!");
		getServer().getLogger().warning("=========================");
		getServer().getLogger().warning("Wylaczanie serwera z powodu braku pluginu DyrtCraftXP!");
		getServer().getLogger().warning("=========================");
		getServer().getLogger().warning("Wylaczanie serwera z powodu braku pluginu DyrtCraftXP!");
		getServer().getLogger().warning("=========================");
		getServer().getLogger().warning("\n");
		getServer().shutdown();
	}
	
	public String getKickMessage() {
		String a = ChatColor.GOLD + "\n= = = = = = = = = = = = = = = = = = = = = = = = = =\n";
		String b = ChatColor.DARK_RED + "Obecnie trwaja prace techniczne na serwerowni!\n";
		String c = ChatColor.DARK_RED + "Wróc pózniej!\n";
		String d = ChatColor.GOLD + "= = = = = = = = = = = = = = = = = = = = = = = = = =\n";
		String e = ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "\nDyrtCraft Network";
		return a + b + c + d + e;
	}
	
}
