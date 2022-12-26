package net.jadedmc.jadedcore.listeners;

import net.jadedmc.jadedcore.features.games.GamesGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    /**
     * Runs when the event is called.
     * @param event PlayerInteractEvent.
     */
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        // Exit if the item is null.
        if (event.getItem() == null)
            return;

        // Exit if item meta is null.
        if (event.getItem().getItemMeta() == null)
            return;

        Player player = event.getPlayer();

        String item = ChatColor.stripColor(event.getItem().getItemMeta().getDisplayName());
        switch (item) {
            case "Games" -> new GamesGUI().open(player);
        }
    }
}