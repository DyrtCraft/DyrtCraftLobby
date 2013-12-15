package pl.dyrtcraft.dyrtcraftlobby.versions.shot;

import java.util.ArrayList;
import java.util.List;

import pl.dyrtcraft.dyrtcraftlobby.DCLobby;
import pl.dyrtcraft.dyrtcraftlobby.Plugin;
import pl.dyrtcraft.dyrtcraftlobby.shot.DyrtCraftLobbyShot;

public class LobbyPlugin implements Plugin {
	
	private List<String> nicknames = new ArrayList<String>();
	
	@Override
	public List<String> getNicknames() {
		return nicknames;
	}
	
	@Override
	public void loadBroadcasts() {
		List<String> messages = new ArrayList<String>();
		for(String message : DyrtCraftLobbyShot.get().getConfig().getStringList("messages")) {
			message = message.replace("&", "§");
			messages.add(message);
		}
		DCLobby.getBroadcaster().setMessages(messages);
	}
	
	@Override
	public void loadNicknames() {
		List<String> nicknames = new ArrayList<String>();
		for(String nickname : DyrtCraftLobbyShot.get().getConfig().getStringList("banned-nicknames")) {
			nicknames.add(nickname.toLowerCase());
		}
		this.setNicknames(nicknames);
	}
	
	@Override
	public void setNicknames(List<String> nicknames) {
		this.nicknames = nicknames;
	}
	
}
