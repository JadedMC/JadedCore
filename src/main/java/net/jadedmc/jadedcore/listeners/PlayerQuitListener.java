package net.jadedmc.jadedcore.listeners;

import net.jadedmc.jadedcore.JadedAPI;
import net.jadedmc.jadedcore.features.party.Party;
import net.jadedmc.jadedcore.utils.chat.ChatUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        event.setQuitMessage(ChatUtils.translate("&8[&c-&8] &c") + player.getName());

        Party party = JadedAPI.getPlugin().partyManager().getParty(player);
        if(party != null) {
            if(party.getOnlineCount() == 1) {
                JadedAPI.getPlugin().partyManager().disbandParty(party.getUUID());
            }
        }
    }

}