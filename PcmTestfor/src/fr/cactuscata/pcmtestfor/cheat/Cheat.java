package fr.cactuscata.pcmtestfor.cheat;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.cactuscata.pcmevent.utils.bukkit.ItemBuilder;
import fr.cactuscata.pcmevent.utils.bukkit.PrefixMessage;
import fr.cactuscata.pcmevent.utils.bukkit.Tps;
import fr.cactuscata.pcmevent.utils.other.DateUtils;
import fr.cactuscata.pcmevent.utils.other.Maths;
import fr.cactuscata.pcmtestfor.utils.PlayerTestfor;

public abstract class Cheat {

	private final List<String> loreCheatInformationItem = new ArrayList<>();
	private int alertNumber;
	private final Material material;

	protected abstract String getMessageToSendAtEveryone(final String playerName);

	protected abstract String getMessageToOp(final String firstPart, final String cheatValue, final String endPart);

	protected abstract String reasonCheat(final String information);

	public abstract void check(final PlayerTestfor playerTestfor);

	public final ItemStack getItemCheat() {
		return new ItemBuilder(this.material).setDisplayName("§c" + this.toString() + ": " + this.alertNumber)
				.setLore(getWarnList()).setGlowing(getNumberOfAlert() != 0).build();
	}

	public final void reset() {
		this.loreCheatInformationItem.clear();
		this.alertNumber = 0;
	}

	protected Cheat(final Material material) {
		this.material = material;
	}

	public final int getNumberOfAlert() {
		return this.alertNumber;
	}

	public final List<String> getWarnList() {
		return this.loreCheatInformationItem;
	}

	protected final void addWarnAndSendMessages(final PlayerTestfor playerTestfor) {
		addWarnAndSendMessages("", playerTestfor);
	}

	protected final void addWarnAndSendMessages(final String message, final PlayerTestfor playerTestfor) {

		this.alertNumber++;

		final String endInformation = " (MS: " + playerTestfor.getPing() + " [TPS: "
				+ Maths.arrondidouble(Tps.getTps(), 2) + "])";

		this.loreCheatInformationItem
				.add("§c[§f" + DateUtils.getActualDate() + "§c] §4" + this.reasonCheat(message) + endInformation);
		if (playerTestfor.isCleanPlayer())
			playerTestfor.setBadPlayer();
		sendMessage(message, endInformation, playerTestfor);
	}

	private final void sendMessage(final String message, final String endInformation,
			final PlayerTestfor playerTestfor) {

		final Player cheater = playerTestfor.getPlayer();

		switch (this.alertNumber) {
		case 1:
			cheater.sendMessage(PrefixMessage.ANTI_CHEAT_TESTFOR
					+ "Attention jeune personne, tu es suspecté de cheat ! Tu devrais faire un petit peu attention, c'est sanctionnable d'un bannissement de deux mois :)");
			break;
		case 2:
		case 3:
			String text = this.getMessageToOp(PrefixMessage.ANTI_CHEAT_TESTFOR + cheater.getDisplayName() + "§b",
					message, endInformation);
			Bukkit.getOnlinePlayers().stream().filter(player -> player.isOp())
					.forEach(opPlayer -> opPlayer.sendMessage(text));
			cheater.sendMessage(PrefixMessage.ANTI_CHEAT_TESTFOR
					+ "§cJe crois que ton cheat est arrivé aux oreilles des grands et admirables animateurs !");
			break;
		default:
			text = this.getMessageToSendAtEveryone(cheater.getDisplayName());
			Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(text));

		}

	}

}
