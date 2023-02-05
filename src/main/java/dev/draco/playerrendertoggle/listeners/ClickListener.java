package dev.draco.playerrendertoggle.listeners;

import dev.draco.playerrendertoggle.PlayerRenderToggle;
import dev.draco.playerrendertoggle.items.renderToggleItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ClickListener implements Listener {
    private final PlayerRenderToggle plugin;
    private renderToggleItem item;
    public ClickListener(PlayerRenderToggle plugin) {
        this.plugin = plugin;

        if(item == null ){
            item = new renderToggleItem();
        }
    }

    @EventHandler
    public void onToggleItemClick(InventoryClickEvent e){
            if(e.getWhoClicked() instanceof Player){
                Player p = Bukkit.getPlayer(e.getWhoClicked().getUniqueId());
                ItemStack clickedItem = e.getCurrentItem();

                if(clickedItem.getType() == Material.LIME_DYE){
                    for(Player online : Bukkit.getOnlinePlayers()){
                        p.hidePlayer(online); //hides all online players from p who is the one who clicked the item
                    }

                    //Set Current Item to Red to indicate the toggle has taken effect
                    e.setCurrentItem(item.getItem(Material.RED_DYE));

                } else if (clickedItem.getType() == Material.RED_DYE) {
                    for(Player online : Bukkit.getOnlinePlayers()){
                        p.showPlayer(online); //shows all online players from p who is the one who clicked the item
                    }

                    //Set Current Item to Green to indicate the toggle has taken effect
                    e.setCurrentItem(item.getItem(Material.LIME_DYE));
                }

            }
    }

    @EventHandler
    public void onToggleItemClick(PlayerInteractEvent e){
        Player p = e.getPlayer();
        ItemStack itemInHand = e.getItem();

        if(itemInHand.getType() == Material.LIME_DYE){
            for(Player online : Bukkit.getOnlinePlayers()){
                p.hidePlayer(online); //hides all online players from p who is the one who clicked the item
            }

            //Set Current Item to Red to indicate the toggle has taken effect
            p.getInventory().setItemInMainHand(item.getItem(Material.RED_DYE));

        } else if (itemInHand.getType() == Material.RED_DYE) {
            for(Player online : Bukkit.getOnlinePlayers()){
                p.showPlayer(online); //shows all online players from p who is the one who clicked the item
            }

            //Set Current Item to Green to indicate the toggle has taken effect
            p.getInventory().setItemInMainHand(item.getItem(Material.LIME_DYE));
        }

    }
}
