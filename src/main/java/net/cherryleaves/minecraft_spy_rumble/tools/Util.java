package net.cherryleaves.minecraft_spy_rumble.tools;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;

public class Util {
    @Nullable
    public static ItemStack setMenuItemMeta(Material material, String displayName) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return null;
        meta.setDisplayName(displayName);
        item.setItemMeta(meta);
        return item;
    }
}
