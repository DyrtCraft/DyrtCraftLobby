package pl.dyrtcraft.dyrtcraftlobby.versions.shot;

import me.confuser.barapi.BarAPI;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import pl.dyrtcraft.DyrtCraft;
import pl.dyrtcraft.dyrtcraftlobby.DCLobby;
import pl.dyrtcraft.dyrtcraftlobby.Player;
import pl.dyrtcraft.dyrtcraftlobby.Setting;
import pl.dyrtcraft.dyrtcraftlobby.shot.DyrtCraftLobbyShot;
import pl.dyrtcraft.dyrtcraftlobby.shot.Kit;
import pl.dyrtcraft.dyrtcraftlobby.shot.utils.Lang;

public class LobbyPlayer implements Player {
	
	org.bukkit.entity.Player player;
	
	public LobbyPlayer(org.bukkit.entity.Player player) {
		this.player = player;
	}
	
	@Override
	public void checkNotify() {
		if(player.isOp() && DCLobby.getSettings().getValue(Setting.CHAT) == true) {
			player.sendMessage(Lang.prefix() + ChatColor.RED + "Ustawienie chat jest na \"true\"! Zmien je uzywajac /dclobby chat false");
		}
		if(player.isOp() && DCLobby.getSettings().getValue(Setting.PROTECT) == false) {
			player.sendMessage(Lang.prefix() + ChatColor.RED + "Ustawienie protect jest na \"false\"! Zmien je uzywajac /dclobby protect true");
		}
		if(player.isOp() && DCLobby.getSettings().getValue(Setting.WHITELIST) == true) {
			player.sendMessage(Lang.prefix() + ChatColor.RED + "Ustawienie whitelist jest na \"true\"! Zmien je uzywajac /dclobby whitelist false");
		}
	}
	
	@Override
	public void reset() {
		// Sprawdz powiadomienia
		DCLobby.getPlayer(player).checkNotify();
		DCLobby.getServer().checkFull();
		
		// Permissions
		if(!player.isOp()) {
			player.addAttachment(DyrtCraftLobbyShot.get(), "bukkit.command.me", false);
		}
		
		// Teleport na spawn
		player.teleport(new Location(player.getWorld(), 0.5, 64.0, 0.5));
		
		// Pogoda
		player.getWorld().setStorm(false);
		player.getWorld().setThundering(false);
		
		// Reset gm, exp
		player.setExp(0);
		player.setGameMode(GameMode.CREATIVE);
		player.setAllowFlight(true);
		player.setLevel(0);
		
		// Clear aromor
		player.getInventory().setHelmet(null);
		player.getInventory().setChestplate(null);
		player.getInventory().setLeggings(null);
		player.getInventory().setBoots(null);
		
		// Clear inventory
		player.getInventory().clear();
		
		// Items
		player.getInventory().setItem(0, DyrtCraft.getKits().getServerChooserItem());
		Kit.sendKit(player);
		
		// Usun potions
		player.removePotionEffect(PotionEffectType.SPEED);
		player.removePotionEffect(PotionEffectType.JUMP);
		
		// Daj potions
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
		player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 2));
		
		// GHOST!
		DyrtCraftLobbyShot.getGhosts().setGhost(player, true);
		
		// Tab list
		if(player.isOp()) {
			player.setPlayerListName("§c" + player.getName());
			player.chat("/tps");
		} else {
			player.setPlayerListName("§6" + player.getName());
		}
		
		// Motd
		if(!player.isOp()) {
			player.sendMessage("\n\n\n\n\n\n\n");
		}
		if(player.hasPlayedBefore()) {
			for(String motd : DCLobby.getServer().getMotd()) {
				for(ChatColor color : ChatColor.values()) {
					motd = motd.replace("&" + color.getChar(), color.toString()).replace("{PLAYER}", player.getName());
				}
				player.sendMessage(motd);
			}
		} else {
			for(String motd : DCLobby.getServer().getMotdNew()) {
				for(ChatColor color : ChatColor.values()) {
					motd = motd.replace("&" + color.getChar(), color.toString()).replace("{PLAYER}", player.getName());
				}
				player.sendMessage(motd);
			}
		}
		
		// Bar
		BarAPI.setMessage(player, ChatColor.GOLD + "" + ChatColor.BOLD + "Witaj " + player.getName() + " na DyrtCraft Network!");
	}
	
}
