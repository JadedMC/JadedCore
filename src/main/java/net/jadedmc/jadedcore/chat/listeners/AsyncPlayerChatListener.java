package net.jadedmc.jadedcore.chat.listeners;

import github.scarsz.discordsrv.DiscordSRV;
import me.clip.placeholderapi.PlaceholderAPI;
import net.jadedmc.jadedcore.JadedCore;
import net.jadedmc.jadedcore.utils.chat.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This listens to the AsyncPlayerChatEvent event, which is called every time a player send a chat message.
 * We use this to grab the chat message being sent and format it properly.
 */
public class AsyncPlayerChatListener implements Listener {
    private final JadedCore plugin;

    /**
     * To be able to access the configuration files, we need to pass an instance of the plugin to our listener.
     * This is known as Dependency Injection.
     * @param plugin Instance of the plugin.
     */
    public AsyncPlayerChatListener(JadedCore plugin) {
        this.plugin = plugin;
    }


    /**
     * Runs when the event is called.
     * @param event AsyncPlayerChatEvent.
     */
    @EventHandler(priority =  EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event) {
        // Makes sure the event wasn't cancelled by another plugin.
        if(event.isCancelled()) {
            return;
        }

        Player player = event.getPlayer();

        // Cancels the event so that the message does not get sent to general chat.
        event.setCancelled(true);

        String chatFormat = getFormat();
        String chatMessage = event.getMessage();

        // Remove color codes if the player doesn't have permission to use them.
        if(!player.hasPermission("elytrachat.color")) {
            chatMessage = ChatColor.stripColor(ChatUtils.translate(chatMessage));
        }

        String finalMessage = PlaceholderAPI.setPlaceholders(player, chatFormat.replace("%message%", chatMessage));

        // Checks if the message passes the chat filter.
        if(!plugin.filterManager().passesFilter(player, chatMessage)) {
            return;
        }

        String toFilter = chatMessage.toLowerCase()
                .replace(" ", "")
                .replace("-", "")
                .replace("_", "")
                .replace("~", "")
                .replace("/", "")
                .replace("\\", "")
                .replace("=", "")
                .replace("+", "")
                .replace(".", "")
                .replace("`", "")
                .replace("&", "")
                .replace("$", "")
                .replace("%", "")
                .replace("*", "")
                .replace("(", "")
                .replace(")", "");

        // Check filter
        for(String filter : plugin.settingsManager().getConfig().getStringList("Chat.filter")) {
            Pattern pattern = Pattern.compile(filter);
            Matcher matcher = pattern.matcher(toFilter);

            if(matcher.find()) {
                System.out.println("(filter) " + finalMessage);

                // Send normal message to player.
                ChatUtils.chat(player, finalMessage);

                // Loop through staff.
                for(Player staff : Bukkit.getOnlinePlayers()) {
                    if(!staff.hasPermission("staff.filter")) {
                        continue;
                    }

                    // Send filtered message to staff.
                    ChatUtils.chat(staff, "&c(filter) " + finalMessage);
                }
                return;
            }
        }

        // Log message to the console.
        System.out.println(ChatColor.stripColor(finalMessage));

        // Send message to all online players.
        for(Player viewer : Bukkit.getOnlinePlayers()) {
            ChatUtils.chat(viewer, finalMessage);
        }

        if(plugin.getServer().getPluginManager().isPluginEnabled("DiscordSRV")) {
            DiscordSRV.getPlugin().getMainTextChannel().sendMessage(ChatColor.stripColor(ChatUtils.translate(finalMessage))).queue();
        }
    }

    /**
     * Gets the message format.
     * @return Message format with placeholders.
     */
    private String getFormat() {
        // Creates the message to be sent.
        String chatFormat = "";

        // Gets the selected format from the list of formats.
        ConfigurationSection formatText = plugin.settingsManager().getConfig().getConfigurationSection("Chat.format");

        // Loop through the format to add the text together.
        for(String str : formatText.getKeys(false)) {
            // Add the result to the new message.
            chatFormat += formatText.getString(str);
        }

        return chatFormat;
    }
}