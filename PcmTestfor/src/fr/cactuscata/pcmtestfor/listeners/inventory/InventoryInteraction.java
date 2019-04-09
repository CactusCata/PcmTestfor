package fr.cactuscata.pcmtestfor.listeners.inventory;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.cactuscata.pcmevent.utils.bukkit.ItemBuilder;

public final class InventoryInteraction implements Listener {

	@EventHandler
	public final void onClickInventory(InventoryClickEvent event) {

		final ItemStack item = event.getCurrentItem();

		if (item == null)
			return;

		final Inventory inventory = event.getClickedInventory();
		final Player playerWhoClicked = (Player) event.getWhoClicked();

		if (inventory.getTitle().startsWith("CheatsInfo: ")) {

			if (item.hasItemMeta() && item.getItemMeta().hasEnchants()) {

				final String playerGetterName = inventory.getTitle().replace("CheatsInfo: ", "");

				final Inventory inventoryCreated = Bukkit.createInventory(null, 9, "Ban " + playerGetterName);
				inventoryCreated.setItem(2, new ItemBuilder(Material.WOOL, (byte) 5)
						.setDisplayName("§cBannir le joueur §e" + playerGetterName + "§c en executant la commande :")
						.setLore(Arrays.asList("§cgtempban " + playerGetterName + " 2month "
								+ item.getItemMeta().getDisplayName() + " (AntiCheat [Testfor])"))
						.build());
				inventoryCreated.setItem(6,
						new ItemBuilder(Material.WOOL, (byte) 14).setDisplayName("§cRetour").build());

				playerWhoClicked.openInventory(inventoryCreated);

			}
			event.setCancelled(true);

		} else if (inventory.getTitle().startsWith("Ban ")) {
			if (item.getType() == Material.WOOL && item.getDurability() == 14) {
				playerWhoClicked.closeInventory();
				event.setCancelled(true);
			} else {

				Bukkit.getServer().dispatchCommand(playerWhoClicked,
						item.getItemMeta().getLore().get(0).replace("§c", ""));
				playerWhoClicked.closeInventory();
				event.setCancelled(true);

			}

		}
	}
}
