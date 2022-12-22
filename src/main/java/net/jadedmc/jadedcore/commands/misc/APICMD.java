package net.jadedmc.jadedcore.commands.misc;

import net.jadedmc.jadedcore.JadedCore;
import net.jadedmc.jadedcore.utils.chat.ChatUtils;
import net.jadedmc.jadedcore.commands.AbstractCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class APICMD extends AbstractCommand {
    private final JadedCore plugin;

    public APICMD(JadedCore plugin) {
        super("api", "", false);
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        UUID key = UUID.randomUUID();

        ChatUtils.chat(player, "&aYour new API key is: &f" + key.toString());

        try {
            PreparedStatement statement = plugin.mySQL().getConnection().prepareStatement("REPLACE INTO api_keys (uuid, apiKey) VALUES (?,?)");
            statement.setString(1, player.getUniqueId().toString());
            statement.setString(2, key.toString());
            statement.executeUpdate();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
