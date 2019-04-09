package fr.cactuscata.pcmtestfor;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.cactuscata.pcmtestfor.cheat.CheckCheatRunnable;
import fr.cactuscata.pcmtestfor.commands.AntiCheatCommand;
import fr.cactuscata.pcmtestfor.listeners.AutoClickListener;
import fr.cactuscata.pcmtestfor.listeners.FastBowListerner;
import fr.cactuscata.pcmtestfor.listeners.JoinGame;
import fr.cactuscata.pcmtestfor.listeners.LeaveGame;
import fr.cactuscata.pcmtestfor.listeners.inventory.InventoryInteraction;
import fr.cactuscata.pcmtestfor.listeners.inventory.InventoryUpdateRunnable;
import fr.cactuscata.pcmtestfor.utils.PlayerTestfor;

public final class PcmTestfor extends JavaPlugin {

	@Override
	public final void onEnable() {

		PlayerTestfor.registerAllPlayers();

		new InventoryUpdateRunnable().runTaskTimerAsynchronously(this, 0L, 20L);
		new CheckCheatRunnable().runTaskTimerAsynchronously(this, 0L, 20L);

		final PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new AutoClickListener(), this);
		pm.registerEvents(new FastBowListerner(), this);
		pm.registerEvents(new JoinGame(), this);
		pm.registerEvents(new LeaveGame(), this);
		pm.registerEvents(new InventoryInteraction(), this);

		getCommand("anticheat").setExecutor(new AntiCheatCommand());

	}

	@Override
	public final void onDisable() {
		PlayerTestfor.unregisterAllPlayers();
	}

}
