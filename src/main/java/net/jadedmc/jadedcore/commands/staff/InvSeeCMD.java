package net.jadedmc.jadedcore.commands.staff;

import net.jadedmc.jadedcore.commands.AbstractCommand;
import net.jadedmc.jadedcore.utils.chat.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class runs the /invsee command, which allows someone to view the inventory of another player.
 */
public class InvSeeCMD extends AbstractCommand {

    /**
     * Creates the /invsee command with the permission "elytracore.invsee".
     */
    public InvSeeCMD() {
        super("invsee", "elytracore.invsee", false);
    }

    /**
     * This is the code that runs when the command is sent.
     * @param sender The player (or console) that sent the command.
     * @param args The arguments of the command.
     */
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 0) {
            ChatUtils.chat(sender, "&cUsage &8» &c/invsee [player]");
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if(target == null) {
            ChatUtils.chat(sender, "&cError &8» &c &cThat player is not online.");
            return;
        }

        Player player = (Player) sender;
        player.openInventory(target.getInventory());
    }
}