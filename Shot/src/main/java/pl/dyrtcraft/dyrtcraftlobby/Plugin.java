package pl.dyrtcraft.dyrtcraftlobby;

import java.util.List;

public interface Plugin {
	
	List<String> getNicknames();
	
	int loadBroadcasts();
	
	void loadNicknames();
	
	void setNicknames(List<String> nicknames);
	
}
