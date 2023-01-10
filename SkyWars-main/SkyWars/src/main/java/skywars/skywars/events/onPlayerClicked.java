package skywars.skywars.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import static skywars.skywars.SkyWars.*;

public class onPlayerClicked implements Listener {
    public onPlayerClicked() {

    }
    @EventHandler
    public void onPlayerClicked(PlayerInteractEvent event) {
        if (event.getItem() != null) {
            if ((event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) && event.getItem().equals(getClassItem())) {
                Player p = event.getPlayer();
                p.openInventory(getClassInventory().get(p.getName()));
            }
        }
    }
}
