package net.jadedmc.jadedcore;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import net.jadedmc.jadedcore.commands.AbstractCommand;
import net.jadedmc.jadedcore.features.chat.filter.FilterManager;
import net.jadedmc.jadedcore.features.party.PartyManager;
import net.jadedmc.jadedcore.features.player.staff.StaffPlayerManager;
import net.jadedmc.jadedcore.listeners.AsyncPlayerChatListener;
import net.jadedmc.jadedcore.listeners.PlayerJoinListener;
import net.jadedmc.jadedcore.listeners.PlayerQuitListener;
import net.jadedmc.jadedcore.utils.gui.GUIListeners;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public final class JadedCore extends JavaPlugin implements PluginMessageListener {
    private MySQL mySQL;
    private SettingsManager settingsManager;
    private StaffPlayerManager staffPlayerManager;
    private FilterManager filterManager;
    private PartyManager partyManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        new JadedAPI(this);

        settingsManager = new SettingsManager(this);
        staffPlayerManager = new StaffPlayerManager(this);
        filterManager = new FilterManager();
        partyManager = new PartyManager(this);

        mySQL = new MySQL(this);
        mySQL.openConnection();

        AbstractCommand.registerCommands(this);

        getServer().getPluginManager().registerEvents(new AsyncPlayerChatListener(this), this);
        getServer().getPluginManager().registerEvents(new GUIListeners(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);

        getServer().getMessenger().registerIncomingPluginChannel(this, "jadedmc:party", this);
        getServer().getMessenger().registerOutgoingPluginChannel(this, "jadedmc:party");
    }

    @Override
    public void onPluginMessageReceived(String channel, @NotNull Player player, byte[] bytes) {
        if (!channel.equalsIgnoreCase( "jadedmc:party")) {
            return;
        }

        ByteArrayDataInput in = ByteStreams.newDataInput(bytes);
        String subChannel = in.readUTF();

        switch (subChannel.toLowerCase()) {
            case "sync" -> {
                String message = in.readUTF();
                System.out.println("[Party Sync] " + message);
                partyManager.syncParty(message);
            }

            case "disband" -> {
                String message = in.readUTF();
                System.out.println("[Party Disband] " + message);
                partyManager.disbandParty(UUID.fromString(message));
            }
            default -> System.out.println(channel);
        }
    }
    /**
     * Get the filter manager, which manages the chat filter.
     * @return Filter manager.
     */
    public FilterManager filterManager() {
        return  filterManager;
    }

    /**
     * Be able to connect to MySQL.
     * @return MySQL.
     */
    public MySQL mySQL() {
        return mySQL;
    }


    public PartyManager partyManager() {
        return partyManager;
    }

    /**
     * Get the Settings Manager, which gives us access to the plugin Configuration.
     * @return Settings Manager.
     */
    public SettingsManager settingsManager() {
        return settingsManager;
    }

    /**
     * Get the Staff Player manager, which manages StaffPlayer objects.
     * @return Staff player manager.
     */
    public StaffPlayerManager staffPlayerManager() {
        return staffPlayerManager;
    }
}