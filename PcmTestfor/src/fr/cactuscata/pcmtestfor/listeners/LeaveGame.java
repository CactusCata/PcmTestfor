package fr.cactuscata.pcmtestfor.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.cactuscata.pcmtestfor.utils.PlayerTestfor;

public final class LeaveGame implements Listener {

	@EventHandler
	public final void onQuit(final PlayerQuitEvent event) {

		final Player player = event.getPlayer();

		if (PlayerTestfor.getPlayerTestfor(player).isCleanPlayer())
			PlayerTestfor.unregister(player);

	}

}
