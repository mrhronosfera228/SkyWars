package skywars.skywars.events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static skywars.skywars.SkyWars.*;
import static skywars.skywars.gameLogic.Game.GameEnd;

public class onPlayerLeave implements Listener {
    public onPlayerLeave() {

    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.SPECTATOR && GameStart) {
            getAlivePlayers().remove(player.getName());
            if (getAlivePlayers().size() <= 1) {
                GameEnd();
            }
        }
    }
}
