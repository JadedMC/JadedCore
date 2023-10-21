package net.jadedmc.jadedcore.features.achievements;

import net.jadedmc.jadedcore.JadedCore;
import net.jadedmc.jadedcore.features.ProfileGUI;
import net.jadedmc.jadedcore.features.games.Game;
import net.jadedmc.jadedcore.features.player.JadedPlayer;
import net.jadedmc.jadedcore.utils.gui.CustomGUI;
import net.jadedmc.jadedcore.utils.item.SkullBuilder;
import net.jadedmc.jadedutils.MathUtils;
import net.jadedmc.jadedutils.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AchievementsGUI extends CustomGUI {
    private final JadedCore plugin;

    public AchievementsGUI(final JadedCore plugin, Player player) {
        super(54, "Achievements");
        this.plugin = plugin;

        addFiller(1,2,3,4,5,6,7,8,45,46,47,48,49,50,51,52,53);

        Game[] games = new Game[]{Game.GENERAL, Game.CACTUS_RUSH, Game.ELYTRAPVP, Game.HOUSING};
        int[] gameSlots = new int[]{19,20,21,22,23,24,25,28,29,30,31,32,33,34};

        ItemStack back = new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjg0ZjU5NzEzMWJiZTI1ZGMwNThhZjg4OGNiMjk4MzFmNzk1OTliYzY3Yzk1YzgwMjkyNWNlNGFmYmEzMzJmYyJ9fX0=")
                .setDisplayName("&cBack")
                .build();
        setItem(0, back, (p, a) -> new ProfileGUI(plugin, p).open(p));

        JadedPlayer jadedPlayer = plugin.jadedPlayerManager().getPlayer(player);

        int i = 0;
        for(Game game : games) {
            List<Achievement> totalAchievements = plugin.achievementManager().getAchievements(game);
            List<Achievement> playerAchievements = new ArrayList<>();

            int playerPoints = 0;

            for(Achievement achievement : jadedPlayer.getAchievements()) {
                if(achievement.getGame() != game) {
                    continue;
                }

                playerAchievements.add(achievement);
                playerPoints += achievement.getPoints();
            }

            int totalPoints = 0;

            for(Achievement achievement : totalAchievements) {
                totalPoints += achievement.getPoints();
            }

            ItemStack item = new ItemBuilder(game.getIconMaterial().parseMaterial())
                    .setDisplayName("<green>" + game.getName())
                    .addLore("<gray>Unlocked: <green>" + playerAchievements.size() + "<gray>/<green>" + totalAchievements.size() + " <dark_gray>(" + MathUtils.percent(playerAchievements.size(), totalAchievements.size()) + "%)")
                    .addLore("<gray>Points: <yellow>" + playerPoints + "<gray>/<yellow>" + totalPoints + " <dark_gray>(" + MathUtils.percent(playerPoints, totalPoints) + "%)")
                    .addLore("")
                    .addLore("&aClick to view achievements!")
                    .build();
            setItem(gameSlots[i], item, (p,a) -> new AchievementsGUI(plugin, p, game).open(p));
            i++;
        }
    }

    public AchievementsGUI(final JadedCore plugin, Player player, Game game) {
        super(54, "Achievements - " + game.getName());
        this.plugin = plugin;

        addFiller(1,2,3,4,5,6,7,8,45,46,47,48,49,50,51,52,53);
        ItemStack back = new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjg0ZjU5NzEzMWJiZTI1ZGMwNThhZjg4OGNiMjk4MzFmNzk1OTliYzY3Yzk1YzgwMjkyNWNlNGFmYmEzMzJmYyJ9fX0=")
                .setDisplayName("&cBack")
                .build();
        setItem(0, back, (p, a) -> new AchievementsGUI(plugin, p).open(p));

        JadedPlayer jadedPlayer = plugin.jadedPlayerManager().getPlayer(player);

        int i = 9;
        for(Achievement achievement : plugin.achievementManager().getAchievements(game)) {

            if(jadedPlayer.getAchievements().contains(achievement)) {
                ItemBuilder builder = new ItemBuilder(Material.DIAMOND)
                        .setDisplayName("<green>" + achievement.getName())
                        .addLore("<gray>" + achievement.getDescription())
                        .addLore("")
                        .addLore("<gray>Rewards:");

                for(String reward : achievement.getRewards()) {
                    builder.addLore("  " + reward);
                }
                setItem(i, builder.build());
            }
            else {
                ItemBuilder builder = new ItemBuilder(Material.COAL)
                        .setDisplayName("<red>" + achievement.getName())
                        .addLore("<gray>" + achievement.getDescription())
                        .addLore("")
                        .addLore("<gray>Rewards:");

                for(String reward : achievement.getRewards()) {
                    builder.addLore("  " + reward);
                }

                setItem(i, builder.build());
            }


            i++;
        }
    }
}
