package dev.draco.playerrendertoggle;

import dev.draco.playerrendertoggle.listeners.ClickListener;
import dev.draco.playerrendertoggle.listeners.SpawnListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerRenderToggle extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new SpawnListener(this), this);
        getServer().getPluginManager().registerEvents(new ClickListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
