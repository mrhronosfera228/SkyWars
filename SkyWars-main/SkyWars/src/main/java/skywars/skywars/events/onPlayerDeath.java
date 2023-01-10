package skywars.skywars.events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import static skywars.skywars.SkyWars.getAlivePlayers;
import static skywars.skywars.gameLogic.Game.GameEnd;

public class onPlayerDeath implements Listener {
    public onPlayerDeath() {

    }
    @EventHandler
    public void onPlayerDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            player.setGameMode(GameMode.SPECTATOR);
            getAlivePlayers().remove(player.getName());
            if (getAlivePlayers().size() <= 1) {
                GameEnd();
            }
        }
    }
}
