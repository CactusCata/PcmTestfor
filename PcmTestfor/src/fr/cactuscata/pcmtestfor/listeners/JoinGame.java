package fr.cactuscata.pcmtestfor.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.cactuscata.pcmevent.utils.bukkit.PrefixMessage;
import fr.cactuscata.pcmevent.utils.other.StringUtils;
import fr.cactuscata.pcmtestfor.utils.PlayerTestfor;

public final class JoinGame implements Listener {

	@EventHandler
	public final void onJoin(final PlayerJoinEvent event) {

		final Player player = event.getPlayer();

		if (PlayerTestfor.getPlayerCheaterKeepMap().containsKey(player.getName())) {
			PlayerTestfor playerTestfor = PlayerTestfor.getPlayerCheaterKeepMap().get(player.getName());
			PlayerTestfor.put(player, playerTestfor);
		} else
			new PlayerTestfor(player);

		if (player.isOp())
			if (!PlayerTestfor.getPlayerCheaterKeepMap().isEmpty())
				player.sendMessage(PrefixMessage.PREFIX + "Les joueurs ayant utilisé un cheat sont : "
						+ StringUtils.join(PlayerTestfor.getPlayerCheaterKeepMap().keySet(), " "));

	}

}
