package dev.draco.playerrendertoggle.classes;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

public class ConfigMeta {
    public String ItemMaterial;
    public String ItemName;
    public String Lore1;
    public String Lore2;
    public String Lore3;


    public ConfigMeta GetMeta(Plugin plugin, String path){
        ConfigMeta meta = new ConfigMeta();

        ConfigurationSection sectionMeta = plugin.getConfig().getConfigurationSection(path);

        //SetValues
        if(sectionMeta != null){
            meta.ItemMaterial = sectionMeta.getString("ItemMaterial");
            meta.ItemName = sectionMeta.getString("ItemName");
            meta.Lore1 = sectionMeta.getString("Lore1");
            meta.Lore2 = sectionMeta.getString("Lore2");
            meta.Lore3 = sectionMeta.getString("Lore3");
        }


        return meta;
    }
}
