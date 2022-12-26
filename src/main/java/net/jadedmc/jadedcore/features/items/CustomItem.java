package net.jadedmc.jadedcore.features.items;

import net.jadedmc.jadedcore.utils.item.ItemBuilder;
import net.jadedmc.jadedcore.utils.xseries.XMaterial;
import org.bukkit.inventory.ItemStack;

public enum CustomItem {
    GAME_SELECTOR(new ItemBuilder(XMaterial.COMPASS)
            .setDisplayName("&a&lGames")
            .build());

    private final ItemStack itemStack;

    CustomItem(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack toItemStack() {
        return itemStack;
    }
}
