package skywars.skywars;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

import static skywars.skywars.gameLogic.ClassesItems.ItemCreate;
import static skywars.skywars.loaders.LocationLoads.SkyWarsLocation;
import static skywars.skywars.loaders.SkyWarsCubLoad.SkyWarsCuboid;

public class SkyWars {
    public static boolean GameStart = false;
    public static boolean InLobby = true;
    public static FileConfiguration config = MainClass.getData().getConfig();
    private static List<String> AlivePlayers = new ArrayList<>();
    private static Map<String, Inventory> ClassInventory = new HashMap<>();
    private static ItemStack ClassItem = ItemCreate(Material.BOOK, "&5Выбор класса");
    private static Map<String, List<String>> Classes = new HashMap<>();


    //Телепортация игроков в стартовую позицию
    public static void inCub(Player p) {
        for (int i = 0; i < SkyWarsCuboid.size(); i++) {
            boolean stop = true;
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (SkyWarsCuboid.get(i).contains(player.getLocation())) {
                    stop = false;
                }
            }
            if (stop) {
                p.getPlayer().teleport(SkyWarsLocation.get(i));
            }
        }
    }
    //Прогрузка конфигов
    public static void loadInConfig(Player player) {
        if (config.getConfigurationSection("players." + player.getName()) == null) {
            config.createSection("players." + player.getName());
            config.createSection("players." + player.getName() + ".name");
            config.createSection("players." + player.getName() + ".Class");
            config.createSection("players." + player.getName() + ".balance");
            MainClass.getInstance().reloadConfig();
            MainClass.getData().save();
            Classes.put(player.getName(), config.getStringList("players." + player.getName() + ".Class"));
        }
    }

    public static List<String> getAlivePlayers() {
        return AlivePlayers;
    }

    public static Map<String, Inventory> getClassInventory() {
        return ClassInventory;
    }

    public static ItemStack getClassItem() {
        return ClassItem;
    }

    public static Map<String, List<String>> getClasses() {
        return Classes;
    }

}
