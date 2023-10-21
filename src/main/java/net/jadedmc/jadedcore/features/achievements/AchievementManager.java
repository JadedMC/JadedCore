package net.jadedmc.jadedcore.features.achievements;

import net.jadedmc.jadedcore.JadedCore;
import net.jadedmc.jadedcore.features.games.Game;
import net.jadedmc.jadedcore.features.player.JadedPlayer;
import net.jadedmc.jadedutils.chat.ChatUtils;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AchievementManager {
    private final JadedCore plugin;
    private final Map<String, Achievement> achievements = new LinkedHashMap<>();

    public AchievementManager(final JadedCore plugin) {
        this.plugin = plugin;

        createAchievement(Game.GENERAL, "general_3", "Am I in Trouble?", "Beat a staff member in a game.", 5);
        createAchievement(Game.GENERAL, "general_1", "A Whole New World", "Join the server for the first time.", 5);
        createAchievement(Game.GENERAL, "general_4", "Better With Friends", "Create or join a party with other players.", 5);
        createAchievement(Game.GENERAL, "general_2", "Let My Voice Be Heard!", "Use chat for the first time.", 5, "<dark_aqua>5 Network Experience</dark_aqua>");
    }

    public void loadAchievements() {
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                PreparedStatement statement = plugin.mySQL().getConnection().prepareStatement("SELECT * FROM achievements_list");
                ResultSet resultSet = statement.executeQuery();

                while(resultSet.next()) {
                    achievements.put(resultSet.getString("id"), new Achievement(plugin, Game.valueOf(resultSet.getString("mode")), resultSet.getString("id"), resultSet.getString("name"), resultSet.getString("description"), resultSet.getInt("achievementPoints"), resultSet.getString("rewards")));
                }
            }
            catch (SQLException exception) {
                exception.printStackTrace();
            }
        });
    }

    public void createAchievement(final Game game, final String id, final String name, final String description, final int points, String... rewards) {

        String rewardsString = "";

        for(int i = 0; i < rewards.length; i++) {
            if(i != 0) {
                rewardsString += ";";
            }

            rewardsString += rewards[i];
        }

        String finalRewardsString = rewardsString;
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                PreparedStatement statement = plugin.mySQL().getConnection().prepareStatement("REPLACE INTO achievements_list (id,mode,name,description,achievementPoints,rewards) VALUES (?,?,?,?,?,?)");
                statement.setString(1, id);
                statement.setString(2, game.toString());
                statement.setString(3, name);
                statement.setString(4, description);
                statement.setInt(5, points);
                statement.setString(6, finalRewardsString);
                statement.executeUpdate();
            }
            catch (SQLException exception) {
                exception.printStackTrace();
            }
        });

        achievements.put(id, new Achievement(plugin, game, id, name, description, points, rewardsString));
    }

    public List<Achievement> getAchievements(Game game) {
        List<Achievement> gameAchievements = new ArrayList<>();

        for(Achievement achievement : achievements.values()) {
            if(achievement.getGame() == game) {
                gameAchievements.add(achievement);
            }
        }

        return gameAchievements;
    }

    public Achievement getAchievement(String id) {
        if(achievements.containsKey(id)) {
            return achievements.get(id);
        }

        return null;
    }
}
