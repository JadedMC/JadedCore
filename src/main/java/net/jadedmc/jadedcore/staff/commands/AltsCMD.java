package net.jadedmc.jadedcore.staff.commands;

import net.jadedmc.jadedcore.JadedCore;
import net.jadedmc.jadedcore.utils.chat.commands.AbstractCommand;
import net.jadedmc.jadedcore.utils.chat.ChatUtils;
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