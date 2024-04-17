package net.cherryleaves.minecraft_spy_rumble.command;

import net.cherryleaves.minecraft_spy_rumble.command.commands.StartCommandExecutor;
import net.cherryleaves.minecraft_spy_rumble.command.commands.StopGameCommandExecutor;
import net.cherryleaves.minecraft_spy_rumble.command.commands.TaskSpawnCommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class CommandManager {
    public CommandManager(JavaPlugin plugin) {
       Objects.requireNonNull(plugin.getCommand("task-spawn")).setExecutor(new TaskSpawnCommandExecutor());
       Objects.requireNonNull(plugin.getCommand("start")).setExecutor(new StartCommandExecutor());
       Objects.requireNonNull(plugin.getCommand("stop-game")).setExecutor(new StopGameCommandExecutor());
    }
}
