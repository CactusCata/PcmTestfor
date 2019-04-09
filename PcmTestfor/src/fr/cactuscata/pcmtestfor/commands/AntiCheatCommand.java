package fr.cactuscata.pcmtestfor.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import fr.cactuscata.pcmevent.command.NotSimpleCommand;
import fr.cactuscata.pcmtestfor.utils.PlayerTestfor;

public final class AntiCheatCommand extends NotSimpleCommand {

	private static final Map<Player, PlayerTestfor> verifiers = new HashMap<>();

	public AntiCheatCommand() {
		super(new AntiCheatCmdVerif(), new AntiCheatCmdReset(), new AntiCheatCmdList());
	}

	public static final Map<Player, PlayerTestfor> getVerifiers() {
		return AntiCheatCommand.verifiers;
	}

	@Override
	protected final String getTutorialCommand() {
		return "Grace � cette commande vous pourrez r�cuperer la liste de tous les cheaters, regardez les informations li�s au cheat ainsi que retirer ces derni�res.";
	}

}
