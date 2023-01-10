package skywars.skywars.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import skywars.skywars.SkyWars;

import static skywars.skywars.SkyWars.*;
import static skywars.skywars.gameLogic.Game.GameStart;

public class onPlayerJoin implements Listener {
    public onPlayerJoin() {

    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (InLobby) {
            SkyWars.inCub(player);
            SkyWars.loadInConfig(player);
            player.getInventory().clear();
            player.getInventory().addItem(getClassItem());
            player.setGameMode(GameMode.SURVIVAL);
            if (Bukkit.getOnlinePlayers().size() > 1) {
                GameStart();
            }
        } else {
            player.setGameMode(GameMode.SPECTATOR);
        }
    }
}
