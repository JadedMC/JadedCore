package net.jadedmc.jadedcore;

import net.jadedmc.jadedcore.commands.AbstractCommand;
import net.jadedmc.jadedcore.features.player.staff.StaffPlayerManager;
import net.jadedmc.jadedcore.listeners.*;
import net.jadedmc.jadedcore.utils.gui.GUIListeners;
import org.bukkit.plugin.java.JavaPlugin;

public final class JadedCore extends JavaPlugin{
    private MySQL mySQL;
    private SettingsManager settingsManager;
    private StaffPlayerManager staffPlayerManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        new JadedAPI(this);

        settingsManager = new SettingsManager(this);
        staffPlayerManager = new StaffPlayerManager(this);

        mySQL = new MySQL(this);
        mySQL.openConnection();

        AbstractCommand.registerCommands(this);

        getServer().getPluginManager().registerEvents(new GUIListeners(), this);
        getServer().getPluginManager().registerEvents(new PlayerCommandPreprocessListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
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