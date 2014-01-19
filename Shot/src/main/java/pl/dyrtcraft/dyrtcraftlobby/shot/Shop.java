package pl.dyrtcraft.dyrtcraftlobby.shot;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import pl.dyrtcraft.DyrtCraft;

public class Shop implements Listener {
	
	public static Inventory inv;
	private static ItemStack xp, compass, unBan, vip, svip, close;
	
	public static void show(Player player) {
		inv = Bukkit.createInventory(player, 9, ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "DyrtCraft" + ChatColor.RESET + ChatColor.DARK_GRAY + " Sklep globalny");
		xp = createXp(player);
		compass = createCompass();
		close = createClose();
		
		unBan = create(player, "Unban na serwerze Hardcore", "Zakup unbana na serwerze Apocalypto", "Apocalypto - Hardcore", 14000);
		vip = create(player, "Globalna ranga VIP", "Zakup rangi VIP na kazdy serwer DyrtCraft Network", "Wszystkie", 100000);
		svip = create(player, "Globalna ranga SVIP", "Zakup rangi SVIP na kazdy serwer DyrtCraft Network", "Wszystkie", 140000);
		
		// Set slots
		inv.setItem(0, xp);
		inv.setItem(1, compass);
		inv.setItem(8, close);
		
		inv.setItem(3, unBan);
		inv.setItem(4, vip);
		inv.setItem(5, svip);
		
		// Open
		player.openInventory(inv);
		
	}
	
	private static ItemStack create(Player player, String name, String about, String server, int cost) {
		ItemStack i = new ItemStack(Material.FIREWORK, 1);
		ItemMeta iMeta = i.getItemMeta();
		iMeta.setDisplayName(ChatColor.GOLD + name);
		iMeta.setLore(Arrays.asList(ChatColor.DARK_GREEN + "Serwer: " + ChatColor.GOLD + server, ChatColor.DARK_GREEN + about, ChatColor.DARK_PURPLE + "Koszt " + cost + " XP"));
		i.setItemMeta(iMeta);
		return i;
	}
	
	private static ItemStack createClose() {
		ItemStack i = new ItemStack(Material.EYE_OF_ENDER, 1);
		ItemMeta iMeta = i.getItemMeta();
		iMeta.setDisplayName(ChatColor.GOLD + "Zamknij okno");
		i.setItemMeta(iMeta);
		return i;
	}
	
	private static ItemStack createCompass() {
		ItemStack i = new ItemStack(Material.COMPASS, 1);
		ItemMeta iMeta = i.getItemMeta();
		iMeta.setDisplayName(ChatColor.GOLD + "Wybór serwerów");
		iMeta.setLore(Arrays.asList(ChatColor.DARK_GREEN + "Wybierz serwer sieci DyrtCraft Network"));
		i.setItemMeta(iMeta);
		return i;
	}
	
	private static ItemStack createXp(Player player) {
		ItemStack i = new ItemStack(Material.EMERALD, 1);
		ItemMeta iMeta = i.getItemMeta();
		iMeta.setDisplayName(ChatColor.GOLD + "Twoja ilosc XP:");
		iMeta.setLore(Arrays.asList(ChatColor.DARK_GREEN + "Posiadasz " + DyrtCraft.getMember(player).getXp() + " XP"));
		i.setItemMeta(iMeta);
		return i;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		try {
			if(!(e.getInventory().getName() == inv.getName())) { return; }
			
			Player p = (Player) e.getWhoClicked();
			e.setCancelled(true);
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(compass.getItemMeta().getDisplayName())) {
				p.openInventory(DyrtCraft.getProxy().getServerChooserInventory(p));
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(close.getItemMeta().getDisplayName())) {
				p.closeInventory();
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(unBan.getItemMeta().getDisplayName())) {
				p.sendMessage(ChatColor.YELLOW + "Wkrótce :P");
				p.closeInventory();
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(vip.getItemMeta().getDisplayName())) {
				p.sendMessage(ChatColor.YELLOW + "Wkrótce :P");
				p.closeInventory();
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(svip.getItemMeta().getDisplayName())) {
				p.sendMessage(ChatColor.YELLOW + "Wkrótce :P");
				p.closeInventory();
				return;
			}
		} catch(NullPointerException ex) {}
	}
	
}
