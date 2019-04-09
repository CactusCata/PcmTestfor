package fr.cactuscata.pcmtestfor.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cactuscata.pcmevent.command.CCommandExecutor.SenderType;
import fr.cactuscata.pcmevent.command.CommandException;
import fr.cactuscata.pcmevent.command.CommandValidator;
import fr.cactuscata.pcmevent.command.SubCommand;
import fr.cactuscata.pcmtestfor.utils.PlayerTestfor;

final class AntiCheatCmdVerif extends SubCommand {

	AntiCheatCmdVerif() {
		super("verif", SenderType.PLAYER, "Veuillez préciser le joueur !");
	}

	@Override
	protected final String getHelp() {
		return "Cet argument permet de regarder tous les cheats potentiellement utilisés par le joueur précisé.";
	}

	@Override
	protected final void execute(final CommandSender sender, final String[] args) throws CommandException {

		final Player playerGetter = Bukkit.getPlayerExact(args[0]);
		final PlayerTestfor playerTestforGetter;
		final boolean offline = (playerGetter == null || !playerGetter.isOnline());
		if (offline) {
			CommandValidator.isFalse(PlayerTestfor.getPlayerCheaterKeepMap().containsKey(args[0]),
					"Le joueur " + args[0] + " n'est pas connecté ou n'avait pas fait d'alerte de cheat !");

			playerTestforGetter = PlayerTestfor.getPlayerCheaterKeepMap().get(args[0]);

		} else {
			playerTestforGetter = PlayerTestfor.getPlayerTestfor(playerGetter);
		}

		final Player playerViewer = (Player) sender;
		playerViewer.openInventory(Bukkit.createInventory(null, 36, "CheatsInfo: " + args[0]));
		AntiCheatCommand.getVerifiers().put(playerViewer, playerTestforGetter);
	}

}
