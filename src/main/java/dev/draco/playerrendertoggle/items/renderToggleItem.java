package dev.draco.playerrendertoggle.items;

import dev.draco.playerrendertoggle.PlayerRenderToggle;
import dev.draco.playerrendertoggle.classes.ConfigMeta;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;

public class renderToggleItem {

    private final PlayerRenderToggle plugin;

    public renderToggleItem(PlayerRenderToggle plugin) {
        this.plugin = plugin;
    }

    public ItemStack getItem(Boolean HidePlayers) {
        //Determine which object to pull back from the Config based on the HidePlayers Boolean
        String configPath =  HidePlayers ? "HidePlayers" : "ShowPlayers";
        ConfigMeta meta = new ConfigMeta().GetMeta(plugin, configPath);

        if(meta != null){
            //instantiate the objects
            ItemStack item = new ItemStack(Material.valueOf(meta.ItemMaterial));
            ItemMeta itemMeta = item.getItemMeta();
            ArrayList<String> toggleLore = new ArrayList<>();

            //Change the Item Lore
            if(!meta.Lore1.equals("")){
                toggleLore.add(meta.Lore1);
            }
            if(!meta.Lore2.equals("")){
                toggleLore.add(meta.Lore2);
            }
            if(!meta.Lore3.equals("")){
                toggleLore.add(meta.Lore3);
            }

            //Set Meta
            itemMeta.setDisplayName(meta.ItemName);
            itemMeta.setLore(toggleLore);

            item.setItemMeta(itemMeta);

            return item;
        }

        return new ItemStack(Material.AIR);
    }
}
