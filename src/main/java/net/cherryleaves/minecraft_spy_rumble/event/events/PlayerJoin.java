package net.cherryleaves.minecraft_spy_rumble.event.events;

import net.cherryleaves.minecraft_spy_rumble.tools.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoin {
    public PlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        // 参加時にアイテムを削除
        p.getInventory().clear();

        // op所持者に設定本を付与
        if (p.isOp()) {
            ItemStack operateBook = Util.getOperateBook();
            p.getInventory().addItem(operateBook);
        }
    }
}
