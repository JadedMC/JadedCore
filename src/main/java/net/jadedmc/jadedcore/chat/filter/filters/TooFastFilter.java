package net.jadedmc.jadedcore.chat.filter.filters;

import net.jadedmc.jadedcore.chat.filter.Filter;
import net.jadedmc.jadedcore.utils.chat.ChatUtils;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class TooFastFilter extends Filter {
    private final Map<Player, Long> lastMessage = new HashMap<>();

    public TooFastFilter() {
        setFalseMessage(false);
    }

    public boolean passesFilter(Player player, String message) {
        if(player.hasPermission("elytracore.bypass.timefilter")) {
            return true;
        }

        if(lastMessage.get(player) == null) {
            lastMessage.put(player, System.currentTimeMillis());
            return true;
        }

        if(System.currentTimeMillis() < lastMessage.get(player) + 1500) {
            ChatUtils.chat(player, "&cYou are chatting too fast!");
            return false;
        }



        lastMessage.put(player, System.currentTimeMillis());
        return true;
    }

    public void removePlayer(Player player) {
        lastMessage.remove(player);
    }
}