package net.jadedmc.jadedcore.commands.misc;

import net.jadedmc.jadedcore.commands.AbstractCommand;
import net.jadedmc.jadedcore.utils.chat.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class runs the heal command, which heals the player who ran it.
 */
public class HealCMD extends AbstractCommand {

    /**
     * Creates the /heal command with the permission "jadedcore.heal".
     */
    public HealCMD() {
        super("heal", "jadedcore.heal", false);
    }

    /**
     * This is the code that runs when the command is sent.
     * @param sender The player (or console) that sent the command.
     * @param args The arguments of the command.
     */
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // Checks if the player meant to heal themselves.
        if(args.length == 0) {
            player.setHealth(player.getMaxHealth());
            ChatUtils.chat(player, "&a&lHeal &8» &aYou have been healed.");
            return;
        }

        // If not, makes sure they have permission to heal someone else.
        if(!player.hasPermission("jadedcore.heal.other")) {
            ChatUtils.chat(player,"&c&lError &8» &cYou do not have access to that command!");
            return;
        }

        // Gets the player the sender meant to heal.
        Player target = Bukkit.getPlayer(args[0]);

        // Makes sure they are online.
        if(target == null) {
            ChatUtils.chat(player,"&c&lError &8» &cThat player is not online!");
            return;
        }

        // Heals the player.
        target.setHealth(target.getMaxHealth());
        ChatUtils.chat(target, "&a&lHeal &8» &aYou have been healed.");
        ChatUtils.chat(player, "&a&lHeal &8» &aYou have healed &f" + target.getName() + "&a.");
    }
}