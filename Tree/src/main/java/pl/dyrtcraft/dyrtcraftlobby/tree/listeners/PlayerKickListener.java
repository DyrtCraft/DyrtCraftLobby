package pl.dyrtcraft.dyrtcraftlobby.tree.listeners;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import pl.dyrtcraft.dyrtcraftlobby.tree.DyrtCraftLobbyTree;

public class PlayerKickListener implements Listener {

	DyrtCraftLobbyTree plugin;
	
	public PlayerKickListener(DyrtCraftLobbyTree dyrtCraftLobbyTree) {
		plugin = dyrtCraftLobbyTree;
	}
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent e) {
		e.setLeaveMessage("§f<> §a" + e.getPlayer().getName() + " §awyszedl z Lobby§f <>");
		
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
	}
	
}
