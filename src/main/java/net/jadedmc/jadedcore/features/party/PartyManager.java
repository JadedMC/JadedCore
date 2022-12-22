package net.jadedmc.jadedcore.features.party;

import org.bukkit.entity.Player;

import java.util.*;

public class PartyManager {
    private final Map<UUID, Party> parties = new HashMap<>();

    /**
     * Syncs a party from bungeecord.
     * @param message Bungeecord message.
     */
    public void syncParty(String message) {
        parties.put(UUID.fromString(message.split("~")[0]), new Party(message));
    }

    /**
     * Marks a party as disbanded.
     * @param uuid UUID of the party that is disbanded.
     */
    public void disbandParty(UUID uuid) {
        parties.remove(uuid);
    }

    /**
     * Get the party a player is in.
     * Returns null if not in a party.
     * @param player Player to get party of.
     * @return Party the player is in.
     */
    public Party getParty(Player player) {
        for(Party party : parties.values()) {
            if(party.getPlayers().contains(player.getUniqueId())) {
                return party;
            }
        }

        return null;
    }
}
