package dev.draco.playerrendertoggle.listeners;

import dev.draco.playerrendertoggle.PlayerRenderToggle;
import dev.draco.playerrendertoggle.classes.ConfigMeta;
import dev.draco.playerrendertoggle.items.renderToggleItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ClickListener implements Listener {
    private final PlayerRenderToggle plugin;
    private renderToggleItem item;
    private final ConfigMeta HidePlayer;
    private final ConfigMeta ShowPlayer;
    public ClickListener(PlayerRenderToggle plugin) {
        this.plugin = plugin;
        HidePlayer = new ConfigMeta().GetMeta(plugin, "HidePlayers");
        ShowPlayer = new ConfigMeta().GetMeta(plugin, "ShowPlayers");

        if(item == null ){
            item = new renderToggleItem(plugin);
        }
    }

    @EventHandler
    public void onToggleItemClick(InventoryClickEvent e){
            if(e.getWhoClicked() instanceof Player){
                ItemStack clickedItem = e.getCurrentItem();

                if(clickedItem != null){
                    Player p = Bukkit.getPlayer(e.getWhoClicked().getUniqueId());

                    if(clickedItem.getType() == Material.valueOf(HidePlayer.ItemMaterial)){
                        for(Player online : Bukkit.getOnlinePlayers()){
                            if (p != null) {
                                p.hidePlayer(plugin, online); //hides all online players from p who is the one who clicked the item
                            }
                        }

                        //Set Current Item to Red to indicate the toggle has taken effect
                        e.setCurrentItem(item.getItem(false));

                    } else if (clickedItem.getType() == Material.valueOf(ShowPlayer.ItemMaterial)) {
                        for(Player online : Bukkit.getOnlinePlayers()){
                            if (p != null) {
                                p.showPlayer(plugin, online); //shows all online players from p who is the one who clicked the item
                            }
                        }

                        //Set Current Item to Green to indicate the toggle has taken effect
                        e.setCurrentItem(item.getItem(true));
                    }
                }
            }
    }

    @EventHandler
    public void onToggleItemClick(PlayerInteractEvent e){
        ItemStack itemInHand = e.getItem();

        if(itemInHand != null){
            Player p = e.getPlayer();

            if(itemInHand.getType() == Material.valueOf(HidePlayer.ItemMaterial)){
                for(Player online : Bukkit.getOnlinePlayers()){
                    p.hidePlayer(plugin, online); //hides all online players from p who is the one who clicked the item
                }

                //Set Current Item to Red to indicate the toggle has taken effect
                p.getInventory().setItemInMainHand(item.getItem(false));

            } else if (itemInHand.getType() == Material.valueOf(ShowPlayer.ItemMaterial)) {
                for(Player online : Bukkit.getOnlinePlayers()){
                    p.showPlayer(plugin, online); //shows all online players from p who is the one who clicked the item
                }

                //Set Current Item to Green to indicate the toggle has taken effect
                p.getInventory().setItemInMainHand(item.getItem(true));
            }
        }
    }
}
