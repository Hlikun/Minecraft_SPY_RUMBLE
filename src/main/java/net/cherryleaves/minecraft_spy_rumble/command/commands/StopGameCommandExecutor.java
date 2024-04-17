package net.cherryleaves.minecraft_spy_rumble.command.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

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

        // wolfとvillagerのチームを削除
        unregisterTeam("wolf");
        unregisterTeam("villager");

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

    private void unregisterTeam(String teamName) {
        // ScoreboardManagerを取得&null回避
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        if (manager == null) return;
        // Scoreboardを取得
        Scoreboard scoreboard = manager.getMainScoreboard();
        // teamNameからTeamを取得&null回避
        Team team = scoreboard.getTeam(teamName);
        if (team == null) return;
        // チームを削除
        team.unregister();
    }
}
