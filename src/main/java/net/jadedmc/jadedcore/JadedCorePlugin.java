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
import net.jadedmc.jadedcore.features.leaderboards.LeaderboardManager;
import net.jadedmc.jadedcore.features.player.JadedPlayerManager;
import net.jadedmc.jadedcore.listeners.*;
import net.jadedmc.jadedutils.gui.GUIListeners;
import net.jadedmc.jadedutils.scoreboard.ScoreboardUpdate;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class JadedCorePlugin extends JavaPlugin{
    private MySQL mySQL;
    private SettingsManager settingsManager;
    private JadedPlayerManager jadedPlayerManager;
    private AchievementManager achievementManager;
    private LeaderboardManager leaderboardManager;
    private HookManager hookManager;

    @Override
    public void onEnable() {
        // Plugin startup logic

        new JadedAPI(this);

        settingsManager = new SettingsManager(this);
        jadedPlayerManager = new JadedPlayerManager(this);

        mySQL = new MySQL(this);
        mySQL.openConnection();

        achievementManager = new AchievementManager(this);
        leaderboardManager = new LeaderboardManager(this);
        hookManager = new HookManager(this);

        AbstractCommand.registerCommands(this);

        getServer().getPluginManager().registerEvents(new ChannelMessageSendListener(this), this);
        getServer().getPluginManager().registerEvents(new GUIListeners(), this);
        getServer().getPluginManager().registerEvents(new PartySyncListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerCommandPreprocessListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);

        new UserDataRecalculateListener(this, LuckPermsProvider.get());

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        new Placeholders(this).register();

        // Updates scoreboards every second
        new ScoreboardUpdate().runTaskTimer(this, 20L, 20L);
    }

    /**
     * Get the Achievement Manager, which controls Achievements.
     * @return AchievementManager.
     */
    public AchievementManager achievementManager() {
        return achievementManager;
    }

    public HookManager hookManager() {
        return hookManager;
    }

    /**
     * Get the Leaderboard Manager, which controls leaderboards.
     * @return Leaderboard Manager.
     */
    public LeaderboardManager leaderboardManager() {
        return leaderboardManager;
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