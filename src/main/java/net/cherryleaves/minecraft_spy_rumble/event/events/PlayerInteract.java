package net.cherryleaves.minecraft_spy_rumble.event.events;

import net.cherryleaves.minecraft_spy_rumble.Minecraft_SPY_RUMBLE;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract {
    public PlayerInteract(PlayerInteractEvent e) {
        Action a = e.getAction();
        Player p = e.getPlayer();

        if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) {
            if (e.getItem() != null && e.getItem().getType() == Material.BOOK) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                // 設定GUIを開く
                Minecraft_SPY_RUMBLE.SettingGUICreate();
                p.openInventory(Minecraft_SPY_RUMBLE.SettingGUI);
            }
        }
    }
}
