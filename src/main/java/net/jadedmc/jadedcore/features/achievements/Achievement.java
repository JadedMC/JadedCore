package net.jadedmc.jadedcore.features.achievements;

import net.jadedmc.jadedcore.JadedCore;
import net.jadedmc.jadedcore.features.games.Game;
import net.jadedmc.jadedcore.features.player.JadedPlayer;
import net.jadedmc.jadedutils.chat.ChatUtils;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Achievement {
    private final JadedCore plugin;
    private final Game game;
    private final String id;
    private final String name;
    private final String description;
    private final int points;
    private final List<String> rewards = new ArrayList<>();

    public Achievement(final JadedCore plugin, final Game game, final String id, final String name, final String description, final int points, final String rewardsString) {
        this.plugin = plugin;
        this.game = game;
        this.id = id;
        this.name = name;
        this.description = description;
        this.points = points;

        // Add the rewards.
        rewards.add("<yellow>" + points + " Achievement Points</yellow>");
        rewards.addAll(Arrays.asList(rewardsString.split(";")));
    }

    public Game getGame() {
        return game;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPoints() {
        return points;
    }

    public String getID() {
        return id;
    }

    public List<String> getRewards() {
        return rewards;
    }

    public void unlock(Player player) {
        JadedPlayer jadedPlayer = plugin.jadedPlayerManager().getPlayer(player);

        if(jadedPlayer.getAchievements().contains(this)) {
            return;
        }

        jadedPlayer.getAchievements().add(this);

        StringBuilder rewardsString = new StringBuilder();

        for(String reward : rewards) {
            rewardsString.append("<newline>  ").append(reward);
        }

        String message = "<yellow><obf>#</obf><green>>> Achievement Unlocked: <hover:show_text:'<green>" + name + "</green><newline><gray>" + description + "</gray><newline><newline><gray>Rewards:</gray>" + rewardsString + "'><gold>" + name  + "</gold></hover> <green><<<yellow><obf>#";
        ChatUtils.chat(player, message);

        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                PreparedStatement statement = plugin.mySQL().getConnection().prepareStatement("INSERT INTO player_achievements (uuid,achievementID) VALUES (?,?)");
                statement.setString(1, player.getUniqueId().toString());
                statement.setString(2, id);
                statement.executeUpdate();
            }
            catch (SQLException exception) {
                exception.printStackTrace();
            }
        });
    }
}
