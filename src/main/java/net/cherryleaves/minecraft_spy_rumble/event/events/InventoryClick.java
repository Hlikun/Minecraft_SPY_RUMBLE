package net.cherryleaves.minecraft_spy_rumble.event.events;

import net.cherryleaves.minecraft_spy_rumble.Game;
import net.cherryleaves.minecraft_spy_rumble.Minecraft_SPY_RUMBLE;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClick {
    public InventoryClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player player = (Player) e.getWhoClicked();
            // クリックされたGUIを取得する
            Inventory clickedInventory = e.getClickedInventory();
            if (clickedInventory == Minecraft_SPY_RUMBLE.SettingGUI) {
                ItemStack clickedItem = e.getCurrentItem();
                if (clickedItem != null && clickedItem.getType().equals(Material.RED_CANDLE)) {
                    Minecraft_SPY_RUMBLE.BWPCount += 1;
                    player.playSound(player.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 1.0f, 1.0f);
                    Minecraft_SPY_RUMBLE.SettingGUICreate();
                    player.openInventory(Minecraft_SPY_RUMBLE.SettingGUI);
                }
                if (clickedItem != null && clickedItem.getType().equals(Material.BLUE_CANDLE)) {
                    if (Minecraft_SPY_RUMBLE.BWPCount > 1) {
                        Minecraft_SPY_RUMBLE.BWPCount -= 1;
                        player.playSound(player.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 1.0f, 1.0f);
                        Minecraft_SPY_RUMBLE.SettingGUICreate();
                        player.openInventory(Minecraft_SPY_RUMBLE.SettingGUI);
                    }
                    else {
                        player.sendMessage(ChatColor.RED + "人狼の数を1未満にすることはできません");
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1.0f, 0.01f);
                        e.setCancelled(true);
                    }
                }
                if (clickedItem != null && clickedItem.getType().equals(Material.MAGENTA_CANDLE)) {
                    Minecraft_SPY_RUMBLE.ParallelTaskCount += 1;
                    player.playSound(player.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 1.0f, 1.0f);
                    Minecraft_SPY_RUMBLE.SettingGUICreate();
                    player.openInventory(Minecraft_SPY_RUMBLE.SettingGUI);
                }
                if (clickedItem != null && clickedItem.getType().equals(Material.LIGHT_BLUE_CANDLE)) {
                    if (Minecraft_SPY_RUMBLE.ParallelTaskCount > 0) {
                        Minecraft_SPY_RUMBLE.ParallelTaskCount -= 1;
                        player.playSound(player.getLocation(), Sound.BLOCK_DISPENSER_DISPENSE, 1.0f, 1.0f);
                        Minecraft_SPY_RUMBLE.SettingGUICreate();
                        player.openInventory(Minecraft_SPY_RUMBLE.SettingGUI);
                    }
                    else {
                        player.sendMessage(ChatColor.RED + "同時に出現するタスクの数を0未満にすることはできません");
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1.0f, 0.01f);
                    }
                }
                if (clickedItem != null && clickedItem.getType().equals(Material.TOTEM_OF_UNDYING)) {
                    Minecraft_SPY_RUMBLE.armorStandCount = 0;
                    for (ArmorStand armorStand : Bukkit.getWorlds().get(0).getEntitiesByClass(ArmorStand.class)) {
                        if (armorStand.getScoreboardTags().contains("TaskPoint")) {
                            Minecraft_SPY_RUMBLE.armorStandCount++;
                        }
                    }
                    if(Minecraft_SPY_RUMBLE.armorStandCount >= Minecraft_SPY_RUMBLE.ParallelTaskCount) {
                        player.closeInventory();
                        new Game().Start();
                    }
                    else{
                        player.sendMessage(ChatColor.RED + "設置してあるアーマースタンドの数を超えて設定することはできません。");
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 1f, 1f);
                    }
                }
                e.setCancelled(true);
            }
        }
    }
}
