package net.jadedmc.jadedcore.commands.misc;

import net.jadedmc.jadedcore.commands.AbstractCommand;
import net.jadedmc.jadedcore.utils.chat.ChatUtils;
import org.bukkit.command.CommandSender;

public class RulesCMD extends AbstractCommand {

    public RulesCMD() {
        super("rules", "", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ChatUtils.chat(sender, "&a&lRules &8» &aYou can view the server rules at &fhttp://www.jadedmc.net/rules&a.");
    }
}
