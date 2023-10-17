package net.jadedmc.jadedcore.features;

import net.jadedmc.jadedcore.JadedCore;
import net.jadedmc.jadedcore.features.player.JadedPlayer;
import net.jadedmc.jadedcore.utils.gui.CustomGUI;
import net.jadedmc.jadedutils.items.ItemBuilder;
import net.jadedmc.jadedutils.items.SkullBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.SimpleDateFormat;

public class ProfileGUI extends CustomGUI {

    public ProfileGUI(JadedCore plugin, Player player) {
        super(54, "Profile");
        JadedPlayer jadedPlayer = plugin.jadedPlayerManager().getPlayer(player);

        addFiller(0,1,2,3,4,5,6,7,8,45,46,47,48,49,50,51,52,53);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String firstJoined = dateFormat.format(jadedPlayer.getFirstJoined());

        ItemStack characterInfo = new SkullBuilder(player).asItemBuilder()
                .setDisplayName("<green><bold>Character Info")
                .addLore("<gray>Player: <green>" + player.getName())
                .addLore("<gray>Rank: " + jadedPlayer.getRank().getDisplayName())
                .addLore("")
                .addLore("<gray>Level: <green>" + jadedPlayer.getLevel())
                .addLore("<gray>Experience: <green>" + jadedPlayer.getExperience())
                .addLore("")
                .addLore("<gray>First Joined: <green>" + firstJoined)
                .build();
        setItem(22, characterInfo);

        ItemStack achievements = new ItemBuilder(Material.DIAMOND)
                .setDisplayName("<green><bold>Achievements")
                .build();
        setItem(31, achievements);
    }
}