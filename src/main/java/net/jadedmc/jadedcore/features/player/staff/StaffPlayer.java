package net.jadedmc.jadedcore.features.player.staff;

import net.jadedmc.jadedcore.JadedCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class stores data needed by a staff member.
 */
public class StaffPlayer {
    private final JadedCore plugin;
    private final Player player;
    private boolean spying;
    private boolean vanished;

    /**
     * Creates a StaffPlayer object.
     *
     * @param plugin Instance of the plugin.
     * @param player Player to create object of.
     */
    public StaffPlayer(JadedCore plugin, Player player) {
        this.plugin = plugin;
        this.player = player;

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                PreparedStatement statement = plugin.mySQL().getConnection().prepareStatement("SELECT * FROM staff_settings WHERE uuid = ? LIMIT 1");
                statement.setString(1, player.getUniqueId().toString());
                ResultSet results = statement.executeQuery();

                if (results.next()) {
                    spying = results.getBoolean(3);
                    vanished = results.getBoolean(2);
                } else {
                    PreparedStatement statement2 = plugin.mySQL().getConnection().prepareStatement("INSERT INTO staff_settings (uuid) VALUES (?)");
                    statement2.setString(1, player.getUniqueId().toString());
                    statement2.executeUpdate();

                    spying = false;
                    vanished = false;
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        });
    }

    /**
     * Get the player of the StaffPlayer object.
     *
     * @return Player of the StaffPlayer.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get if the player is spying on commands.
     *
     * @return Whether they are spying on the commands.
     */
    public boolean isSpying() {
        return spying;
    }

    /**
     * Get if the player is vanished.
     *
     * @return Whether they are vanished.
     */
    public boolean isVanished() {
        return vanished;
    }

    /**
     * Set if the player is spying on commands.
     *
     * @param spying Whether they are spying on commands.
     */
    public void setSpying(boolean spying) {
        this.spying = spying;

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                PreparedStatement statement = plugin.mySQL().getConnection().prepareStatement("UPDATE staff_settings SET spy = ? WHERE uuid = ?");
                statement.setBoolean(1, spying);
                statement.setString(2, player.getUniqueId().toString());
                statement.executeUpdate();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        });
    }

    /**
     * Set if the player is currently vanished.
     *
     * @param vanished Whether they are vanished.
     */
    public void setVanished(boolean vanished) {
        this.vanished = vanished;

        if (!vanished) {
            for (Player pl : Bukkit.getOnlinePlayers()) {
                if (pl.equals(player)) {
                    continue;
                }

                pl.showPlayer(player);
            }
        } else {
            for (Player pl : Bukkit.getOnlinePlayers()) {
                if (pl.equals(player)) {
                    continue;
                }

                pl.hidePlayer(player);
            }
        }

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                PreparedStatement statement = plugin.mySQL().getConnection().prepareStatement("UPDATE staff_settings SET vanish = ? WHERE uuid = ?");
                statement.setBoolean(1, vanished);
                statement.setString(2, player.getUniqueId().toString());
                statement.executeUpdate();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        });
    }
}