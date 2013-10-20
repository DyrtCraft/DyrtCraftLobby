package pl.dyrtcraft.dyrtcraftlobby;

import org.bukkit.plugin.java.JavaPlugin;

public class DyrtCraftLobby extends JavaPlugin {

	public void onEnable() {
		if(!(getServer().getPluginManager().isPluginEnabled("DyrtCraftXP"))) {
			getLogger().warning("Do poprawnego dzialania tego pluginu potrzeby jest plugin DyrtCraftXP!");
			getLogger().warning("Auto wylaczanie...");
			shutdownServer();
		}		
		getCommand("dclobby").setExecutor(new pl.dyrtcraft.dyrtcraftlobby.Commands(this));
		
		getServer().getPluginManager().registerEvents(new pl.dyrtcraft.dyrtcraftlobby.Listeners(this), this);
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
