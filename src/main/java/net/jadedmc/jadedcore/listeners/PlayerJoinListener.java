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
package net.jadedmc.jadedcore.listeners;

import net.jadedmc.jadedcore.JadedCore;
import net.jadedmc.jadedutils.chat.ChatUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private final JadedCore plugin;

    public PlayerJoinListener(JadedCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.joinMessage(null);

        Player player = event.getPlayer();
        plugin.jadedPlayerManager().addPlayer(player).thenAccept(jadedPlayer -> {

            // Join Message
            switch (jadedPlayer.getRank()) {
                case AMETHYST -> ChatUtils.broadcast(player.getWorld(), "&5>&f>&5> &lAmethyst &7" + player.getName() + " &ahas joined the lobby! &5<&f<&5<");
                case SAPPHIRE -> ChatUtils.broadcast(player.getWorld(), "&9>&f>&9> &lSapphire &7" + player.getName() + " &ahas joined the lobby! &9<&f<&9<");
                case JADED -> ChatUtils.broadcast(player.getWorld(), "&a>&f>&a> &lJaded &7" + player.getName() + " &ahas joined the lobby! &a<&f<&a<");
                default -> ChatUtils.broadcast(player.getWorld(), "&8[&a+&8] &a" + player.getName());
            }

            plugin.achievementManager().getAchievement("general_1").unlock(player);
        });
    }
}