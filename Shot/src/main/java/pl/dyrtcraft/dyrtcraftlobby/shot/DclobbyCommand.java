package pl.dyrtcraft.dyrtcraftlobby.shot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.dyrtcraft.DyrtCraft;
import pl.dyrtcraft.dyrtcraftlobby.DCLobby;
import pl.dyrtcraft.dyrtcraftlobby.Setting;
import pl.dyrtcraft.dyrtcraftlobby.shot.utils.Lang;

public class DclobbyCommand implements CommandExecutor {

	DyrtCraftLobbyShot plugin;
	
	public DclobbyCommand(DyrtCraftLobbyShot dyrtCraftLobbyTree) {
		plugin = dyrtCraftLobbyTree;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("dclobby")) {
			if(args.length == 0) {
				return erArg(sender, "Zbyt malo argumentów");
			}
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("about") || args[0].equalsIgnoreCase("info") || args[0].equalsIgnoreCase("version")) {
					return aboutArg(sender);
				}
				if(args[0].equalsIgnoreCase("ban")) {
					banManage(sender);
					return true;
				}
				if(args[0].equalsIgnoreCase("chat")) {
					return chatArg(sender, null);
				}
				if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("pomoc") || args[0].equalsIgnoreCase("?")) {
					return helpArg(sender);
				}
				if(args[0].equalsIgnoreCase("kit")) {
					return kitArg(sender);
				}
				if(args[0].equalsIgnoreCase("protect") || args[0].equalsIgnoreCase("protected")) {
					return protectArg(sender, null);
				}
				if(args[0].equalsIgnoreCase("reload")) {
					return reloadArg(sender);
				}
				if(args[0].equalsIgnoreCase("reset")) {
					return resetArg(sender.getName(), sender);
				}
				if(args[0].equalsIgnoreCase("whitelist")) {
					return whitelist(sender, null);
				} else {
					return erArg(sender, "Podano bledny argument");
				}
			}
			if(args.length == 2) {
				if(args[0].equalsIgnoreCase("ban")) {
					if(args[1].equalsIgnoreCase("add") || args[1].equalsIgnoreCase("del") || args[1].equalsIgnoreCase("remove")) {
						return erArg(sender, "Nie podano nicku");
					}
					if(args[1].equalsIgnoreCase("manage")) {
						return banManage(sender);
					} else {
						return erArg(sender, "Podano bledny argument");
					}
				}
				if(args[0].equalsIgnoreCase("chat")) {
					return chatArg(sender, args[1]);
				}
				if(args[0].equalsIgnoreCase("protect") || args[0].equalsIgnoreCase("protected")) {
					return protectArg(sender, args[1]);
				}
				if(args[0].equalsIgnoreCase("reset")) {
					return resetArg(args[1], sender);
				}
				if(args[0].equalsIgnoreCase("whitelist")) {
					return whitelist(sender, args[1]);
				} else {
					return erArg(sender, "Podano bledny argument");
				}
			}
			if(args.length == 3) {
				if(args[0].equalsIgnoreCase("ban")) {
					if(args[1].equalsIgnoreCase("add")) {
						return banAdd(sender, args[2].toLowerCase());
					}
					if(args[1].equalsIgnoreCase("del") || args[1].equalsIgnoreCase("remove")) {
						return banDel(sender, args[2].toLowerCase());
					} else {
						return erArg(sender, "Podano bledny argument");
					}
				} else {
					return erArg(sender, "Podano bledny argument");
				}
			} else {
				return erArg(sender, "Zbyt duzo argumentów");
			}
		}
		return false;
	}
	
	private boolean erArg(CommandSender sender, String reason) {
		sender.sendMessage(Lang.prefix() + Lang.error() + " " + reason + "!");
		sender.sendMessage(Lang.usage() + plugin.getCommand("dclobby").getUsage());
		return true;
	}
	
	private boolean aboutArg(CommandSender sender) {
		sender.sendMessage(ChatColor.DARK_GREEN + " =============== DyrtCraftLobby Shot =============== ");
		sender.sendMessage(ChatColor.DARK_GREEN + "Wersja: " + ChatColor.GRAY + plugin.getDescription().getVersion());
		sender.sendMessage(ChatColor.DARK_GREEN + "Nazwa kodowa: " + ChatColor.GRAY + "Shot");
		sender.sendMessage(ChatColor.DARK_GREEN + "Autor: " + ChatColor.GRAY + "TheMolkaPL");
		sender.sendMessage(ChatColor.DARK_GREEN + "GitHub: " + ChatColor.GRAY + "https://github.com/DyrtCraft/DyrtCraftLobby");
		return true;
	}
	
	private boolean banAdd(CommandSender sender, String nick) {
		if(!sender.isOp()) {
			sender.sendMessage(Lang.prefix() + Lang.permissions());
			return true;
		}
		
		if(DCLobby.getPlugin().getNicknames().contains(sender.getName())) {
			sender.sendMessage(Lang.prefix() + ChatColor.RED + "Nie mozesz zbanowac samego siebie!");
			return true;
		}
		if(DCLobby.getPlugin().getNicknames().contains(nick)) {
			sender.sendMessage(Lang.prefix() + ChatColor.RED + "Nick " + nick + " juz jest zablokowany!");
			return true;
		}
		DCLobby.getPlugin().getNicknames().add(nick);
		plugin.getConfig().set("banned-nicknames", plugin.getConfig().getStringList("banned-nicknames").add(nick));
		plugin.saveConfig();
		plugin.reloadConfig();
		sender.sendMessage(Lang.prefix() + ChatColor.DARK_GREEN + "Pomyslnie zablokowano nick " + nick + "!");
		DyrtCraft.getUtils().sendNotify(sender.getName() + " zablokowal nick " + nick, false);
		return true;
	}
	
	private boolean banDel(CommandSender sender, String nick) {
		if(!sender.isOp()) {
			sender.sendMessage(Lang.prefix() + Lang.permissions());
			return true;
		}
		
		if(!DCLobby.getPlugin().getNicknames().contains(nick)) {
			sender.sendMessage(Lang.prefix() + ChatColor.RED + "Nick " + nick + " nie jest zablokowany!");
			return true;
		}
		DCLobby.getPlugin().getNicknames().remove(nick);
		plugin.getConfig().set("banned-nicknames", plugin.getConfig().getStringList("banned-nicknames").remove(nick));
		plugin.saveConfig();
		plugin.reloadConfig();
		sender.sendMessage(Lang.prefix() + ChatColor.DARK_GREEN + "Pomyslnie odblokowano nick " + nick + "!");
		DyrtCraft.getUtils().sendNotify(sender.getName() + " odblokowal nick " + nick, false);
		return true;
	}
	
	private boolean banManage(CommandSender sender) {
		if(!sender.isOp()) {
			sender.sendMessage(Lang.prefix() + Lang.permissions());
			return true;
		}
		
		StringBuilder str = new StringBuilder();
		for(String nick : DCLobby.getPlugin().getNicknames()) {
			str.append(ChatColor.GOLD + nick);
			str.append(ChatColor.GRAY + ", ");
		}
		sender.sendMessage(ChatColor.DARK_GREEN + " ======== Lista zablokowanych nicków (" + DCLobby.getPlugin().getNicknames().size() + ") ======== ");
		sender.sendMessage(str.toString());
		return true;
	}
	
	private boolean chatArg(CommandSender sender, String arg) {
		if(arg == null) {
			if(!sender.isOp()) {
				sender.sendMessage(Lang.prefix() + Lang.permissions());
				return true;
			}
			sender.sendMessage(Lang.prefix() + Lang.stan("chat") + DCLobby.getSettings().getValue(Setting.CHAT) + ".");
			return true;
		}
		if(!sender.isOp()) {
			sender.sendMessage(Lang.prefix() + Lang.permissions());
			return true;
		}
		
		if(arg.equalsIgnoreCase("true") || arg.equalsIgnoreCase("allow") || arg.equalsIgnoreCase("on")) {
			if(DCLobby.getSettings().getValue(Setting.CHAT) == true) {
				sender.sendMessage(Lang.prefix() + Lang.alreadySet(true));
				return true;
			}
			DCLobby.getSettings().setValue(Setting.CHAT, true);
			DyrtCraft.getUtils().sendNotify(sender.getName() + " zmienil ustawienie chat na true", false);
			return true;
		}
		if(arg.equalsIgnoreCase("false") || arg.equalsIgnoreCase("deny") || arg.equalsIgnoreCase("off")) {
			if(DCLobby.getSettings().getValue(Setting.CHAT) == false) {
				sender.sendMessage(Lang.prefix() + Lang.alreadySet(false));
				return true;
			}
			DCLobby.getSettings().setValue(Setting.CHAT, false);
			DyrtCraft.getUtils().sendNotify(sender.getName() + " zmienil ustawienie chat na false", false);
			return true;
		} else {
			sender.sendMessage(Lang.prefix() + Lang.error() + " Podano nieprawidlowy argument!");
			sender.sendMessage(Lang.usage() + "/dclobby chat <false|true>");
			return true;
		}
	}
	
	private boolean helpArg(CommandSender sender) {
		sender.sendMessage(ChatColor.DARK_GREEN + " ========== DyrtCraftLobby Shot Pomoc ========== ");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.ITALIC + "/dclobby about " + ChatColor.RESET + ChatColor.GOLD + "- Informacje o pluginie");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.ITALIC + "/dclobby ban add <nick> " + ChatColor.RESET + ChatColor.GOLD + "- Dodaj nick do zbanowanych");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.ITALIC + "/dclobby ban del <nick> " + ChatColor.RESET + ChatColor.GOLD + "- Usun nick ze zbanowanych");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.ITALIC + "/dclobby ban manage " + ChatColor.RESET + ChatColor.GOLD + "- Lista graczy zbanowanych");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.ITALIC + "/dclobby chat [false|true] " + ChatColor.RESET + ChatColor.GOLD + "- Ustaw dostepnosc chatu dla graczy");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.ITALIC + "/dclobby kit " + ChatColor.RESET + ChatColor.GOLD + "- Otrzymaj domyslne itemy");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.ITALIC + "/dclobby protect [false|true] " + ChatColor.RESET + ChatColor.GOLD + "- Ustaw cuboid");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.ITALIC + "/dclobby reload " + ChatColor.RESET + ChatColor.GOLD + "- Przeladuj ustawienia pluginu");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.ITALIC + "/dclobby reset [-a|player] " + ChatColor.RESET + ChatColor.GOLD + "- Zresetuj siebie lub gracza");
		sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.ITALIC + "/dclobby whitelist [false|true] " + ChatColor.RESET + ChatColor.GOLD + "- Ustaw dostepnosc serwera");
		return true;
	}
	
	private boolean kitArg(CommandSender sender) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Lang.prefix() + Lang.console());
			return true;
		}
		sender.sendMessage(Lang.prefix() + Lang.gettingKit());
		((Player) sender).getInventory().setItem(0, DyrtCraft.getKits().getServerChooserItem());
		Kit.sendKit((Player) sender);
		return true;
	}
	
	private boolean protectArg(CommandSender sender, String arg) {
		if(arg == null) {
			if(!sender.isOp()) {
				sender.sendMessage(Lang.prefix() + Lang.permissions());
				return true;
			}
			sender.sendMessage(Lang.prefix() + Lang.stan("protect") + DCLobby.getSettings().getValue(Setting.PROTECT) + ".");
			return true;
		}
		if(!sender.isOp()) {
			sender.sendMessage(Lang.prefix() + Lang.permissions());
			return true;
		}
		
		if(arg.equalsIgnoreCase("true") || arg.equalsIgnoreCase("allow") || arg.equalsIgnoreCase("on")) {
			if(DCLobby.getSettings().getValue(Setting.PROTECT) == true) {
				sender.sendMessage(Lang.prefix() + Lang.alreadySet(true));
				return true;
			}
			DCLobby.getSettings().setValue(Setting.PROTECT, true);
			DyrtCraft.getUtils().sendNotify(sender.getName() + " zmienil ustawienie protect na true", false);
			return true;
		}
		if(arg.equalsIgnoreCase("false") || arg.equalsIgnoreCase("deny") || arg.equalsIgnoreCase("off")) {
			if(DCLobby.getSettings().getValue(Setting.PROTECT) == false) {
				sender.sendMessage(Lang.prefix() + Lang.alreadySet(false));
				return true;
			}
			DCLobby.getSettings().setValue(Setting.PROTECT, false);
			DyrtCraft.getUtils().sendNotify(sender.getName() + " zmienil ustawienie protect na false", false);
			return true;
		} else {
			sender.sendMessage(Lang.prefix() + Lang.error() + " Podano nieprawidlowy argument!");
			sender.sendMessage(Lang.usage() + "/dclobby protect <false|true>");
			return true;
		}
	}
	
	private boolean reloadArg(CommandSender sender) {
		if(!sender.isOp()) {
			sender.sendMessage(Lang.prefix() + Lang.permissions());
			return true;
		}
		
		sender.sendMessage(Lang.prefix() + "Przeladowywanie pliku config.yml...");
		try {
			plugin.reloadConfig();
		} catch(Exception ex) {
			sender.sendMessage(Lang.prefix() + ChatColor.RED + "Wystapil blad podczas przeladowywania pliku config.yml! Zobacz konsole");
			DyrtCraft.getUtils().sendNotify(sender.getName() + " przeladowal config.yml (wystapil blad)", true);
			ex.printStackTrace();
			return true;
		}
		sender.sendMessage(Lang.prefix() + ChatColor.DARK_GREEN + "Pomyslnie przeladowano plik config.yml!");
		DyrtCraft.getUtils().sendNotify(sender.getName() + " przeladowal config.yml (pomyslnie)", false);
		
		DCLobby.getPlugin().loadBroadcasts();
		DCLobby.getPlugin().loadNicknames();
		
		DCLobby.getSettings().setDefault();
		return true;
	}
	
	private boolean resetArg(String reset, CommandSender sender) {
		if(reset.equalsIgnoreCase("CONSOLE")) {
			sender.sendMessage(Lang.prefix() + ChatColor.RED + "Nie mozna zresetowac konsoli!");
			return true;
		}
		Player player = null;
		try {
			player = Bukkit.getServer().getPlayer(reset);
		} catch(NullPointerException ex) {
			sender.sendMessage(Lang.prefix() + Lang.console());
			return true;
		}
		if(reset.equalsIgnoreCase(sender.getName())) {
			// Molek tu byl xD
			if(!sender.isOp()) {
				sender.sendMessage(Lang.prefix() + Lang.permissions());
				return true;
			}
			sender.sendMessage(Lang.prefix() + Lang.reset(reset));
			DCLobby.getPlayer(player).reset();
			return true;
		}
		if(!sender.isOp()) {
			sender.sendMessage(Lang.prefix() + Lang.permissions());
			return true;
		}
		if(reset.equalsIgnoreCase("-a") || reset.equalsIgnoreCase("-ALL")) {
			sender.sendMessage(Lang.prefix() + ChatColor.GRAY + "Resetowanie wszystkich graczy...");
			int res = 0;
			for(Player pl : Bukkit.getOnlinePlayers()) {
				DCLobby.getPlayer(pl).reset();
				res = res + 1;
			}
			sender.sendMessage(Lang.prefix() + ChatColor.DARK_GREEN + "Zresetowano wszystkich graczy online (" + res + ")!");
			return true;
		}
		if(player == null) {
			sender.sendMessage(Lang.prefix() + Lang.offline(reset));
        	return true;
        }
		sender.sendMessage(Lang.prefix() + Lang.reset(reset));
		DCLobby.getPlayer(player).reset();
		return true;
	}
	
	private boolean whitelist(CommandSender sender, String arg) {
		if(arg == null) {
			if(!sender.isOp()) {
				sender.sendMessage(Lang.prefix() + Lang.permissions());
				return true;
			}
			sender.sendMessage(Lang.prefix() + Lang.stan("whitelist") + DCLobby.getSettings().getValue(Setting.WHITELIST) + ".");
			return true;
		}
		if(!sender.isOp()) {
			sender.sendMessage(Lang.prefix() + Lang.permissions());
			return erArg(sender, "Podano bledny argument");
		}
		
		if(arg.equalsIgnoreCase("true") || arg.equalsIgnoreCase("allow") || arg.equalsIgnoreCase("on")) {
			if(DCLobby.getSettings().getValue(Setting.WHITELIST) == true) {
				sender.sendMessage(Lang.prefix() + Lang.alreadySet(true));
				return true;
			}
			DCLobby.getSettings().setValue(Setting.WHITELIST, true);
			DyrtCraft.getUtils().sendNotify(sender.getName() + " zmienil ustawienie whitelist na true", false);
			return true;
		}
		if(arg.equalsIgnoreCase("false") || arg.equalsIgnoreCase("deny") || arg.equalsIgnoreCase("off")) {
			if(DCLobby.getSettings().getValue(Setting.WHITELIST) == false) {
				sender.sendMessage(Lang.prefix() + Lang.alreadySet(false));
				return true;
			}
			DCLobby.getSettings().setValue(Setting.WHITELIST, false);
			DyrtCraft.getUtils().sendNotify(sender.getName() + " zmienil ustawienie whitelist na false", false);
			return true;
		} else {
			sender.sendMessage(Lang.prefix() + Lang.error() + " Podano nieprawidlowy argument!");
			sender.sendMessage(Lang.usage() + "/dclobby whitelist <false|true>");
			return true;
		}
	}
	
}
