package skywars.skywars.loaders;

import org.bukkit.Bukkit;
import skywars.skywars.gameLogic.LocationCub;

import java.util.ArrayList;
import java.util.List;

public class SkyWarsCubLoad {
    public static List<LocationCub> SkyWarsCuboid = new ArrayList<>();
    public static void load() {
        SkyWarsCuboid.add(new LocationCub(Bukkit.getWorld("SkyWars"), 48, 63, 21, 50, 59, 23));
        SkyWarsCuboid.add(new LocationCub(Bukkit.getWorld("SkyWars"), 59, 63, 0, 61, 59, 1));
        SkyWarsCuboid.add(new LocationCub(Bukkit.getWorld("SkyWars"), 48, 63, -22, 50, 59, -20));
        SkyWarsCuboid.add(new LocationCub(Bukkit.getWorld("SkyWars"), 27, 63, -38, 29, 59, -36));
        SkyWarsCuboid.add(new LocationCub(Bukkit.getWorld("SkyWars"), 12, 63, -60, 14, 59, -58));
    }
}
