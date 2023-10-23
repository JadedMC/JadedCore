/*
 * This file is part of JadedCore, licensed under the MIT License.
 *
 *  Copyright (c) JadedMC
 *  Copyright (c) contributors
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */
package net.jadedmc.jadedcore;

import net.jadedmc.jadedcore.commands.AbstractCommand;
import net.jadedmc.jadedcore.features.achievements.AchievementManager;
import net.jadedmc.jadedcore.features.player.JadedPlayerManager;
import net.jadedmc.jadedcore.listeners.*;
import net.jadedmc.jadedcore.utils.gui.GUIListeners;
import net.jadedmc.jadedutils.chat.ChatUtils;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;

public final class JadedCore extends JavaPlugin{
    private MySQL mySQL;
    private SettingsManager settingsManager;
    private JadedPlayerManager jadedPlayerManager;
    private AchievementManager achievementManager;

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Initialize an audiences instance for the plugin
        BukkitAudiences adventure = BukkitAudiences.create(this);
        ChatUtils.setAdventure(adventure);

        new JadedAPI(this);

        settingsManager = new SettingsManager(this);
        jadedPlayerManager = new JadedPlayerManager(this);

        mySQL = new MySQL(this);
        mySQL.openConnection();

        achievementManager = new AchievementManager(this);

        AbstractCommand.registerCommands(this);

        getServer().getPluginManager().registerEvents(new GUIListeners(), this);
        getServer().getPluginManager().registerEvents(new PlayerCommandPreprocessListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        new Placeholders(this).register();
    }

    public AchievementManager achievementManager() {
        return achievementManager;
    }

    /**
     * Be able to connect to MySQL.
     * @return MySQL.
     */
    public MySQL mySQL() {
        return mySQL;
    }

    /**
     * Get the Jaded Player manager, which manages player data.
     * @return Jaded Player manager.
     */
    public JadedPlayerManager jadedPlayerManager() {
        return jadedPlayerManager;
    }

    /**
     * Get the Settings Manager, which gives us access to the plugin Configuration.
     * @return Settings Manager.
     */
    public SettingsManager settingsManager() {
        return settingsManager;
    }
}