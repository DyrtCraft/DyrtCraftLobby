package pl.dyrtcraft.dyrtcraftlobby;

import java.util.List;

public interface Server {
	
	void checkFull();
	
	List<String> getMotd();
	
	List<String> getMotdNew();
	
	void kickAll();
	
}
