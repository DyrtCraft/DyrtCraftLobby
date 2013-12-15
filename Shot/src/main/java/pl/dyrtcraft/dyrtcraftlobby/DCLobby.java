package pl.dyrtcraft.dyrtcraftlobby;

import pl.dyrtcraft.dyrtcraftlobby.versions.shot.LobbyBroadcaster;
import pl.dyrtcraft.dyrtcraftlobby.versions.shot.LobbyPlayer;
import pl.dyrtcraft.dyrtcraftlobby.versions.shot.LobbyPlugin;
import pl.dyrtcraft.dyrtcraftlobby.versions.shot.LobbyServer;
import pl.dyrtcraft.dyrtcraftlobby.versions.shot.LobbySettings;

public final class DCLobby {
	
	private static Broadcaster broadcaster = new LobbyBroadcaster();
	private static Plugin plugin = new LobbyPlugin();
	private static Server server = new LobbyServer();
	private static Settings settings = new LobbySettings();
	
	public static Broadcaster getBroadcaster() {
		return broadcaster;
	}
	
	public static Player getPlayer(org.bukkit.entity.Player player) {
		return new LobbyPlayer(player);
	}
	
	public static Plugin getPlugin() {
		return plugin;
	}
	
	public static Server getServer() {
		return server;
	}
	
	public static Settings getSettings() {
		return settings;
	}
	
}
