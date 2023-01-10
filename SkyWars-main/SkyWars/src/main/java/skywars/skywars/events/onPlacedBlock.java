package skywars.skywars.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import static skywars.skywars.SkyWars.GameStart;

public class onPlacedBlock implements Listener {
    public onPlacedBlock() {

    }
    @EventHandler
    public void onPlacedBlock(BlockPlaceEvent event) {
        if (!GameStart) {
            event.setCancelled(true);
        }
    }
}
