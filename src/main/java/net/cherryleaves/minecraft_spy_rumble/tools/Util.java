package net.cherryleaves.minecraft_spy_rumble.tools;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;

public class Util {
    // アイテムを名前を付けた状態で作成するメソッド
    @Nullable
    public static ItemStack createItemWithDisplayName(Material material, String displayName) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return null;
        meta.setDisplayName(displayName);
        item.setItemMeta(meta);
        return item;
    }

    // 設定本を取得するメソッド
    public static ItemStack getOperateBook() {
        return Util.createItemWithDisplayName(Material.BOOK, ChatColor.BOLD + "設定本");
    }
}
