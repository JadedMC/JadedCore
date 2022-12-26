package net.jadedmc.jadedcore.listeners;

import net.jadedmc.jadedcore.JadedCore;
import net.jadedmc.jadedcore.features.player.staff.StaffPlayer;
import net.jadedmc.jadedcore.utils.chat.ChatUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandPreprocessListener implements Listener {
    private final JadedCore plugin;

    public PlayerCommandPreprocessListener(JadedCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String cmd = event.getMessage();

        for (StaffPlayer staff : plugin.staffPlayerManager().getCommandSpyEnabled()) {
            ChatUtils.chat(staff.getPlayer(), "&7[&aSpy&7] &a" + player.getName() + ": &f" + cmd);
        }
    }
}