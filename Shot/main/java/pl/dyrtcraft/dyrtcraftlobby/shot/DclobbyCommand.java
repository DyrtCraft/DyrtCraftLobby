package pl.dyrtcraft.dyrtcraftlobby.shot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.DyrtCraft.DyrtCraftXP.api.DyrtCraftPlugin;

public class DclobbyCommand implements CommandExecutor {

	DyrtCraftLobbyShot plugin;
	
	public DclobbyCommand(DyrtCraftLobbyShot dyrtCraftLobbyTree) {
		plugin = dyrtCraftLobbyTree;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("dclobby") || command.getName().equalsIgnoreCase("dyrtcraftlobby")) {
			if(args.length == 0) {
				return erArg(sender, "Zbyt malo argumentów");
			}
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("about") || args[0].equalsIgnoreCase("info") || args[0].equalsIgnoreCase("version")) {
					return aboutArg(sender);
				}
				if(args[0].equalsIgnoreCase("kit")) {
					return kitArg(sender);
				}
				if(args[0].equalsIgnoreCase("protect") || args[0].equalsIgnoreCase("protected")) {
					return protectArg(sender, null);
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
			} else {
				return erArg(sender, "Zbyt duzo argumentów");
			}
		}
		return false;
	}
	
	private boolean erArg(CommandSender sender, String reason) {
		sender.sendMessage(Util.prefix() + Util.error() + " " + reason + "!");
		sender.sendMessage(Util.usage() + plugin.getCommand("dclobby").getUsage());
		return true;
	}
	
	private boolean aboutArg(CommandSender sender) {
		sender.sendMessage(ChatColor.DARK_GREEN + " =============== DyrtCraftLobby Shot =============== ");
		sender.sendMessage(ChatColor.DARK_GREEN + "Wersja: " + ChatColor.GRAY + plugin.getDescription().getVersion());
		sender.sendMessage(ChatColor.DARK_GREEN + "Nazwa kodowa: " + ChatColor.GRAY + "Shot");
		sender.sendMessage(ChatColor.DARK_GREEN + "Autorzy: " + ChatColor.GRAY + plugin.getDescription().getAuthors());
		sender.sendMessage(ChatColor.DARK_GREEN + "GitHub: " + ChatColor.GRAY + "https://github.com/DyrtCraft/DyrtCraftLobby");
		return true;
	}
	
	private boolean kitArg(CommandSender sender) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Util.prefix() + Util.console());
			return true;
		}
		sender.sendMessage(Util.prefix() + Util.gettingKit());
		Kit.sendKit((Player) sender);
		return true;
	}
	
	private boolean protectArg(CommandSender sender, String arg) {
		if(arg == null) {
			if(!sender.hasPermission("lobby.protect")) {
				sender.sendMessage(Util.prefix() + Util.permissions("lobby.protect"));
				return true;
			}
			sender.sendMessage(Util.prefix() + Util.stan("protect") + DyrtCraftLobbyShot.protect + ".");
			return true;
		}
		if(!sender.hasPermission("lobby.protect.set")) {
			sender.sendMessage(Util.prefix() + Util.permissions("lobby.protect.set"));
			return true;
		}
		
		if(arg.equalsIgnoreCase("true") || arg.equalsIgnoreCase("allow") || arg.equalsIgnoreCase("on")) {
			if(DyrtCraftLobbyShot.protect == true) {
				sender.sendMessage(Util.prefix() + Util.alreadySet(true));
				return true;
			}
			DyrtCraftLobbyShot.protect = true;
			DyrtCraftPlugin.sendMsgToOp(sender.getName() + " zmienil ustawienia protect na true", 0);
			return true;
		}
		if(arg.equalsIgnoreCase("false") || arg.equalsIgnoreCase("deny") || arg.equalsIgnoreCase("off")) {
			if(DyrtCraftLobbyShot.protect == false) {
				sender.sendMessage(Util.prefix() + Util.alreadySet(false));
				return true;
			}
			DyrtCraftLobbyShot.protect = false;
			DyrtCraftPlugin.sendMsgToOp(sender.getName() + " zmienil ustawienia protect na false", 0);
			return true;
		} else {
			return true;
		}
	}
	
	private boolean resetArg(String reset, CommandSender sender) {
		Player player = Bukkit.getServer().getPlayer(reset);
		if(reset.equalsIgnoreCase(sender.getName())) {
			// Molek tu byl xD
			if(!sender.hasPermission("lobby.reset")) {
				sender.sendMessage(Util.prefix() + Util.permissions("lobby.reset"));
				return true;
			}
			sender.sendMessage(Util.prefix() + Util.reset(reset));
			DyrtCraftLobbyShot.resetPlayer(player);
			return true;
		}
		if(!sender.hasPermission("lobby.reset.player")) {
			sender.sendMessage(Util.prefix() + Util.permissions("whitelist.protect.player"));
			return true;
		}
		if(player == null) {
        	sender.sendMessage(ChatColor.RED + "Gracz " + reset + " nie jest obecnie online!");
        	return true;
        }
		sender.sendMessage(Util.prefix() + Util.reset(reset));
		DyrtCraftLobbyShot.resetPlayer(player);
		return true;
	}
	
	private boolean whitelist(CommandSender sender, String arg) {
		if(arg == null) {
			if(!sender.hasPermission("lobby.whitelist")) {
				sender.sendMessage(Util.prefix() + Util.permissions("lobby.whitelist"));
				return true;
			}
			sender.sendMessage(Util.prefix() + Util.stan("whitelist") + DyrtCraftLobbyShot.whitelist + ".");
			return true;
		}
		if(!sender.hasPermission("lobby.whitelist.set")) {
			sender.sendMessage(Util.prefix() + Util.permissions("lobby.whitelist.set"));
			return erArg(sender, "Podano bledny argument");
		}
		
		if(arg.equalsIgnoreCase("true") || arg.equalsIgnoreCase("allow") || arg.equalsIgnoreCase("on")) {
			if(DyrtCraftLobbyShot.whitelist == true) {
				sender.sendMessage(Util.prefix() + Util.alreadySet(true));
				return true;
			}
			DyrtCraftLobbyShot.whitelist = true;
			DyrtCraftPlugin.sendMsgToOp(sender.getName() + " zmienil ustawienia whitelist na true", 0);
			return true;
		}
		if(arg.equalsIgnoreCase("false") || arg.equalsIgnoreCase("deny") || arg.equalsIgnoreCase("off")) {
			if(DyrtCraftLobbyShot.whitelist == false) {
				sender.sendMessage(Util.prefix() + Util.alreadySet(false));
				return true;
			}
			DyrtCraftLobbyShot.whitelist = false;
			DyrtCraftPlugin.sendMsgToOp(sender.getName() + " zmienil ustawienia whitelist na false", 0);
			return true;
		} else {
			return erArg(sender, "Podano bledny argument");
		}
	}
	
}
