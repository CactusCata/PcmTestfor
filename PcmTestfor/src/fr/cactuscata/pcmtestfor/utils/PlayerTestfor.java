package fr.cactuscata.pcmtestfor.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.cactuscata.pcmevent.utils.bukkit.PlayerPcm;
import fr.cactuscata.pcmtestfor.cheat.Cheat;
import fr.cactuscata.pcmtestfor.cheat.list.AutoClick;
import fr.cactuscata.pcmtestfor.cheat.list.AutoClick.AutoClickType;
import fr.cactuscata.pcmtestfor.cheat.list.FastBow;

public final class PlayerTestfor extends PlayerPcm {

	private final static Map<Player, PlayerTestfor> playersTestfor = new HashMap<>();
	private final static Map<String, PlayerTestfor> playerCheaterKeep = new HashMap<>();

	private final List<Cheat> cheatsInformation = new ArrayList<>();
	private final AutoClick autoRightClick = new AutoClick(AutoClickType.AUTO_RIGHT_CLICK),
			autoLeftClick = new AutoClick(AutoClickType.AUTO_LEFT_CLICK);
	private final FastBow fastBow = new FastBow();

	public PlayerTestfor(final Player player) {
		super(player);
		PlayerTestfor.playersTestfor.put(player, this);
		this.cheatsInformation.add(autoRightClick);
		this.cheatsInformation.add(autoLeftClick);
		this.cheatsInformation.add(fastBow);
	}

	public static final void registerAllPlayers() {
		Bukkit.getOnlinePlayers().forEach(onlinePlayer -> new PlayerTestfor(onlinePlayer));
	}

	public static final void unregisterAllPlayers() {
		PlayerTestfor.playersTestfor.clear();
	}

	public static final void unregister(final Player player) {
		PlayerTestfor.playersTestfor.remove(player);
	}

	public static final PlayerTestfor getPlayerTestfor(final Player player) {
		return PlayerTestfor.playersTestfor.get(player);
	}

	public static final Collection<PlayerTestfor> getPlayersTestfor() {
		return PlayerTestfor.playersTestfor.values();
	}

	public static final void put(final Player player, final PlayerTestfor playerTestfor) {
		playersTestfor.put(player, playerTestfor);
	}

	public final AutoClick getAutoRightClick() {
		return autoRightClick;
	}

	public final AutoClick getAutoLeftClick() {
		return autoLeftClick;
	}

	public final FastBow getFastBow() {
		return this.fastBow;
	}

	public final void resetAllCheatsStats() {
		this.cheatsInformation.forEach(cheat -> cheat.reset());
		PlayerTestfor.playerCheaterKeep.remove(super.getPlayer().getName());
		super.removeIsCheater();
	}

	public final List<Cheat> getCheatInformation() {
		return this.cheatsInformation;
	}

	public final boolean isCleanPlayer() {
		return !super.isCheater();
	}

	public final void setBadPlayer() {
		PlayerTestfor.playerCheaterKeep.put(super.getPlayer().getName(), this);
		super.setCheater();
	}

	public static final Map<String, PlayerTestfor> getPlayerCheaterKeepMap() {
		return PlayerTestfor.playerCheaterKeep;
	}

	public final int getNumberOfTotalAlert() {
		int alert = 0;
		for (final Cheat cheat : this.cheatsInformation)
			alert += cheat.getNumberOfAlert();
		return alert;
	}

	public final List<String> getAllWarn() {
		final List<String> warnList = new ArrayList<>();

		this.cheatsInformation.forEach(cheat -> cheat.getWarnList().forEach(warn -> warnList.add(warn)));

		return warnList;
	}

}
