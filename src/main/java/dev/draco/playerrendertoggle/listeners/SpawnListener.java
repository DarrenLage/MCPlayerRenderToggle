package dev.draco.playerrendertoggle.listeners;

import com.destroystokyo.paper.event.player.PlayerReadyArrowEvent;
import dev.draco.playerrendertoggle.PlayerRenderToggle;
import dev.draco.playerrendertoggle.items.renderToggleItem;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

import java.lang.module.Configuration;
import java.util.ArrayList;

public class SpawnListener implements Listener {

    private final PlayerRenderToggle plugin;
    private int itemIndex = 0;
    private renderToggleItem item;

    public SpawnListener(PlayerRenderToggle plugin) {
        this.plugin = plugin;
        itemIndex = plugin.getConfig().getInt("itemIndex");

        if(item == null ){
            item = new renderToggleItem();
        }
    }

    @EventHandler
    public void onSpawn(PlayerSpawnLocationEvent e) {
        //instantiate objects
        PlayerInventory i = e.getPlayer().getInventory();

        //set the item in the player inventory to default LIME_WOOL
        i.setItem(itemIndex, item.getItem(Material.LIME_DYE));
    }
}
