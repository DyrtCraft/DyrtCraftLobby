package pl.dyrtcraft.dyrtcraftlobby;

import java.util.List;

public interface Plugin {
	
	List<String> getNicknames();
	
	void loadBroadcasts();
	
	void loadNicknames();
	
	void setNicknames(List<String> nicknames);
	
}
