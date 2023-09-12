package net.jadedmc.jadedcore.commands.misc;

import net.jadedmc.jadedcore.commands.AbstractCommand;
import net.jadedmc.jadedcore.utils.chat.ChatUtils;
import org.bukkit.command.CommandSender;

public class DiscordCMD extends AbstractCommand {

    public DiscordCMD() {
        super("discord,", "", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ChatUtils.chat(sender, "&a&lDiscord &8» &aJoin our discord server at &fhttp://discord.gg/YWGFeNA &a.");
    }
}
