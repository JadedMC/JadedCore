package net.jadedmc.jadedcore.commands.staff;

import net.jadedmc.jadedcore.commands.AbstractCommand;
import net.jadedmc.jadedcore.utils.chat.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class runs the ecesee command, which allows a player to view someone's enderchest.
 */
public class ECSeeCMD extends AbstractCommand {

    /**
     * Creates the /ecsee command with the permission "jadedcore.ecsee".
     */
    public ECSeeCMD() {
        super("ecsee", "jadedcore.ecsee", false);
    }

    /**
     * This is the code that runs when the command is sent.
     * @param sender The player (or console) that sent the command.
     * @param args The arguments of the command.
     */
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 0) {
            ChatUtils.chat(sender, "&cUsage &8» &c/ecsee [player]");
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if(target == null) {
            ChatUtils.chat(sender, "&cError &8» &cThat player is not online.");
            return;
        }

        Player player = (Player) sender;
        player.openInventory(target.getEnderChest());
    }
}