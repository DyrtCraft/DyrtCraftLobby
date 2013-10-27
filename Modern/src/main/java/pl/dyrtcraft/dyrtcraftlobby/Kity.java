package pl.dyrtcraft.dyrtcraftlobby;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class Kity {

	DyrtCraftLobby plugin;
	
	public Kity(DyrtCraftLobby dyrtCraftLobby) {
		plugin = dyrtCraftLobby;
	}
	
	public static ItemStack compass() {
		ItemStack kompas = new ItemStack(Material.COMPASS);
		ItemMeta kompasMeta = kompas.getItemMeta();
		kompasMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Serwery DyrtCraft Network");
		kompasMeta.setLore(Arrays.asList(ChatColor.GRAY + "Kliknij prawym klawiszem myszki, aby wybrac serwer!"));
		kompas.setItemMeta(kompasMeta);
		return kompas;
	}
	
	public static ItemStack ksiazka1() {
		ItemStack ksiazka1 = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta ksiazka1Meta = (BookMeta) ksiazka1.getItemMeta();
		ksiazka1Meta.setTitle(ChatColor.GREEN + "Witamy");
		ksiazka1Meta.setAuthor(ChatColor.GOLD + "" + ChatColor.BOLD + "DyrtCraft Network");
		ksiazka1Meta.addPage(
				"§2Witamy na DyrtCraft Network!\n" +
				"\n" +
				"\n" +
				"§0Aby wejsc na jeden z serwerow musisz przejsc przez odpowiedni portal.\n" +
				"\n" +
				"\n" +
				"§0Na poszczegolnych serwerach znajduja sie regulaminy oraz wskazowki dotyczace\n" +
				"§0rozgrywki."
				);
		ksiazka1Meta.addPage("§3§0Po wiecej informacji zajrzyj na nasze forum:\n" +
				"§3dyrtcraft.pl/forum\n" +
				"\n" +
				"§0Zachecamy tez do odwiedzenia naszej strony internetowej:\n" +
				"§3dyrtcraft.pl\n" +
				"\n" +
				"§0gdzie znajdziesz dawke newsow z serwerowni oraz ciekawostki ze");
		ksiazka1Meta.addPage("§0swiata Minecraft!\n" +
				"\n" +
				"\n" +
				"§2Zyczymy udanej rozgrywki i mile spedzonego czasu na naszych serwerach.\n" +
				"\n" +
				"\n" +
				"§2Pozdrawiamy,\n" +
				"\n" +
				"§2DyrtCraft Network."
				);
		ksiazka1.setItemMeta(ksiazka1Meta);
		return ksiazka1;
	}
	
	public static ItemStack ksiazka2() {
		ItemStack ksiazka2 = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta ksiazka2Meta = (BookMeta) ksiazka2.getItemMeta();
		ksiazka2Meta.setTitle(ChatColor.GREEN + "Zakup rang");
		ksiazka2Meta.setAuthor(ChatColor.GOLD + "" + ChatColor.BOLD + "DyrtCraft Network");
		ksiazka2Meta.addPage(
				"§2Rangi na serwerach DyrtCraft.\n" +
				"\n" +
				"\n" +
				"§0Kazdy z serwerow ma oreslona tematyke oraz system rozgrywki. Na kazdym z naszych\n" +
				"§0serwerow mamy Wam do zaoferowania platne rangi, ktore dadza Wam specjalne przywileje\n" +
				"§0do wykorzystania na konkretnym serwerze."
				);
		ksiazka2Meta.addPage("§0Aby zakupic range wejdz w zakladke §2DyrtShop§0 na naszej stronie internetowej:\n" +
				"\n" +
				"§3www.dyrtcraft.pl§0\n" +
				"\n" +
				"\n" +
				"§0Rangi zakupic mozna poprzez doladowanie §3krotkim kodem SMS §0lub dokonujac\n" +
				"§3przelewu na konto§0.\n" +
				"\n" +
				"§2Wiecej informacji na Naszym forum."
				);
		ksiazka2.setItemMeta(ksiazka2Meta);
		return ksiazka2;
	}
	
	public static ItemStack sklep() {
		ItemStack sklep = new ItemStack(Material.EMERALD, 1);
		ItemMeta sklepMeta = sklep.getItemMeta();
		sklepMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Sklep Lobby");
		sklep.setItemMeta(sklepMeta);
		return sklep;
	}
	
}
