package pl.dyrtcraft.dyrtcraftlobby.shot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.ScoreboardManager;

import pl.dyrtcraft.DyrtCraft;

public class Scoreboard {

	DyrtCraftLobbyShot plugin;
	
	public Scoreboard(DyrtCraftLobbyShot dyrtCraftLobbyTree) {
		plugin = dyrtCraftLobbyTree;
	}
	
	public static void setScoreboard(Player player) {
		player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		
		ScoreboardManager sm = Bukkit.getScoreboardManager();
		org.bukkit.scoreboard.Scoreboard tablica = sm.getNewScoreboard();
		
		Objective obj = tablica.registerNewObjective("tablicaxp","dummy");
		obj.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "DyrtCraft Network");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		// XP
		Score sc = obj.getScore(Bukkit.getOfflinePlayer("§6Twoja ilosc XP"));
		sc.setScore(getXP(player));
		
		player.setScoreboard(tablica);
	}
	
	public static int getXP(Player player) {
		try {
			int xp = DyrtCraft.getMember(player).getXp();
			return xp;
		} catch(Exception ex) {
			return -1;
		}
	}
	
}
