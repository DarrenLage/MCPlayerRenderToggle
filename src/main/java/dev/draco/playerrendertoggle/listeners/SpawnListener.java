package dev.draco.playerrendertoggle.listeners;

import dev.draco.playerrendertoggle.PlayerRenderToggle;
import dev.draco.playerrendertoggle.classes.PlayersHidingOthers;
import dev.draco.playerrendertoggle.items.renderToggleItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.PlayerInventory;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

public class SpawnListener implements Listener {

    private int itemIndex = 0;
    private final renderToggleItem item;
    private final PlayersHidingOthers playersHidingOthersMap;

    public SpawnListener(PlayerRenderToggle plugin, PlayersHidingOthers playersHidingOthers) {
        itemIndex = plugin.getConfig().getInt("ItemIndex");
        item = new renderToggleItem(plugin);
        playersHidingOthersMap = playersHidingOthers;
    }

    @EventHandler
    public void onSpawn(PlayerSpawnLocationEvent e) {
        //instantiate objects
        Player p = e.getPlayer();
        PlayerInventory i = p.getInventory();

        boolean playerHideState = playersHidingOthersMap.GetPlayerHideState(p.getUniqueId());
        if(playerHideState) {
            //Currently Hiding Players

            //set the item in the player inventory
            i.setItem(itemIndex, item.getItem(false));
        }
        else {
            //Not currently hiding players

            //set the item in the player inventory
            i.setItem(itemIndex, item.getItem(true));
        }
    }
}
