package pl.dyrtcraft.dyrtcraftlobby.versions.shot;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import pl.dyrtcraft.dyrtcraftlobby.Broadcaster;
import pl.dyrtcraft.dyrtcraftlobby.DCLobby;
import pl.dyrtcraft.dyrtcraftlobby.shot.DyrtCraftLobbyShot;

public class LobbyBroadcaster implements Broadcaster, Runnable {
	
	private int index = 0;
	private int interval = 60;
	private List<String> messages = new ArrayList<String>();
	private String prefix = ChatColor.RED + "Ogloszenie: ";
	
	@Override
	public int getIndex() {
		return index;
	}
	
	@Override
	public int getInterval() {
		return interval;
	}
	
	@Override
	public List<String> getMessages() {
		return messages;
	}
	
	@Override
	public String getPrefix() {
		return prefix;
	}
	
	@Override
	public void schedule() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(DyrtCraftLobbyShot.get(), this, 20 * DCLobby.getBroadcaster().getInterval(), 20 * DCLobby.getBroadcaster().getInterval());
	}
	
	@Override
	public void setIndex(int index) {
		this.index = index;
		if(this.index >= DCLobby.getBroadcaster().getMessages().size() || this.index < 0) {
			this.index = 0;
		}
	}
	
	@Override
	public void setInterval(int interval) {
		this.interval = interval;
	}
	
	@Override
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	
	@Override
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	// Runnable
	@Override
	public void run() {
		for(Player player : Bukkit.getOnlinePlayers()) {
			String message = DCLobby.getBroadcaster().getMessages().get(getIndex());
			for(ChatColor color : ChatColor.values()) {
				message = message.replace("&" + color.getChar(), color.toString()).replace("{PLAYER}", player.getName());
			}
			player.sendMessage(DCLobby.getBroadcaster().getPrefix() + message);
		}
		setIndex(getIndex() + 1);
	}
	
}
