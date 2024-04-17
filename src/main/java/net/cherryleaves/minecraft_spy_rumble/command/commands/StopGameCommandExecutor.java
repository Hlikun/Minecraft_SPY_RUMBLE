package net.cherryleaves.minecraft_spy_rumble.command.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.Objects;

public class StopGameCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (!(sender instanceof Player) || !sender.isOp()) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
            return true;
        }
        Player player = (Player) sender;

        player.sendMessage("ゲームを強制中断させました");
        player.playSound(player.getLocation(), Sound.ITEM_GOAT_HORN_SOUND_0, 1, 1);

        Objects.requireNonNull(Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard().getTeam("wolf")).unregister();
        Objects.requireNonNull(Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard().getTeam("villager")).unregister();

        Bukkit.getServer().getWorlds().forEach(world -> {
            world.getEntities().forEach(entity -> {
                if (entity instanceof ArmorStand) {
                    ArmorStand armorStand = (ArmorStand) entity;
                    if (armorStand.getScoreboardTags().contains("TaskPoint")) {
                        armorStand.setGlowing(true);
                        if (armorStand.getScoreboardTags().contains("SelectedTaskPoint")) {
                            armorStand.removeScoreboardTag("SelectedTaskPoint");
                        }
                    }
                }
            });
        });
        return true;
    }
}
