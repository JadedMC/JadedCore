package net.jadedmc.jadedcore.commands.staff;

import net.jadedmc.jadedcore.JadedCore;
import net.jadedmc.jadedcore.commands.AbstractCommand;
import net.jadedmc.jadedcore.features.player.staff.StaffPlayer;
import net.jadedmc.jadedcore.utils.chat.ChatUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class runs the commandspy command, which spies on all commands being used.
 */
public class CommandSpyCMD extends AbstractCommand {
    private final JadedCore plugin;

    /**
     * Creates the /commandspy command with the permission "jadedcore.commandspy".
     * @param plugin Instance of the plugin.
     */
    public CommandSpyCMD(JadedCore plugin) {
        super("commandspy", "jadedcore.commandspy", false);
        this.plugin = plugin;
    }

    /**
     * This is the code that runs when the command is sent.
     * @param sender The player (or console) that sent the command.
     * @param args The arguments of the command.
     */
    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        StaffPlayer staffPlayer = plugin.staffPlayerManager().getPlayer(player);

        if(staffPlayer.isSpying()) {
            staffPlayer.setSpying(false);
            ChatUtils.chat(player, "&aYou are no longer spying on commands.");
        }
        else {
            staffPlayer.setSpying(true);
            ChatUtils.chat(player, "&aYou are now spying on commands.");
        }
    }
}