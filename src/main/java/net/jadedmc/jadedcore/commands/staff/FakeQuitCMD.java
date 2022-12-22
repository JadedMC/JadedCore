package net.jadedmc.jadedcore.commands.staff;

import net.jadedmc.jadedcore.commands.AbstractCommand;
import net.jadedmc.jadedcore.utils.chat.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class runs the fakejoin command, which broadcasts a fake quit message.
 */
public class FakeQuitCMD extends AbstractCommand {

    /**
     * Creates the /fakequit command with the permission "elytracore.fakequit".
     */
    public FakeQuitCMD() {
        super("fakequit", "elytracore.fakequit", false);
    }

    /**
     * This is the code that runs when the command is sent.
     * @param sender The player (or console) that sent the command.
     * @param args The arguments of the command.
     */
    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        Bukkit.broadcastMessage(ChatUtils.translate("&8[&c-&8] &c" + player.getName()));
    }
}