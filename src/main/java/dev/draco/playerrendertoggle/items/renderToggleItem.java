package dev.draco.playerrendertoggle.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class renderToggleItem {

    public static final String displayName = "Toggle Player Rendering";

    public static ItemStack getItem(Material material) {
        //instantiate the objects
        ItemStack item = new ItemStack(material);
        ItemMeta toggleMeta = item.getItemMeta();
        ArrayList<String> toggleLore = new ArrayList<>();

        //Change the ItemMeta
        toggleLore.add("Clicking this item");
        toggleLore.add("will toggle other");
        toggleLore.add("players rendering for you");

        toggleMeta.setDisplayName(displayName);
        toggleMeta.setLore(toggleLore);

        item.setItemMeta(toggleMeta);

        return item;
    }
}
