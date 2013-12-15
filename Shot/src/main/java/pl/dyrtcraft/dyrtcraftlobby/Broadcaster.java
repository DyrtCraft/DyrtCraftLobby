package pl.dyrtcraft.dyrtcraftlobby;

import java.util.List;

public interface Broadcaster {
	
	int getIndex();
	
	int getInterval();
	
	List<String> getMessages();
	
	String getPrefix();
	
	void schedule();
	
	void setIndex(int index);
	
	void setInterval(int interval);
	
	void setMessages(List<String> messages);
	
	void setPrefix(String prefix);
	
}
