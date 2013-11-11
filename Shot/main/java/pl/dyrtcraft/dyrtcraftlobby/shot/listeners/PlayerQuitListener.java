package pl.dyrtcraft.dyrtcraftlobby.shot.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import pl.dyrtcraft.dyrtcraftlobby.shot.DyrtCraftLobbyShot;
import pl.dyrtcraft.dyrtcraftlobby.shot.Scoreboard;

public class PlayerQuitListener implements Listener {

	DyrtCraftLobbyShot plugin;
	
	public PlayerQuitListener(DyrtCraftLobbyShot dyrtCraftLobbyTree) {
		plugin = dyrtCraftLobbyTree;
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		//e.setQuitMessage("§f<> §a" + e.getPlayer().getName() + " §awyszedl z Lobby§f <>");
		e.setQuitMessage(null);
		
		Firework fw = (Firework) e.getPlayer().getWorld().spawn(e.getPlayer().getLocation(), Firework.class);
		
		FireworkMeta fwMeta = fw.getFireworkMeta();
		fwMeta.addEffect(FireworkEffect.builder()
						.flicker(true)
						.trail(true)
						.with(Type.CREEPER)
						.withColor(Color.GREEN)
						.withFade(Color.ORANGE)
						.build());
		fwMeta.setPower(1);
		fw.setFireworkMeta(fwMeta);
		
		for(Player players : Bukkit.getOnlinePlayers()) {
			Scoreboard.setScoreboard(players);
		}
	}
	
}
