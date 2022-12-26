package net.jadedmc.jadedcore.commands.misc;

import net.jadedmc.jadedcore.commands.AbstractCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class runs the enderchest command, which opens the player's enderchest.
 */
public class EnderchestCMD extends AbstractCommand {

    /**
     * Creates the /enderchest command with the permission "jadedcore.enderchest".
     */
    public EnderchestCMD() {
        super("enderchest", "jadedcore.enderchest", false);
    }

    /**
     * This is the code that runs when the command is sent.
     * @param sender The player (or console) that sent the command.
     * @param args The arguments of the command.
     */
    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        player.openInventory(player.getEnderChest());
    }
}