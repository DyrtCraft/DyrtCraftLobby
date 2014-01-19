package pl.dyrtcraft.dyrtcraftlobby.versions.shot;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

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
	public int loadBroadcasts() {
		List<String> messages = new ArrayList<String>();
		int fail = 0;
		for(String message : DyrtCraftLobbyShot.get().getConfig().getStringList("messages")) {
			if(message.length() > 64) {
				fail++;
				DyrtCraftLobbyShot.get().getLogger().log(Level.WARNING, "Wiadomosc o indeksie " +
						(DyrtCraftLobbyShot.get().getConfig().getStringList("messages").indexOf(message) + 1) +
						" posiada wiecej niz 64 znaki! Oznacza to, ze zostanie ona ucieta podczas jej wyswietlania!");
			}
			message = message.replace("&", "§");
			messages.add(message);
		}
		DCLobby.getBroadcaster().setMessages(messages);
		return fail;
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
