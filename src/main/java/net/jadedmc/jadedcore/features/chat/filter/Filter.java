package net.jadedmc.jadedcore.features.chat.filter;

import org.bukkit.entity.Player;

public abstract class Filter {
    private boolean falseMessage;

    public Filter() {
        falseMessage = true;
    }

    public void setFalseMessage(boolean falseMessage) {
        this.falseMessage = falseMessage;
    }

    public boolean showFalseMessage() {
        return falseMessage;
    }

    public abstract boolean passesFilter(Player player, String message);

    public void removePlayer(Player player) {}
}