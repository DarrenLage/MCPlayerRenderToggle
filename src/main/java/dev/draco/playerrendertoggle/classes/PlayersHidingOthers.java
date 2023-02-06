package dev.draco.playerrendertoggle.classes;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.UUID;

public class PlayersHidingOthers {

    public static HashMap<UUID, Boolean> PlayersHidingOthersMap;

    public PlayersHidingOthers(){
        PlayersHidingOthersMap = new HashMap<>();
    }

    public void AddPlayerToList(UUID id){
        PlayersHidingOthersMap.put(id, true);
    }

    public void RemovePlayerFromList(UUID id){
        PlayersHidingOthersMap.put(id, false);
    }

    public boolean GetPlayerHideState(UUID id){
        var result = PlayersHidingOthersMap.get(id);
        if(result == null){
            return false;
        }else{
            return result;
        }
    }

    public void HidePlayerFromAllCurrentlyHiding(Player p, Plugin plugin){
        PlayersHidingOthersMap.forEach((id, value) ->{
            if(value){
                Player hashPlayer = Bukkit.getServer().getPlayer(id);
                if(hashPlayer != null){
                    hashPlayer.hidePlayer(plugin, p); //hides player p from HashPlayer who is currently hiding all players
                }
            }
        });
    }
}
