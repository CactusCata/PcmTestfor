package fr.cactuscata.pcmtestfor.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

import fr.cactuscata.pcmtestfor.utils.PlayerTestfor;

public final class FastBowListerner implements Listener {

	@EventHandler
	public final void fastbow(final EntityShootBowEvent event) {
		if (!(event.getEntity() instanceof Player) || event.getForce() != 1.0D)
			return;

		PlayerTestfor.getPlayerTestfor((Player) event.getEntity()).getFastBow().incrementArrowSend();

	}

}
