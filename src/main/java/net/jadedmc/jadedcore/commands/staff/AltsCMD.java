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
package net.jadedmc.jadedcore.commands.staff;

import net.jadedmc.jadedcore.JadedCore;
import net.jadedmc.jadedcore.commands.AbstractCommand;
import net.jadedmc.jadedutils.chat.ChatUtils;
import org.bukkit.command.CommandSender;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class runs the alts command, which allows to check all accounts using the same ip.
 */
public class AltsCMD extends AbstractCommand {
    private final JadedCore plugin;

    /**
     * Creates the /alts command with the permission "jadedcore.alts".
     * @param plugin Instance of the plugin.
     */
    public AltsCMD(JadedCore plugin) {
        super("alts", "jadedcore.alts", true);

        this.plugin = plugin;
    }

    /**
     * This is the code that runs when the command is sent.
     * @param sender The player (or console) that sent the command.
     * @param args The arguments of the command.
     */
    @Override
    public void execute(CommandSender sender, String[] args) {
        // Make sure they're using the command properly.
        if(args.length < 1) {
            ChatUtils.chat(sender, "&c&lUsage &8» &c/alts [player]");
            return;
        }

        // Runs MySQL async.
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                PreparedStatement statement = plugin.mySQL().getConnection().prepareStatement("SELECT * FROM player_info WHERE username = ?");
                statement.setString(1, args[0]);
                ResultSet results = statement.executeQuery();

                if(results.next()) {
                    String target = results.getString(2);
                    String ip = results.getString(3);

                    PreparedStatement statement2 = plugin.mySQL().getConnection().prepareStatement("SELECT * FROM player_info WHERE ip = ?");
                    statement2.setString(1, ip);
                    ResultSet results2 = statement2.executeQuery();

                    ChatUtils.chat(sender, "&aAlts of &f" + target + "&a:");
                    while(results2.next()) {
                        ChatUtils.chat(sender, "&7- &f" + results2.getString(2));
                    }
                }
                else {
                    ChatUtils.chat(sender, "&cError &8» &cThat player has not played.");
                }
            }
            catch (SQLException exception) {
                exception.printStackTrace();
            }
        });
    }
}