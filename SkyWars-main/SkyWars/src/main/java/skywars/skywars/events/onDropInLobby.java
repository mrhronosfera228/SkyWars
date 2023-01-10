package skywars.skywars.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import static skywars.skywars.SkyWars.GameStart;

public class onDropInLobby implements Listener {
    public onDropInLobby() {

    }
    @EventHandler
    public void onDropInLobby(PlayerDropItemEvent event) {
        if (!GameStart) {
            event.setCancelled(true);
        }
    }
}
