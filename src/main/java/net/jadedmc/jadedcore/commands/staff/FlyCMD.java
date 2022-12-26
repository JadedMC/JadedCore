package net.jadedmc.jadedcore.commands.staff;

import net.jadedmc.jadedcore.commands.AbstractCommand;
import net.jadedmc.jadedcore.utils.chat.ChatUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class runs the fly command, which allows a player to toggle the ability to fly.
 */
public class FlyCMD extends AbstractCommand {

    /**
     * Creates the /fly command with the permission "jadedcore.rename".
     */
    public FlyCMD() {
        super("fly", "jadedcore.fly", false);
    }

    /**
     * This is the code that runs when the command is sent.
     * @param sender The player (or console) that sent the command.
     * @param args The arguments of the command.
     */
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if(player.getAllowFlight()) {
            ChatUtils.chat(player, "&a&lFly &8» &aFlight has been disabled.");
            player.setAllowFlight(false);
            player.setFlying(false);
        }
        else {
            ChatUtils.chat(player, "&a&lFly &8» &aFlight has been enabled.");
            player.setAllowFlight(true);
            player.setFlying(true);
        }
    }
}