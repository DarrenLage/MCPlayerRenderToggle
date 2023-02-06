package dev.draco.playerrendertoggle.listeners;

import dev.draco.playerrendertoggle.PlayerRenderToggle;
import dev.draco.playerrendertoggle.classes.PlayersHidingOthers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class JoinListener implements Listener {

    private final Plugin Plugin;
    private final PlayersHidingOthers playersHidingOthersMap;

    public JoinListener(PlayerRenderToggle plugin, PlayersHidingOthers playersHidingOthers) {
        Plugin = plugin;
        playersHidingOthersMap = playersHidingOthers;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        if (playersHidingOthersMap != null) {
            Player p = e.getPlayer();
            playersHidingOthersMap.HidePlayerFromAllCurrentlyHiding(p, Plugin);

            boolean playerHideState = playersHidingOthersMap.GetPlayerHideState(p.getUniqueId());
            if(playerHideState) {
                //Hide all Players from player who just joined

                for (Player online : Bukkit.getOnlinePlayers()) {
                    p.hidePlayer(Plugin, online); //hides all online players from p who is the one who clicked the item
                }
            }
        }
    }
}
