package net.jadedmc.jadedcore.features.party;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.jadedmc.jadedcore.JadedCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class PartyManager {
    private final JadedCore plugin;
    private final Map<UUID, Party> parties = new HashMap<>();

    public PartyManager(JadedCore plugin) {
        this.plugin = plugin;
    }

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

    public void summonParty(Party party) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("summon");
        out.writeUTF(party.getUUID().toString());
        Iterables.getFirst(Bukkit.getOnlinePlayers(), null).sendPluginMessage(plugin, "jadedmc:party", out.toByteArray());
    }
}
