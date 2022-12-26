package net.jadedmc.jadedcore.commands.misc;

import net.jadedmc.jadedcore.commands.AbstractCommand;
import net.jadedmc.jadedcore.utils.chat.ChatUtils;
import net.jadedmc.jadedcore.utils.chat.StringUtils;
import net.jadedmc.jadedcore.utils.item.ItemBuilder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * This class runs the rename command, which allows to a player to rename an item.
 */
public class RenameCMD extends AbstractCommand {

    /**
     * Creates the /rename command with the permission "jadedcore.rename".
     */
    public RenameCMD() {
        super("rename", "jadedcore.rename", false);
    }

    /**
     * This is the code that runs when the command is sent.
     * @param sender The player (or console) that sent the command.
     * @param args The arguments of the command.
     */
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // Exit if no arguments
        if(args.length == 0) {
            ChatUtils.chat(player,"&c&lUsage &8» &c/rename [name]");
            return;
        }

        String name = StringUtils.join(args, " ");

        ItemStack item = player.getItemInHand();
        ItemStack renamed = new ItemBuilder(item)
                .setDisplayName(name)
                .build();
        player.setItemInHand(renamed);

        ChatUtils.chat(player, "&a&lRename &8» &aItem's name set to &f" + name + "&a.");
    }
}