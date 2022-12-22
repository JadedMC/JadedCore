package net.jadedmc.jadedcore.commands.misc;

import net.jadedmc.jadedcore.commands.AbstractCommand;
import net.jadedmc.jadedcore.utils.chat.ChatUtils;
import net.jadedmc.jadedcore.utils.chat.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class runs the broadcast command, which allows the server to broadcast a message.
 */
public class BroadcastCMD extends AbstractCommand {

    /**
     * Creates the /broadcast command with the permission "jadedcore.broadcast".
     */
    public BroadcastCMD() {
        super("broadcast", "jadedcore.broadcast", true);
    }

    /**
     * This is the code that runs when the command is sent.
     * @param sender The player (or console) that sent the command.
     * @param args The arguments of the command.
     */
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 0) {
            ChatUtils.chat(sender, "&c&lUsage &8» &c/bc [message]");
            return;
        }

        for(Player player : Bukkit.getOnlinePlayers()) {
            ChatUtils.chat(player, StringUtils.join(args, " "));
        }
    }
}