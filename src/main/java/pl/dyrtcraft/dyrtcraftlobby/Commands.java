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
		sender.sendMessage(ChatColor.GOLD + "Autor: " + plugin.getDescription().getAuthors());
		sender.sendMessage(ChatColor.GOLD + " >==========[ " + ChatColor.BOLD + ChatColor.AQUA + "DyrtCraftLobby" + ChatColor.RESET + ChatColor.GOLD + " ]==========< ");
		return true;
	}
	
	protected boolean erArg(CommandSender sender, String reason) {
		sender.sendMessage(ChatColor.RED + reason);
		sender.sendMessage(ChatColor.RED + "Uzycie: " + plugin.getCommand("dclobby").getUsage());
		return true;
	}
	
	protected boolean kitArg(CommandSender sender) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Nie mozesz wykonac tej komendy z poziomu konsoli!");
			return true;
		}
		Player p = (Player) sender;
		
		p.sendMessage(ChatColor.GRAY + "Otrzymanie podstawowych itemow.");
		p.getInventory().addItem(Kity.compass());
		p.getInventory().addItem(Kity.ksiazka1());
		p.getInventory().addItem(Kity.ksiazka2());
		return true;
	}
	
}
