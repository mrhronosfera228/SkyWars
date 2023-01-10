package skywars.skywars.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import static skywars.skywars.SkyWars.GameStart;

public class onBreakBlock implements Listener {
    public onBreakBlock() {

    }
    @EventHandler
    public void onBreakBlock(BlockBreakEvent event) {
        if (!GameStart) {
            event.setCancelled(true);
        }
    }
}
