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
package net.jadedmc.jadedcore.features.player;

import net.jadedmc.jadedcore.JadedCore;
import net.jadedmc.jadedcore.features.player.staff.StaffPlayer;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.entity.Player;

/**
 * Represents a Player on the server. Stores plugin-specific data about them.
 */
public class JadedPlayer {
    private final JadedCore plugin;
    private final Player player;
    private Rank rank;

    /**
     * Creates the JadedPlayer
     * @param plugin Instance of the plugin.
     * @param player Player object to use.
     */
    public JadedPlayer(final JadedCore plugin, final Player player) {
        this.plugin = plugin;
        this.player = player;

        // Update the player's rank.
        this.rank = Rank.fromName(LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup());
    }

    /**
     * Gets the rank of the player.
     * @return Player's rank.
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Get the player's staff player object.
     * Returns null if the player is not a staff member.
     * @return StaffPlayer object.
     */
    public StaffPlayer getStaffPlayer() {
        if(!isStaffMember()) {
            return null;
        }

        return plugin.staffPlayerManager().getPlayer(player);
    }

    /**
     * Check if the player has the permissions of a specific rank.
     * @param toCheck Rank to check.
     * @return Whether they have the permissions of it or not.
     */
    public boolean hasRank(Rank toCheck) {
        return (rank.getWeight() >= toCheck.getWeight());
    }

    /**
     * Get whether the player is a staff member.
     * @return If the player is a staff member.
     */
    public boolean isStaffMember() {
        return rank.isStaffRank();
    }
}