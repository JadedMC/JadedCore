package net.jadedmc.jadedcore.listeners;

import net.jadedmc.jadedcore.JadedCore;
import net.jadedmc.jadedcore.utils.chat.ChatUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    private final JadedCore plugin;

    public PlayerQuitListener(JadedCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        event.setQuitMessage(ChatUtils.translate("&8[&c-&8] &c") + player.getName());

        plugin.staffPlayerManager().removePlayer(player);
    }

}