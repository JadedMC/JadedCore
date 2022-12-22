package net.jadedmc.jadedcore.features.player.staff;

import net.jadedmc.jadedcore.JadedCore;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class manages the Staff Player object, which stores all data needed to be saved for staff.
 */
public class StaffPlayerManager {
    private final JadedCore plugin;
    private final Map<Player,StaffPlayer> staffPlayers = new HashMap();

    /**
     * Initializes the Staff Player Manager.
     * @param plugin Instance of the plugin.
     */
    public StaffPlayerManager(JadedCore plugin) {
        this.plugin = plugin;
    }

    /**
     * Add a player to the staff player list.
     * @param player Player to add.
     */
    public void addPlayer(Player player) {
        staffPlayers.put(player, new StaffPlayer(plugin, player));
    }

    /**
     * Get the staff players that have command spy enabled.
     * @return All staff players with command spy enabled.
     */
    public Set<StaffPlayer> getCommandSpyEnabled() {
        Set<StaffPlayer> commandSpyEnabled = new HashSet<>();

        for(StaffPlayer staffPlayer : staffPlayers.values()) {
            if(staffPlayer.isSpying()) {
                commandSpyEnabled.add(staffPlayer);
            }
        }

        return commandSpyEnabled;
    }

    /**
     * Get the staff player of a player
     * @param player Player to get staff player of.
     * @return Staff player of the player.
     */
    public StaffPlayer getPlayer(Player player) {
        return staffPlayers.get(player);
    }

    /**
     * Get the staff players that have vanish enabled.
     * @return All staff players with vanish enable.
     */
    public Set<StaffPlayer> getVanishEnabled() {
        Set<StaffPlayer> vanishEnabled = new HashSet<>();

        for(StaffPlayer staffPlayer : staffPlayers.values()) {
            if(staffPlayer.isVanished()) {
                vanishEnabled.add(staffPlayer);
            }
        }

        return vanishEnabled;
    }

    /**
     * Remove a player from the staff player list.
     * @param player Player to remove.
     */
    public void removePlayer(Player player) {
        staffPlayers.remove(player);
    }
}