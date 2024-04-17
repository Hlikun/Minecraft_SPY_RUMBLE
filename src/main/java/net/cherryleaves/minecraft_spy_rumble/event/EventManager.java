package net.cherryleaves.minecraft_spy_rumble.event;

import net.cherryleaves.minecraft_spy_rumble.event.events.InventoryClick;
import net.cherryleaves.minecraft_spy_rumble.event.events.PlayerInteract;
import net.cherryleaves.minecraft_spy_rumble.event.events.PlayerJoin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventManager implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        new PlayerInteract(e);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        new InventoryClick(e);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        new PlayerJoin(e);
    }
}
