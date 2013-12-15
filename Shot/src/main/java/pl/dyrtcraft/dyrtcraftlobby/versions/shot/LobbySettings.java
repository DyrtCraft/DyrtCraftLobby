package pl.dyrtcraft.dyrtcraftlobby.versions.shot;

import javax.annotation.Nonnull;

import pl.dyrtcraft.DyrtCraft;
import pl.dyrtcraft.dyrtcraftlobby.Setting;
import pl.dyrtcraft.dyrtcraftlobby.Settings;

public class LobbySettings implements Settings {
	
	private boolean chat = false;
	private boolean protect = true;
	private boolean whitelist = false;
	
	@Override
	public boolean getValue(@Nonnull Setting setting) {
		if(setting == Setting.CHAT) {
			return this.chat;
		}
		if(setting == Setting.PROTECT) {
			return this.protect;
		}
		if(setting == Setting.WHITELIST) {
			return this.whitelist;
		} else { return true; }
	}
	
	@Override
	public void setDefault() {
		this.chat = false;
		DyrtCraft.getUtils().sendNotify("Zmieniono ustawienie chat na false", false);
		this.protect = true;
		DyrtCraft.getUtils().sendNotify("Zmieniono ustawienie protect na true", false);
		this.whitelist = false;
		DyrtCraft.getUtils().sendNotify("Zmieniono ustawienie whitelist na false", false);
	}
	
	@Override
	public void setValue(@Nonnull Setting setting, @Nonnull boolean value) {
		if(setting == Setting.CHAT) {
			this.chat = value;
		}
		if(setting == Setting.PROTECT) {
			this.protect = value;
		}
		if(setting == Setting.WHITELIST) {
			this.whitelist = value;
		}
	}
	
}
