package net.jadedmc.jadedcore.listeners;

import net.jadedmc.jadedcore.utils.chat.ChatUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(ChatUtils.translate("&8[&a+&8] &a") + event.getPlayer().getName());
    }

}
