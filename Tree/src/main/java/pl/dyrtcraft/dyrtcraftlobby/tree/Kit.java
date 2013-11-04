package pl.dyrtcraft.dyrtcraftlobby.tree;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class Kit {
	
	public static ItemStack witamyBook() {
		ItemStack a = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta aMeta = (BookMeta) a.getItemMeta();
		aMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Witamy");
		aMeta.setAuthor(ChatColor.DARK_GREEN + "DyrtCraft Network");
		aMeta.addPage(
				"§2Witamy na DyrtCraft Network!\n" +
				"\n" +
				"\n" +
				"§0Aby wejsc na jeden z serwerow musisz przejsc przez odpowiedni portal.\n" +
				"\n" +
				"\n" +
				"§0Na poszczegolnych serwerach znajduja sie regulaminy oraz wskazowki dotyczace\n" +
				"§0rozgrywki."
				);
		aMeta.addPage("§3§0Po wiecej informacji zajrzyj na nasze forum:\n" +
				"§3dyrtcraft.pl/forum\n" +
				"\n" +
				"§0Zachecamy tez do odwiedzenia naszej strony internetowej:\n" +
				"§3dyrtcraft.pl\n" +
				"\n" +
				"§0gdzie znajdziesz dawke newsow z serwerowni oraz ciekawostki ze");
		aMeta.addPage("§0swiata Minecraft!\n" +
				"\n" +
				"\n" +
				"§2Zyczymy udanej rozgrywki i mile spedzonego czasu na naszych serwerach.\n" +
				"\n" +
				"\n" +
				"§2Pozdrawiamy,\n" +
				"\n" +
				"§2DyrtCraft Network."
				);
		a.setItemMeta(aMeta);
		return a;
	}
	
	public static ItemStack zakupBook() {
		ItemStack a = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta aMeta = (BookMeta) a.getItemMeta();
		aMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Zakup rang");
		aMeta.setAuthor(ChatColor.DARK_GREEN + "DyrtCraft Network");
		aMeta.addPage(
				"§2Rangi na serwerach DyrtCraft.\n" +
				"\n" +
				"\n" +
				"§0Kazdy z serwerow ma oreslona tematyke oraz system rozgrywki. Na kazdym z naszych\n" +
				"§0serwerow mamy Wam do zaoferowania platne rangi, ktore dadza Wam specjalne przywileje\n" +
				"§0do wykorzystania na konkretnym serwerze."
				);
		aMeta.addPage("§0Aby zakupic range wejdz w zakladke §2DyrtShop§0 na naszej stronie internetowej:\n" +
				"\n" +
				"§3www.dyrtcraft.pl§0\n" +
				"\n" +
				"\n" +
				"§0Rangi zakupic mozna poprzez doladowanie §3krotkim kodem SMS §0lub dokonujac\n" +
				"§3przelewu na konto§0.\n" +
				"\n" +
				"§2Wiecej informacji na Naszym forum."
				);
		a.setItemMeta(aMeta);
		return a;
	}
	
	public static void sendKit(Player player) {
		Inventory inv = player.getInventory();
		inv.setItem(1, Kit.witamyBook());
		inv.setItem(2, Kit.zakupBook());
	}
	
}
