package dev.draco.playerrendertoggle.listeners;

import dev.draco.playerrendertoggle.PlayerRenderToggle;
import dev.draco.playerrendertoggle.items.renderToggleItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.PlayerInventory;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

public class SpawnListener implements Listener {

    private int itemIndex = 0;
    private final renderToggleItem item;

    public SpawnListener(PlayerRenderToggle plugin) {
        itemIndex = plugin.getConfig().getInt("itemIndex");
        item = new renderToggleItem(plugin);
    }

    @EventHandler
    public void onSpawn(PlayerSpawnLocationEvent e) {
        //instantiate objects
        PlayerInventory i = e.getPlayer().getInventory();

        //set the item in the player inventory
        i.setItem(itemIndex, item.getItem(true));
    }
}
