package net.jadedmc.jadedcore.listeners;

import net.jadedmc.jadedcore.JadedCore;
import net.jadedmc.jadedcore.utils.chat.ChatUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private final JadedCore plugin;

    public PlayerJoinListener(JadedCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(ChatUtils.translate("&8[&a+&8] &a") + event.getPlayer().getName());

        Player player = event.getPlayer();
        if(player.hasPermission("jadedmc.staff")) {
            plugin.staffPlayerManager().addPlayer(player);
        }
    }
}