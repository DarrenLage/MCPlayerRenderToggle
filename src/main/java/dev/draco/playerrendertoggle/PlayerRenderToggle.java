package dev.draco.playerrendertoggle;

import dev.draco.playerrendertoggle.classes.PlayersHidingOthers;
import dev.draco.playerrendertoggle.listeners.ClickListener;
import dev.draco.playerrendertoggle.listeners.SpawnListener;
import dev.draco.playerrendertoggle.listeners.JoinListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerRenderToggle extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        PlayersHidingOthers playersHidingOthers = new PlayersHidingOthers();

        getServer().getPluginManager().registerEvents(new SpawnListener(this, playersHidingOthers), this);
        getServer().getPluginManager().registerEvents(new ClickListener(this, playersHidingOthers), this);
        getServer().getPluginManager().registerEvents(new JoinListener(this, playersHidingOthers), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
