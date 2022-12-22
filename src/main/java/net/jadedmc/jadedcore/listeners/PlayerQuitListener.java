package net.jadedmc.jadedcore.listeners;

import net.jadedmc.jadedcore.utils.chat.ChatUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(ChatUtils.translate("&8[&c-&8] &c") + event.getPlayer().getName());
    }

}