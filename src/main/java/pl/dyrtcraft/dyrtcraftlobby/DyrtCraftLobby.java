package pl.dyrtcraft.dyrtcraftlobby;

import org.bukkit.plugin.java.JavaPlugin;

public class DyrtCraftLobby extends JavaPlugin {
	
	public boolean protect;

	public void onEnable() {
		if(!(getServer().getPluginManager().isPluginEnabled("DyrtCraftXP"))) {
			getLogger().warning("[DCLobby] Do poprawnego dzialania tego pluginu potrzeby jest plugin DyrtCraftXP!");
			getLogger().warning("[DCLobby] Auto wylaczanie...");
			shutdownServer();
		}
		getCommand("dclobby").setExecutor(new pl.dyrtcraft.dyrtcraftlobby.Commands(this));
		
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
	
}
