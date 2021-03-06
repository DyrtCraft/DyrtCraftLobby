package pl.dyrtcraft.dyrtcraftlobby;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.ScoreboardManager;

import pl.DyrtCraft.DyrtCraftXP.api.XP;

public class Scoreboard {

	DyrtCraftLobby plugin;
	
	public Scoreboard(DyrtCraftLobby dyrtCraftLobby) {
		plugin = dyrtCraftLobby;
	}
	
	public static void setScoreboard(Player player) {
		player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		
		ScoreboardManager sm = Bukkit.getScoreboardManager();
		org.bukkit.scoreboard.Scoreboard tablica = sm.getNewScoreboard();
		
		Objective obj = tablica.registerNewObjective("tablicaxp","dummy");
		obj.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "DyrtCraft Netowork");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		// XP
		Score sc = obj.getScore(Bukkit.getOfflinePlayer("�6Ilosc XP"));
		sc.setScore(getXP(player));
		
		// Ilosc graczy na serwerze
		Score sc2 = obj.getScore(Bukkit.getOfflinePlayer("�6Ilosc graczy"));
		sc2.setScore(Bukkit.getOfflinePlayers().length);
		
		player.setScoreboard(tablica);
	}
	
	public static int getXP(Player player) {
		try {
			int xp = XP.getXp(player.getName());
			return xp;
		} catch(Exception ex) {
			return -1;
		}
	}
	
}
