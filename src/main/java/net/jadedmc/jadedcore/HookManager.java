package net.jadedmc.jadedcore;

public class HookManager {
    private final JadedCorePlugin plugin;

    public HookManager(final JadedCorePlugin plugin) {
        this.plugin = plugin;
    }

    public boolean useHyNick() {
        return plugin.getServer().getPluginManager().isPluginEnabled("HyNick");
    }
}