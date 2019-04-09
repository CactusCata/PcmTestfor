package fr.cactuscata.pcmtestfor.cheat.list;

import org.bukkit.Material;

import fr.cactuscata.pcmevent.utils.bukkit.PrefixMessage;
import fr.cactuscata.pcmtestfor.cheat.Cheat;
import fr.cactuscata.pcmtestfor.utils.PlayerTestfor;

public final class FastBow extends Cheat {

	private int arrowSend;

	public FastBow() {
		super(Material.BOW);
	}

	@Override
	public final String getMessageToSendAtEveryone(final String playerName) {
		return PrefixMessage.ANTI_CHEAT_TESTFOR + "Le joueur " + playerName + "§3 semble être Cupidon :o ! (§c"
				+ this.toString() + "§3)";
	}

	@Override
	public final String getMessageToOp(final String firstPart, final String cheatValue, final String endPart) {
		return firstPart + " a lancé §4" + cheatValue + " §bflèches en une seconde " + endPart;
	}

	@Override
	public final String reasonCheat(final String information) {
		return information + " §3flèches par secondes";
	}

	@Override
	public final String toString() {
		return "FastBow";
	}

	@Override
	public final void check(final PlayerTestfor playerTestfor) {

		if (this.arrowSend > 2)
			super.addWarnAndSendMessages(String.valueOf(this.arrowSend), playerTestfor);
		update();

	}

	public final void incrementArrowSend() {
		this.arrowSend++;
	}

	private final void update() {
		this.arrowSend = 0;
	}

}
