package pl.dyrtcraft.dyrtcraftlobby;

import javax.annotation.Nonnull;

public interface Settings {
	
	boolean getValue(@Nonnull Setting setting);
	
	void setDefault();
	
	void setValue(@Nonnull Setting setting, @Nonnull boolean value);
	
}
