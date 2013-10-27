package pl.dyrtcraft.dyrtcraftlobby;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

	DyrtCraftLobby plugin;

	public Commands(DyrtCraftLobby dyrtCraftLobby) {
		plugin = dyrtCraftLobby;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		if(command.getName().equalsIgnoreCase("dclobby")) {
			if(args.length == 0) {
				return erArg(sender, "Zbyt malo argumentów!");
			}
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("about") || args[0].equalsIgnoreCase("info")) {
					return aboutArg(sender);
				}
				if(args[0].equalsIgnoreCase("kit") || args[0].equalsIgnoreCase("itemy")) {
					return kitArg(sender);
				}
				if(args[0].equalsIgnoreCase("prace")) {
					return praceArg(sender, null);
				}
				if(args[0].equalsIgnoreCase("protect")) {
					return protectArg(sender, null);
				} else {
					return erArg(sender, "Podano nieprawidlowy argument!");
				}
			}
			if(args.length == 2) {
				if(args[0].equalsIgnoreCase("prace")) {
					return praceArg(sender, args[1]);
				}
				if(args[0].equalsIgnoreCase("protect")) {
					return protectArg(sender, args[1]);
				} else {
					return erArg(sender, "Podano nieprawidlowy argument!");
				}
			} else {
				return erArg(sender, "Zbyt duzo argumentów!");
			}
		}
		return false;
	}
	
	protected boolean aboutArg(CommandSender sender) {
		sender.sendMessage(ChatColor.GOLD + " >==========[ " + ChatColor.BOLD + ChatColor.AQUA + "DyrtCraftLobby" + ChatColor.RESET + ChatColor.GOLD + " ]==========< ");
		sender.sendMessage(ChatColor.GOLD + "Wersja: " + ChatColor.GRAY + plugin.getDescription().getVersion());
		sender.sendMessage(ChatColor.GOLD + "Autor: " + ChatColor.GRAY + plugin.getDescription().getAuthors());
		sender.sendMessage(ChatColor.GOLD + "GitHub: " + ChatColor.GRAY + "https://www.github.com/DyrtCraft/DyrtCraftLobby/Modern");
		sender.sendMessage(ChatColor.GOLD + " >==========[ " + ChatColor.BOLD + ChatColor.AQUA + "DyrtCraftLobby" + ChatColor.RESET + ChatColor.GOLD + " ]==========< ");
		return true;
	}
	
	protected boolean erArg(CommandSender sender, String reason) {
		sender.sendMessage(ChatColor.AQUA + "[DCLobby] " + ChatColor.RED + reason);
		sender.sendMessage(ChatColor.RED + "Uzycie: " + plugin.getCommand("dclobby").getUsage());
		return true;
	}
	
	protected boolean kitArg(CommandSender sender) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Nie mozesz wykonac tej komendy z poziomu konsoli!");
			return true;
		}
		Player p = (Player) sender;
		
		p.sendMessage(ChatColor.AQUA + "[DCLobby] " + ChatColor.GRAY + "Otrzymanie podstawowych itemow.");
		p.getInventory().addItem(Kity.compass());
		p.getInventory().addItem(Kity.ksiazka1());
		p.getInventory().addItem(Kity.ksiazka2());
		return true;
	}
	
	protected boolean praceArg(CommandSender sender, String arg) {
		if(!(sender.isOp())) {
			return erArg(sender, "Brak uprawnien do tej komendy");
		}
		
		if(arg == null) {
			sender.sendMessage(ChatColor.AQUA + "[DCLobby] " + ChatColor.GRAY + "Stan ustawien prac technicznych: \"" + plugin.prace + "\".");
			return true;
		}
		if(arg.equalsIgnoreCase("true")) {
			if(plugin.prace == true) {
				sender.sendMessage(ChatColor.AQUA + "[DCLobby] " + ChatColor.RED + "To ustawienie juz jest na \"true\"");
				return true;
			}
			plugin.prace = true;
			plugin.getServer().getLogger().warning("[DCLobby] " + sender.getName() + ": zmienil ustawienia prac na \"true\"!");
			sender.sendMessage(ChatColor.AQUA + "[DCLobby] " + ChatColor.DARK_GREEN + "Pomyslnie zmieniono ustawienia prac na \"true\"!");
			return true;
		}
		if(arg.equalsIgnoreCase("false")) {
			if(plugin.prace == false) {
				sender.sendMessage(ChatColor.AQUA + "[DCLobby] " + ChatColor.RED + "To ustawienie juz jest na \"false\"");
				return true;
			}
			plugin.prace = false;
			plugin.getServer().getLogger().warning("[DCLobby] " + sender.getName() + ": zmienil ustawienia prac na \"false\"!");
			sender.sendMessage(ChatColor.AQUA + "[DCLobby] " + ChatColor.DARK_GREEN + "Pomyslnie zmieniono ustawienia prac na \"false\"!");
			return true;
		} else {
			return erArg(sender, "Podano nieprawidlowy argument!\n " + ChatColor.AQUA + "Czy chodzilo Ci o: " + ChatColor.BOLD +  "/dclobby prace <false|true>" + ChatColor.RESET + ChatColor.AQUA + "?");
		}
	}
	
	protected boolean protectArg(CommandSender sender, String arg) {
		if(!(sender.isOp())) {
			return erArg(sender, "Brak uprawnien do tej komendy");
		}
		
		if(arg == null) {
			sender.sendMessage(ChatColor.AQUA + "[DCLobby] " + ChatColor.GRAY + "Stan ustawien zabezpieczen: \"" + plugin.protect + "\".");
			return true;
		}
		if(arg.equalsIgnoreCase("true")) {
			if(plugin.protect == true) {
				sender.sendMessage(ChatColor.AQUA + "[DCLobby] " + ChatColor.RED + "To ustawienie juz jest na \"true\"");
				return true;
			}
			plugin.protect = true;
			plugin.getServer().getLogger().warning("[DCLobby] " + sender.getName() + ": zmienil ustawienia zabezpieczen na \"true\"!");
			sender.sendMessage(ChatColor.AQUA + "[DCLobby] " + ChatColor.DARK_GREEN + "Pomyslnie zmieniono ustawienia zabezpieczen na \"true\"!");
			return true;
		}
		if(arg.equalsIgnoreCase("false")) {
			if(plugin.protect == false) {
				sender.sendMessage(ChatColor.AQUA + "[DCLobby] " + ChatColor.RED + "To ustawienie juz jest na \"false\"");
				return true;
			}
			plugin.protect = false;
			plugin.getServer().getLogger().warning("[DCLobby] " + sender.getName() + ": zmienil ustawienia zabezpieczen na \"false\"!");
			sender.sendMessage(ChatColor.AQUA + "[DCLobby] " + ChatColor.DARK_GREEN + "Pomyslnie zmieniono ustawienia zabezpieczen na \"false\"!");
			return true;
		} else {
			return erArg(sender, "Podano nieprawidlowy argument!\n " + ChatColor.AQUA + "Czy chodzilo Ci o: " + ChatColor.BOLD +  "/dclobby protect <false|true>" + ChatColor.RESET + ChatColor.AQUA + "?");
		}
	}
	
}
