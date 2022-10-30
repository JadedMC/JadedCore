package net.jadedmc.jadedcore;

import net.jadedmc.jadedcore.staff.player.StaffPlayerManager;
import net.jadedmc.jadedcore.utils.chat.commands.AbstractCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class JadedCore extends JavaPlugin {
    private MySQL mySQL;
    private SettingsManager settingsManager;
    private StaffPlayerManager staffPlayerManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        settingsManager = new SettingsManager(this);
        staffPlayerManager = new StaffPlayerManager(this);

        mySQL = new MySQL(this);
        mySQL.openConnection();

        AbstractCommand.registerCommands(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
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