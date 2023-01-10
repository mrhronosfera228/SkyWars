package skywars.skywars.gameLogic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import skywars.skywars.MainClass;
import skywars.skywars.SkyWars;
import skywars.skywars.loaders.LoadChest;

import static skywars.skywars.SkyWars.getAlivePlayers;
import static skywars.skywars.gameLogic.GameClasses.GiveClassItems;
import static skywars.skywars.gameLogic.World.deleteWorld;

public class Game {
    //Старт игры
    public static void GameStart() {
        SkyWars.InLobby = false;
        Bukkit.getScheduler().runTaskLater(MainClass.getInstance(), () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                getAlivePlayers().add(p.getName());
                p.closeInventory();
                p.getInventory().clear();
                GiveClassItems(p);
                new Location(Bukkit.getWorld("SkyWars"), p.getLocation().getX(), p.getLocation().getY() - 1, p.getLocation().getZ()).getBlock().setType(Material.AIR);
            }
            SkyWars.GameStart = true;
        }, 200);
        Bukkit.getScheduler().runTaskLater(MainClass.getInstance(), () -> {
            LoadChest.SetDefaultChest();
            LoadChest.SetMidChest();
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendTitle(ChatColor.translateAlternateColorCodes('&', "&6&Сундуки обновлены"), "", 10, 20, 40);
            }
        }, 2600);
    }
    //Конец игры
    public static void GameEnd() {
        Player player = Bukkit.getPlayer(getAlivePlayers().get(0));
        player.sendTitle(ChatColor.translateAlternateColorCodes('&', "&6&lVICTORY"), "", 10, 20, 40);
        Bukkit.getScheduler().runTaskLater(MainClass.getInstance(), () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.kickPlayer(("Сервер перезагружается"));
            }
            deleteWorld("SkyWars");
            Bukkit.getServer().reload();
        }, 200);
    }
}
