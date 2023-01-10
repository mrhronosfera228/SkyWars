package skywars.skywars.events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.HashMap;

import static skywars.skywars.SkyWars.getAlivePlayers;
import static skywars.skywars.gameLogic.Game.GameEnd;

public class onPlayerGetDamage implements Listener {
    private HashMap<Player, Integer> falldamage = new HashMap<>();
    public onPlayerGetDamage() {

    }
    @EventHandler
    public void onPlayerGetDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            //Реализация смерти игрока
            if (event.getCause().equals(EntityDamageEvent.DamageCause.VOID)) {
                event.setCancelled(true);
                player.setGameMode(GameMode.SPECTATOR);
                getAlivePlayers().remove(player.getName());
                if (getAlivePlayers().size() <= 1) {
                    GameEnd();
                }
            }
            //Отключение урона от падения
            if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                if (falldamage.get(player) == 0) {
                    falldamage.put(player, 1);
                    event.setCancelled(true);
                }
            }
        }
    }
}
