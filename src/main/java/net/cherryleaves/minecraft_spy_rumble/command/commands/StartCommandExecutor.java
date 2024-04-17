package net.cherryleaves.minecraft_spy_rumble.command.commands;

import net.cherryleaves.minecraft_spy_rumble.tools.Util;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class StartCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (!(sender instanceof Player) || !sender.isOp()) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
            return true;
        }
        // senderをPlayerにキャスト
        Player admin = (Player) sender;
        admin.playSound(admin.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.7f, 0.8f);
        // 設定本を付与
        ItemStack operateBook = Util.getOperateBook();
        admin.getInventory().addItem(operateBook);
        return true;
    }
}
