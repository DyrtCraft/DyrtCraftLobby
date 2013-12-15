package pl.dyrtcraft.dyrtcraftlobby.shot.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import pl.dyrtcraft.DyrtCraft;
import pl.dyrtcraft.dyrtcraftlobby.DCLobby;
import pl.dyrtcraft.dyrtcraftlobby.Setting;
import pl.dyrtcraft.dyrtcraftlobby.shot.DyrtCraftLobbyShot;
import pl.dyrtcraft.dyrtcraftlobby.shot.utils.Lang;

public class PlayerLoginListener implements Listener {

	DyrtCraftLobbyShot plugin;
	
	public PlayerLoginListener(DyrtCraftLobbyShot dyrtCraftLobbyTree) {
		plugin = dyrtCraftLobbyTree;
	}
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent e) {
		// Whitelist
		if(DCLobby.getSettings().getValue(Setting.WHITELIST) == true) {
			if(e.getPlayer().isOp()) {
				e.allow();
				if(DCLobby.getPlugin().getNicknames().contains(e.getPlayer().getName().toLowerCase())) {
					DyrtCraft.getUtils().sendNotify("Znaleziono blad: Nick gracza " + e.getPlayer().getName() + " jest zablokowane, oraz jest operatorem", true);
				}
				return;
			} else {
				e.disallow(Result.KICK_WHITELIST, Lang.kickMessage());
				//DyrtCraftPlugin.sendMsgToOp(e.getPlayer().getName() + " nie polaczyl sie z serwerem: Ustawienie whitelist jest na \"true\"", 0);
				DyrtCraft.getUtils().sendNotify(e.getPlayer().getName() + " nie polaczyl sie z serwerem: Ustawienie whitelist jest na \"true\"", false);
				return;
			}
		}
		
		// Is banned
		if(DCLobby.getPlugin().getNicknames().contains(e.getPlayer().getName().toLowerCase())) {
			e.disallow(Result.KICK_BANNED, Lang.banned(e.getPlayer().getName()));
			DyrtCraft.getUtils().sendNotify(e.getPlayer().getName()	 + " chcial sie zalogowac (ten nick jest zablokowany)", false);
		}
		
		// Nickname > 14
		if(e.getPlayer().getName().length() > 14) {
			e.disallow(null, Lang.longNickname(e.getPlayer().getName()));
			//DyrtCraftPlugin.sendMsgToOp(e.getPlayer().getName() + " przekroczyl maksymalna dlugosc nicku (" + e.getPlayer().getName().length() + ")", 0);
			DyrtCraft.getUtils().sendNotify(e.getPlayer().getName() + " przekroczyl maksymalna dlugosc nicku (" + e.getPlayer().getName().length() + "/14)", false);
			return;
		}
	}
	
}
