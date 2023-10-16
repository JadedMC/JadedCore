package net.jadedmc.jadedcore.features.games;

import net.jadedmc.jadedcore.JadedAPI;
import net.jadedmc.jadedcore.utils.chat.ChatUtils;
import net.jadedmc.jadedcore.utils.gui.CustomGUI;
import net.jadedmc.jadedcore.utils.item.ItemBuilder;
import net.jadedmc.jadedcore.utils.xseries.XMaterial;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.ChatPaginator;

public class GamesGUI extends CustomGUI {

    public GamesGUI() {
        super(54, "Games");

        addFiller(0,1,2,3,4,5,6,7,8,45,46,47,48,49,50,51,52,53);

        addGame(20, Game.ELYTRAPVP);
        //addGame(22, Game.TURFWARS);
        addGame(22, Game.CACTUS_RUSH);
        addGame(24, Game.HOUSING);
        addGame(40, Game.LOBBY);
    }

    private void addGame(int slot, Game game) {
        setItem(slot, getGameIcon(game), (p, a) -> JadedAPI.sendBungeecordMessage(p, "BungeeCord", "Connect", game.getServer()));
    }

    private ItemStack getGameIcon(Game game) {
        XMaterial material = game.getIconMaterial();

        if(JadedAPI.getServerVersion() < 9 && material == XMaterial.ELYTRA) {
            material = XMaterial.FEATHER;
        }

        if(game == Game.LOBBY) {
            return new ItemBuilder(material)
                    .setDisplayName("&a&l" + game.getName())
                    .addLore("")
                    .addLore("&a▸ Click to Connect")
                    .addLore(ChatUtils.parsePlaceholders("&7Join %bungee_" + game.getServer() + "% others playing!"))
                    .build();
        }

        ItemBuilder builder = new ItemBuilder(material)
                .setDisplayName("&a&l" + game.getName())
                .addLore("&8" + game.getType())
                .addLore("")
                .addLore(ChatPaginator.wordWrap(game.getDescription(), 25), "&7")
                .addLore("")
                .addLore("&a▸ Click to Connect")
                .addLore(ChatUtils.parsePlaceholders("&7Join %bungee_" + game.getServer() + "% others playing!"));
        return builder.build();
    }

}