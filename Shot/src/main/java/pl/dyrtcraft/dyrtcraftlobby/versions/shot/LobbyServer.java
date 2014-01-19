package pl.dyrtcraft.dyrtcraftlobby.versions.shot;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pl.dyrtcraft.DyrtCraft;
import pl.dyrtcraft.dyrtcraftlobby.Server;
import pl.dyrtcraft.dyrtcraftlobby.shot.DyrtCraftLobbyShot;
import pl.dyrtcraft.dyrtcraftlobby.shot.utils.Lang;

public class LobbyServer implements Server {
	
	@Override
	public void checkFull() {
		if(Bukkit.getOnlinePlayers().length >= Bukkit.getMaxPlayers() - 20) {
			DyrtCraft.getUtils().sendNotify("Serwer sie zapelnia! (" + Bukkit.getOnlinePlayers().length + "/" + Bukkit.getMaxPlayers() + ")", false);
		}
		if(Bukkit.getOnlinePlayers().length >= Bukkit.getMaxPlayers() - 10) {
			DyrtCraft.getUtils().sendNotify("Serwer jest prawie pelny! (" + Bukkit.getOnlinePlayers().length + "/" + Bukkit.getMaxPlayers() + ")", true);
		}
		if(Bukkit.getOnlinePlayers().length >= Bukkit.getMaxPlayers()) {
			DyrtCraft.getUtils().sendNotify("Serwer jest pelny! (" + Bukkit.getOnlinePlayers().length + "/" + Bukkit.getMaxPlayers() + ")", true);
		}
	}
	
	@Override
	public List<String> getMotd() {
		return DyrtCraftLobbyShot.get().getConfig().getStringList("motd.played");
	}
	
	@Override
	public List<String> getMotdNew() {
		return DyrtCraftLobbyShot.get().getConfig().getStringList("motd.new");
	}
	
	@Override
	public void kickAll() {
		for(Player player : Bukkit.getOnlinePlayers()) {
			player.kickPlayer(Lang.kick());
		}
	}
	
}
