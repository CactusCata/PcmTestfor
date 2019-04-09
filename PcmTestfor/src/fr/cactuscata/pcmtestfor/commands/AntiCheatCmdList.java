package fr.cactuscata.pcmtestfor.commands;

import org.bukkit.command.CommandSender;

import fr.cactuscata.pcmevent.command.CCommandExecutor.SenderType;
import fr.cactuscata.pcmevent.command.CommandException;
import fr.cactuscata.pcmevent.command.SubCommand;
import fr.cactuscata.pcmevent.utils.bukkit.PrefixMessage;
import fr.cactuscata.pcmevent.utils.other.StringUtils;
import fr.cactuscata.pcmtestfor.utils.PlayerTestfor;

final class AntiCheatCmdList extends SubCommand {

	AntiCheatCmdList() {
		super("list", SenderType.ALL);
	}

	@Override
	protected final String getHelp() {
		return "Cette commande vous permet de récuperer la liste de tous les joueurs qui ont utilisé un cheat.";
	}

	@Override
	protected final void execute(final CommandSender sender, final String[] args) throws CommandException {
		sender.sendMessage(String.format("%sLes joueurs ayant utilisé un cheat sont : %s", PrefixMessage.PREFIX,
				StringUtils.join(PlayerTestfor.getPlayerCheaterKeepMap().keySet(), " ")));
	}

}
