package net.jadedmc.jadedcore.commands.staff;

import net.jadedmc.jadedcore.commands.AbstractCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class runs the workbench command, which opens a virtual crafting table.
 */
public class WorkbenchCMD extends AbstractCommand {

    /**
     * Creates the /workbench command with the permission "jadedcore.workbench".
     */
    public WorkbenchCMD() {
        super("workbench", "jadedcore.workbench", false);
    }

    /**
     * This is the code that runs when the command is sent.
     * @param sender The player (or console) that sent the command.
     * @param args The arguments of the command.
     */
    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        player.openWorkbench(player.getLocation(), true);
    }
}