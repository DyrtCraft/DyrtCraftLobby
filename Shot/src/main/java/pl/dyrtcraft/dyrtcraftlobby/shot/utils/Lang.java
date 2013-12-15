package pl.dyrtcraft.dyrtcraftlobby.shot.utils;

import org.bukkit.ChatColor;

import pl.dyrtcraft.dyrtcraftlobby.shot.DyrtCraftLobbyShot;

public class Lang {
	
	public static String alreadySet(boolean value) {
		String a = ChatColor.RED + "To ustawienie juz jest aktualnie na \"" + value + "\"!";
		return a;
	}
	
	public static String banned(String nickname) {
		String a = ChatColor.GOLD + "\n= = = = = = = = = = = = = = = = = = = = = = = = = =\n";
		String b = ChatColor.DARK_RED + "Twój nick (" + nickname + ") jest zablokowany!\n";
		String c = ChatColor.DARK_RED + "Nie podszywaj sie pod znane osoby!\n";
		String d = ChatColor.GOLD + "= = = = = = = = = = = = = = = = = = = = = = = = = =\n";
		String e = ChatColor.BLUE + "To Ty? Zglos sie na TeamSpeak 3 " + ChatColor.ITALIC + "dyrtcraft.pl";
		return a + b + c + d + e;
	}
	
	public static String bcPrefix() {
		String a = DyrtCraftLobbyShot.get().getConfig().getString("prefix");
		a = a.replace("&", "§");
		return a;
	}
	
	public static String chatOff() {
		String a = ChatColor.RED + "Na serwerze Lobby chat jest wylaczony!";
		return a;
	}
	
	public static String console() {
		String a = ChatColor.RED + "Nie mozesz wyslac tej wiadomosci z poziomu konsoli!";
		return a;
	}
	
	public static String error() {
		String a = ChatColor.RED + "Blad!";
		return a;
	}
	
	public static String gettingKit() {
		String a = ChatColor.GRAY + "Otrzymywanie podstawowych itemów...";
		return a;
	}
	
	public static String longNickname(String nickname) {
		String a = ChatColor.GOLD + "\n= = = = = = = = = = = = = = = = = = = = = = = = = =\n";
		String b = ChatColor.DARK_RED + "Twój nick w grze (" + nickname + ") jest za dlugi!\n";
		String c = ChatColor.DARK_RED + "Uzyj krótszego nicku!\n";
		String d = ChatColor.GOLD + "= = = = = = = = = = = = = = = = = = = = = = = = = =\n";
		String e = ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "\nDyrtCraft Network";
		return a + b + c + d + e;
	}
	
	public static String kick() {
		// TODO
		String a = "Restartowanie serwera - zaloguj sie ponownie";
		return a;
	}
	
	public static String kickMessage() {
		String a = ChatColor.GOLD + "\n= = = = = = = = = = = = = = = = = = = = = = = = = =\n";
		String b = ChatColor.DARK_RED + "Obecnie trwaja prace techniczne na serwerowni!\n";
		String c = ChatColor.DARK_RED + "Wróc pózniej!\n";
		String d = ChatColor.GOLD + "= = = = = = = = = = = = = = = = = = = = = = = = = =\n";
		String e = ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "\nDyrtCraft Network";
		return a + b + c + d + e;
	}
	
	public static String offline(String player) {
		String a = ChatColor.RED + "Gracz " + player + " nie jest obecnie online!";
		return a;
	}
	
	public static String permissions() {
		String a = ChatColor.RED + "Nie posiadasz odpowienich uprawnien.";
		return a;
	}
	
	public static String prefix() {
		String a = ChatColor.GOLD+"["+ChatColor.DARK_GREEN+""+ChatColor.BOLD+"DCLobby"+ChatColor.RESET+ChatColor.GOLD+"] "+ChatColor.RESET;
		return a;
	}
	
	public static String reset(String player) {
		String a = ChatColor.GRAY + "Resetowanie gracza " + player + ChatColor.GRAY + "...";
		return a;
	}
	
	public static String stan(String ustawienie) {
		String a = ChatColor.GRAY + "Stan ustawienia " + ustawienie + " to: ";
		return a;
	}
	
	public static String usage() {
		String a = ChatColor.GRAY + "Uzycie: ";
		return a;
	}
	
}
