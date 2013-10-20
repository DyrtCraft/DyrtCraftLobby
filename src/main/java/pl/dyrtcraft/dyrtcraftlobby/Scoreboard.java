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
		
		int xp = XP.getXp(player.toString());
		ScoreboardManager sm = Bukkit.getScoreboardManager();
		org.bukkit.scoreboard.Scoreboard tablica = sm.getNewScoreboard();
		
		Objective obj = tablica.registerNewObjective("tablica","dummy");
		obj.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "DyrtCraft Network");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		// XP
		Score sc = obj.getScore(Bukkit.getOfflinePlayer("§6Ilosc XP"));
		sc.setScore(xp);
		
		player.setScoreboard(tablica);
	}
	
}
