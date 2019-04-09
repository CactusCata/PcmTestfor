package fr.cactuscata.pcmtestfor.cheat;

import org.bukkit.scheduler.BukkitRunnable;

import fr.cactuscata.pcmtestfor.utils.PlayerTestfor;

public final class CheckCheatRunnable extends BukkitRunnable {

	@Override
	public final void run() {
		PlayerTestfor.getPlayersTestfor().forEach(
				playerTestFor -> playerTestFor.getCheatInformation().forEach(cheat -> cheat.check(playerTestFor)));

	}
}