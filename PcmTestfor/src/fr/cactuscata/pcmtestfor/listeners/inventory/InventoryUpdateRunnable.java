package fr.cactuscata.pcmtestfor.listeners.inventory;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import fr.cactuscata.pcmevent.utils.bukkit.ItemBuilder;
import fr.cactuscata.pcmevent.utils.bukkit.Tps;
import fr.cactuscata.pcmevent.utils.other.Maths;
import fr.cactuscata.pcmtestfor.cheat.list.AutoClick;
import fr.cactuscata.pcmtestfor.commands.AntiCheatCommand;
import fr.cactuscata.pcmtestfor.utils.PlayerTestfor;

public final class InventoryUpdateRunnable extends BukkitRunnable {

	@Override
	public final void run() {
		for (final Player players : AntiCheatCommand.getVerifiers().keySet()) {
			final Inventory inventoryTop = players.getOpenInventory().getTopInventory();
			if ((inventoryTop != null) && (inventoryTop.getTitle().startsWith("CheatsInfo: "))) {

				if (Bukkit.getPlayer(inventoryTop.getName().split(": ")[1]) != null
						|| PlayerTestfor.getPlayerCheaterKeepMap().containsKey(inventoryTop.getName().split(": ")[1])) {
					final PlayerTestfor playerTestfor = AntiCheatCommand.getVerifiers().get(players);

					for (int i = 0, j = playerTestfor.getCheatInformation().size(); i < j; i++)
						inventoryTop.setItem(i, playerTestfor.getCheatInformation().get(i).getItemCheat());

					addItemAutoClick(playerTestfor.getAutoRightClick(), inventoryTop, 27);
					addItemAutoClick(playerTestfor.getAutoLeftClick(), inventoryTop, 28);

					final int numberOfAllAlerte = playerTestfor.getNumberOfTotalAlert();
					inventoryTop.setItem(32,
							new ItemBuilder(Material.BOOK, setCorrectAmount(numberOfAllAlerte))
									.setDisplayName("§cNombre d'alertes: " + numberOfAllAlerte)
									.setLore(playerTestfor.getAllWarn()).setGlowing(numberOfAllAlerte != 0).build());

					final int ping = playerTestfor.getPing();
					inventoryTop.setItem(33, new ItemBuilder(Material.EXP_BOTTLE, setCorrectAmount(ping))
							.setDisplayName("§6Ping: §f" + ping).build());

					inventoryTop.setItem(34,
							new ItemBuilder(Material.COMMAND, (int) Maths.arrondidouble(Tps.getTps(), 0))
									.setDisplayName("§6Tps server : §f" + Maths.arrondidouble(Tps.getTps(), 3))
									.build());

					final int minutesConnected = (int) playerTestfor.getTimeConnected() / 60000;
					inventoryTop.setItem(35, new ItemBuilder(Material.WATCH, setCorrectAmount(minutesConnected))
							.setDisplayName("§6Temps de jeu : §f" + minutesConnected + " minutes").build());

				}
			} else
				AntiCheatCommand.getVerifiers().remove(players);

		}
	}

	private final void addItemAutoClick(final AutoClick autoClick, final Inventory inventoryTop, final int slot) {
		final int[] clicks = autoClick.getLastClicksCount();
		inventoryTop
				.setItem(slot,
						new ItemBuilder(Material.STICK, setCorrectAmount(clicks[0]))
								.setDisplayName("§c" + autoClick.toString()).setLore(Arrays.asList("§e" + clicks[0],
										"§e" + clicks[1], "§e" + clicks[2], "§e" + clicks[3], "§e" + clicks[4]))
								.build());
	}

	private final int setCorrectAmount(final int amount) {
		return amount > 64 ? 64 : amount;
	}

}
