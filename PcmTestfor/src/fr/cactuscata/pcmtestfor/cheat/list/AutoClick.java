package fr.cactuscata.pcmtestfor.cheat.list;

import org.bukkit.Material;

import fr.cactuscata.pcmevent.utils.bukkit.PrefixMessage;
import fr.cactuscata.pcmevent.utils.bukkit.Tps;
import fr.cactuscata.pcmevent.utils.other.Maths;
import fr.cactuscata.pcmtestfor.cheat.Cheat;
import fr.cactuscata.pcmtestfor.utils.PlayerTestfor;

public final class AutoClick extends Cheat {

	private int clicks;
	private final int[] click = new int[5];
	private long lastTimeAlert, lastTimeBlockInteraction;
	private final String cheatName;

	public AutoClick(final AutoClickType autoClickType) {
		super(Material.STICK);
		this.cheatName = autoClickType.toString();
	}

	@Override
	public final String getMessageToSendAtEveryone(final String playerName) {
		return String.format("%sLe joueur %d semble faire une crise d'épilepsie sur sa souris ! (§c%s§3)",
				PrefixMessage.PREFIX, playerName, this.toString());
	}

	@Override
	public final String getMessageToOp(final String firstPart, final String cheatValue, final String endPart) {
		return String.format("%s a fait §4 %s §bclics %s", firstPart, cheatValue, endPart);
	}

	@Override
	public final String reasonCheat(final String information) {
		return String.format("%s§3clics", information);
	}

	@Override
	public final void check(final PlayerTestfor playerTestfor) {
		if ((getClicks() >= 18 + (int) ((20.0D - Maths.arrondidouble(Tps.getTps(), 3)) * 2.0D)
				+ playerTestfor.getPing() / 50) && (this.lastTimeAlert + 1000L < System.currentTimeMillis())) {
			updateLastAlertTime();

			super.addWarnAndSendMessages(String.valueOf(getClicks()), playerTestfor);
		}

		updateClicks();
	}

	public final int getClicks() {
		return this.clicks;
	}

	public final void incrementClicks() {
		this.clicks++;
	}

	public final long getLastBlockInteraction() {
		return this.lastTimeBlockInteraction;
	}

	public final void setLastBlockInteraction(final long lastBlockInteraction) {
		this.lastTimeBlockInteraction = lastBlockInteraction;
	}

	private final void updateClicks() {
		for (int i = 4; i > 0; i--)
			this.click[i] = this.click[i - 1];
		this.click[0] = this.clicks;
		this.clicks = 0;
	}

	public final int[] getLastClicksCount() {
		return this.click;
	}

	private final void updateLastAlertTime() {
		this.lastTimeAlert = System.currentTimeMillis();
	}

	@Override
	public final String toString() {
		return this.cheatName;
	}

	public enum AutoClickType {

		AUTO_LEFT_CLICK("AutoLeftClick"),
		AUTO_RIGHT_CLICK("AutoRightClick");

		private final String cheatName;

		private AutoClickType(final String cheatName) {
			this.cheatName = cheatName;
		}

		@Override
		public final String toString() {
			return this.cheatName;
		}

	}

}
