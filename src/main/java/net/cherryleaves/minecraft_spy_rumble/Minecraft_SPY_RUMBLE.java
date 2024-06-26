package net.cherryleaves.minecraft_spy_rumble;

import net.cherryleaves.minecraft_spy_rumble.command.CommandManager;
import net.cherryleaves.minecraft_spy_rumble.event.EventManager;
import net.cherryleaves.minecraft_spy_rumble.tools.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class Minecraft_SPY_RUMBLE extends JavaPlugin implements Listener {
    public BukkitRunnable task;

    @Override
    public void onEnable() {
        // Plugin startup logic
        super.onEnable();
        ConsoleCommandSender console = getServer().getConsoleSender();
        getServer().getPluginManager().registerEvents(new EventManager(), this);
        getServer().getPluginManager().registerEvents(new ItemSpawnStand(), this);
        getServer().getPluginManager().registerEvents(new Player_Task(), this);

        new CommandManager(this);


        console.sendMessage(ChatColor.GREEN + "ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー");
        console.sendMessage(ChatColor.AQUA + "Minecraft Spy Rumble plugin activated!!!!!!!!!!!");
        console.sendMessage("");
        console.sendMessage(ChatColor.GREEN + "このゲームは【Spy Rumble】というゲームをモチーフにし、");
        console.sendMessage(ChatColor.GREEN + "我々制作陣がこるんの名の下勝手にMinecraft化したゲームである");
        console.sendMessage("");
        console.sendMessage(ChatColor.GREEN + "java素人の人が書いたコードですので、");
        console.sendMessage(ChatColor.GREEN + "修正等ありましたら是非Githubにプルリク投げて下さい");
        console.sendMessage("");
        console.sendMessage(ChatColor.GREEN + "ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        super.onDisable();
    }

    // BeforeWolfPlayerCountの略
    public static int BWPCount = 1;
    // BeforeVillagerPlayerCountの略
    public static int BVPCount;
    // そもそもの全体人数
    public static int PlayerCount;
    // 同時に出現するタスクの数
    public static int ParallelTaskCount = 3;
    public static Inventory SettingGUI = Bukkit.createInventory(null, 9, ChatColor.BOLD + "設定");
    public static int armorStandCount = 0;

    public static void SettingGUICreate() {
        // 人狼の数減らし
        ItemStack wolfCountDownItem = Util.createItemWithDisplayName(Material.BLUE_CANDLE, ChatColor.BLUE + "人狼の数を減らす");
        SettingGUI.setItem(0, wolfCountDownItem);
        // 人狼の数表示
        ItemStack wolfPlayerCountItem = Util.createItemWithDisplayName(Material.WITHER_SKELETON_SKULL,
                ChatColor.DARK_AQUA + "人狼の数は" + ChatColor.GOLD + BWPCount + ChatColor.DARK_AQUA + "人です");
        SettingGUI.setItem(1, wolfPlayerCountItem);
        // 人狼の数増やし
        ItemStack wolfCountUpItem = Util.createItemWithDisplayName(Material.RED_CANDLE, ChatColor.RED + "人狼の数を増やす");
        SettingGUI.setItem(2, wolfCountUpItem);
        // タスクの数減らし
        ItemStack taskCountDownItem = Util.createItemWithDisplayName(Material.LIGHT_BLUE_CANDLE, ChatColor.RED + "同時に出現するタスクの数を減らす");
        SettingGUI.setItem(4, taskCountDownItem);
        // タスクの数表示
        ItemStack taskCountItem = Util.createItemWithDisplayName(Material.IRON_PICKAXE,
                ChatColor.DARK_AQUA + "同時に出現するタスクの数は" + ChatColor.GOLD + ParallelTaskCount + ChatColor.DARK_AQUA + "個です");
        SettingGUI.setItem(5, taskCountItem);
        // タスクの数増やし
        ItemStack taskCountUpItem = Util.createItemWithDisplayName(Material.MAGENTA_CANDLE, ChatColor.RED + "同時に出現するタスクの数を増やす");
        SettingGUI.setItem(6, taskCountUpItem);
        // ゲームスタートボタン
        ItemStack gameStartItem = Util.createItemWithDisplayName(Material.TOTEM_OF_UNDYING, ChatColor.BLUE + "ゲームスタート！");
        SettingGUI.setItem(8, gameStartItem);
    }


    public void PlayerSneak() {
        task = new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.isSneaking()) {
                        // スニーク中のプレイヤーに対する処理をここに記述する
                    }
                }
            }
        };
        task.runTaskTimer(this, 0L, 1L);
    }
}
