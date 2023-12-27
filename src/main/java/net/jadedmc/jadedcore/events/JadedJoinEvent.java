package net.jadedmc.jadedcore.events;

import net.jadedmc.jadedcore.features.player.JadedPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.checkerframework.checker.nullness.qual.NonNull;

public class JadedJoinEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final JadedPlayer jadedPlayer;

    public JadedJoinEvent(final JadedPlayer jadedPlayer) {
        this.jadedPlayer = jadedPlayer;
    }

    public JadedPlayer getJadedPlayer() {
        return jadedPlayer;
    }

    public @NonNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}