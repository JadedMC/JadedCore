package net.jadedmc.jadedcore;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

/**
 * Allows easy access to plugin configuration
 * files. Stores spawn and arena locations.
 */
public class SettingsManager {
    private final FileConfiguration config;
    private final File configFile;

    public SettingsManager(JadedCore plugin) {
        config = plugin.getConfig();
        config.options().copyDefaults(true);
        configFile = new File(plugin.getDataFolder(), "config.yml");
        plugin.saveConfig();
    }

    /**
     * Get the main configuration file.
     * @return Main configuration file.
     */
    public FileConfiguration getConfig() {
        return config;
    }
}