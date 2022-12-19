package net.jadedmc.jadedcore.staff.commands;

import net.jadedmc.jadedcore.JadedCore;
import net.jadedmc.jadedcore.utils.chat.commands.AbstractCommand;
import net.jadedmc.jadedcore.staff.player.StaffPlayer;
import net.jadedmc.jadedcore.utils.chat.ChatUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class runs the vanish command, which hides a player from all others.
 */
public class VanishCMD extends AbstractCommand {
    private final JadedCore plugin;

    /**
     * Creates the /vanish command with the permission "elytracore.vanish".
     * @param plugin Instance of the plugin.
     */
    public VanishCMD(JadedCore plugin) {
        super("vanish", "jadedcore.vanish", false);
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

        if(staffPlayer.isVanished()) {
            staffPlayer.setVanished(false);
            ChatUtils.chat(player, "&aYou are no longer vanished.");
        }
        else {
            staffPlayer.setVanished(true);
            ChatUtils.chat(player, "&aYou are now vanished.");
        }
    }
}