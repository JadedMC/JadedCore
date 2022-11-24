package net.jadedmc.jadedcore;

import net.jadedmc.jadedcore.chat.filter.FilterManager;
import net.jadedmc.jadedcore.chat.listeners.AsyncPlayerChatListener;
import net.jadedmc.jadedcore.chat.listeners.PlayerJoinListener;
import net.jadedmc.jadedcore.chat.listeners.PlayerQuitListener;
import net.jadedmc.jadedcore.staff.player.StaffPlayerManager;
import net.jadedmc.jadedcore.utils.chat.commands.AbstractCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class JadedCore extends JavaPlugin {
    private MySQL mySQL;
    private SettingsManager settingsManager;
    private StaffPlayerManager staffPlayerManager;
    private FilterManager filterManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        settingsManager = new SettingsManager(this);
        staffPlayerManager = new StaffPlayerManager(this);
        filterManager = new FilterManager();

        mySQL = new MySQL(this);
        mySQL.openConnection();

        AbstractCommand.registerCommands(this);

        getServer().getPluginManager().registerEvents(new AsyncPlayerChatListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * Get the filter manager, which manages the chat filter.
     * @return Filter manager.
     */
    public FilterManager filterManager() {
        return  filterManager;
    }

    /**
     * Be able to connect to MySQL.
     * @return MySQL.
     */
    public MySQL mySQL() {
        return mySQL;
    }

    /**
     * Get the Settings Manager, which gives us access to the plugin Configuration.
     * @return Settings Manager.
     */
    public SettingsManager settingsManager() {
        return settingsManager;
    }

    /**
     * Get the Staff Player manager, which manages StaffPlayer objects.
     * @return Staff player manager.
     */
    public StaffPlayerManager staffPlayerManager() {
        return staffPlayerManager;
    }
}