package fr.cactuscata.pcmtestfor.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cactuscata.pcmevent.command.CCommandExecutor.SenderType;
import fr.cactuscata.pcmevent.command.CommandException;
import fr.cactuscata.pcmevent.command.CommandValidator;
import fr.cactuscata.pcmevent.command.SubCommand;
import fr.cactuscata.pcmevent.utils.bukkit.PrefixMessage;
import fr.cactuscata.pcmtestfor.utils.PlayerTestfor;

final class AntiCheatCmdReset extends SubCommand {

	AntiCheatCmdReset() {
		super("reset", SenderType.ALL, "Veuillez préciser le joueur !");
	}

	@Override
	protected final String getHelp() {
		return "Cette commande permet de remettre à zéro les cheats activés d'un joueur.";
	}

	@Override
	protected final void execute(final CommandSender sender, final String[] args) throws CommandException {
		final Player playerGetter = Bukkit.getPlayerExact(args[0]);
		final PlayerTestfor playerTestforGetter;
		final boolean offline = (playerGetter == null || !playerGetter.isOnline());
		if (offline) {
			CommandValidator.isTrue(!PlayerTestfor.getPlayerCheaterKeepMap().containsKey(args[0]),
					"Le joueur " + args[0] + " n'est pas connecté ou n'avait pas fait d'alerte de cheat !");
			playerTestforGetter = PlayerTestfor.getPlayerCheaterKeepMap().get(args[0]);

		} else
			playerTestforGetter = PlayerTestfor.getPlayerTestfor(playerGetter);

		playerTestforGetter.resetAllCheatsStats();
		PlayerTestfor.getPlayerCheaterKeepMap().remove(playerTestforGetter.getPlayer().getName());
		sender.sendMessage(
				PrefixMessage.PREFIX + "Vous avez remis à zéro les informations du joueur " + args[0] + " !");

	}

}
