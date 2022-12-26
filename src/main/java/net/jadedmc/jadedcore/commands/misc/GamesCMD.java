package net.jadedmc.jadedcore.commands.misc;

import net.jadedmc.jadedcore.commands.AbstractCommand;
import net.jadedmc.jadedcore.features.games.GamesGUI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamesCMD extends AbstractCommand {

    public GamesCMD() {
        super("games", "", false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        new GamesGUI().open(player);
    }
}