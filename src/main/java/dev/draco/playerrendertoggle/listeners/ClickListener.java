package dev.draco.playerrendertoggle.listeners;

import dev.draco.playerrendertoggle.PlayerRenderToggle;
import dev.draco.playerrendertoggle.classes.ConfigMeta;
import dev.draco.playerrendertoggle.classes.Cooldown;
import dev.draco.playerrendertoggle.classes.PlayersHidingOthers;
import dev.draco.playerrendertoggle.items.renderToggleItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class ClickListener implements Listener {
    private final PlayerRenderToggle plugin;
    private renderToggleItem item;
    private final ConfigMeta HidePlayer;
    private final ConfigMeta ShowPlayer;
    private final HashMap<UUID, Long> cooldown;
    private final Long cooldownMax;
    private final String cooldownMessage;
    private final String cooldownMessageColor;
    private PlayersHidingOthers playersHidingOthersMap;

    public ClickListener(PlayerRenderToggle plugin, PlayersHidingOthers playersHidingOthers) {
        this.plugin = plugin;
        HidePlayer = new ConfigMeta().GetMeta(plugin, "HidePlayers");
        ShowPlayer = new ConfigMeta().GetMeta(plugin, "ShowPlayers");
        this.cooldown = new HashMap<>();
        cooldownMax = (plugin.getConfig().getLong("ItemCooldown") * 1000);
        cooldownMessage = plugin.getConfig().getString("CooldownMessage");
        cooldownMessageColor = plugin.getConfig().getString("CooldownMessageColor");
        playersHidingOthersMap = playersHidingOthers;

        if(item == null ){
            item = new renderToggleItem(plugin);
        }
    }

    @EventHandler
    public void onToggleItemClick(InventoryClickEvent e){
            if(e.getWhoClicked() instanceof Player){
                ItemStack clickedItem = e.getCurrentItem();

                if(clickedItem != null){
                    if(clickedItem.getType() == Material.valueOf(HidePlayer.ItemMaterial)){
                        Player p = Bukkit.getPlayer(e.getWhoClicked().getUniqueId());
                        boolean runCode = false;
                        if (p != null) {
                            runCode = CheckCooldown(p);
                        }

                        if(runCode){
                            for(Player online : Bukkit.getOnlinePlayers()){
                                p.hidePlayer(plugin, online); //hides all online players from p who is the one who clicked the item
                            }

                            //Sets the player state to hide all players
                            playersHidingOthersMap.AddPlayerToList(p.getUniqueId());

                            //Set Current Item to Red to indicate the toggle has taken effect
                            e.setCurrentItem(item.getItem(false));
                        }

                    } else if (clickedItem.getType() == Material.valueOf(ShowPlayer.ItemMaterial)) {
                        Player p = Bukkit.getPlayer(e.getWhoClicked().getUniqueId());
                        boolean runCode = false;
                        if (p != null) {
                            runCode = CheckCooldown(p);
                        }

                        if(runCode) {
                            for (Player online : Bukkit.getOnlinePlayers()) {
                                p.showPlayer(plugin, online); //shows all online players from p who is the one who clicked the item
                            }

                            //Sets the player state to not currently hide all players
                            playersHidingOthersMap.RemovePlayerFromList(p.getUniqueId());

                            //Set Current Item to Green to indicate the toggle has taken effect
                            e.setCurrentItem(item.getItem(true));
                        }
                    }
                }
            }
    }

    @EventHandler
    public void onToggleItemClick(PlayerInteractEvent e){
        ItemStack itemInHand = e.getItem();

        if(itemInHand != null){

            if(itemInHand.getType() == Material.valueOf(HidePlayer.ItemMaterial)){
                Player p = e.getPlayer();
                boolean runCode = CheckCooldown(p);

                if(runCode) {
                    for(Player online : Bukkit.getOnlinePlayers()){
                        p.hidePlayer(plugin, online); //hides all online players from p who is the one who clicked the item
                    }

                    //Sets the player state to hide all players
                    playersHidingOthersMap.AddPlayerToList(p.getUniqueId());

                    //Set Current Item to Red to indicate the toggle has taken effect
                    p.getInventory().setItemInMainHand(item.getItem(false));
                }

            } else if (itemInHand.getType() == Material.valueOf(ShowPlayer.ItemMaterial)) {
                Player p = e.getPlayer();
                boolean runCode = CheckCooldown(p);

                if(runCode) {
                    for(Player online : Bukkit.getOnlinePlayers()){
                        p.showPlayer(plugin, online); //shows all online players from p who is the one who clicked the item
                    }

                    //Sets the player state to not currently hide all players
                    playersHidingOthersMap.RemovePlayerFromList(p.getUniqueId());

                    //Set Current Item to Green to indicate the toggle has taken effect
                    p.getInventory().setItemInMainHand(item.getItem(true));
                }
            }
        }
    }

    private boolean CheckCooldown(Player p){
        boolean runCode = false;
        Cooldown hash = new Cooldown(p.getUniqueId(), this.cooldown.get(p.getUniqueId()));

        if(hash.EpochTime == null){
            //Player has not run the command
            this.cooldown.put(hash.ID, System.currentTimeMillis());
            runCode = true;
        }
        else{
            //Player has run the command
            long timeElapsedMS = System.currentTimeMillis() - hash.EpochTime;

            if(timeElapsedMS > cooldownMax){
                this.cooldown.put(hash.ID, System.currentTimeMillis());
                runCode = true;
            }
            else{
                p.sendMessage(ChatColor.valueOf(cooldownMessageColor) + cooldownMessage);
            }
        }

        return runCode;
    }
}
