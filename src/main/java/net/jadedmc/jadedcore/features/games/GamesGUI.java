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
        super(45, "Games");

        addFiller(0,1,2,3,4,5,6,7,8,36,37,38,39,40,41,42,43,44);

        addGame(20, Game.ELYTRAPVP);
        addGame(24, Game.CACTUS_RUSH);
    }

    private void addGame(int slot, Game game) {
        setItem(slot, getGameIcon(game), (p, a) -> JadedAPI.sendBungeecordMessage(p, "BungeeCord", "Connect", game.getServer()));
    }

    private ItemStack getGameIcon(Game game) {
        XMaterial material = game.getIconMaterial();

        if(JadedAPI.getServerVersion() < 9 && material == XMaterial.ELYTRA) {
            material = XMaterial.FEATHER;
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