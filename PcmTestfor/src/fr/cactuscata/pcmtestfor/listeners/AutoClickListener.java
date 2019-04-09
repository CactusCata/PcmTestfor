package fr.cactuscata.pcmtestfor.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.cactuscata.pcmtestfor.cheat.list.AutoClick;
import fr.cactuscata.pcmtestfor.utils.PlayerTestfor;

public final class AutoClickListener implements Listener {

	@EventHandler
	public final void interact(final PlayerInteractEvent event) {

		final PlayerTestfor playerTestfor = PlayerTestfor.getPlayerTestfor(event.getPlayer());
		final Action action = event.getAction();
		final AutoClick autoClick = (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK
				? playerTestfor.getAutoRightClick() : playerTestfor.getAutoLeftClick());

		if (action == Action.LEFT_CLICK_AIR || action == Action.RIGHT_CLICK_AIR) {
			event.setCancelled((autoClick.getLastBlockInteraction() > System.currentTimeMillis())
					&& (autoClick.getClicks() >= 10));

			autoClick.incrementClicks();
		} else if (action == Action.LEFT_CLICK_BLOCK || action == Action.RIGHT_CLICK_BLOCK) {
			autoClick.setLastBlockInteraction(System.currentTimeMillis() + 5000L);
		}

	}

	@EventHandler
	public final void interactEntity(final PlayerInteractAtEntityEvent event) {
		PlayerTestfor.getPlayerTestfor(event.getPlayer()).getAutoRightClick().incrementClicks();
	}

}
