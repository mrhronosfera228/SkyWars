package skywars.skywars.loaders;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class LocationLoads {
    public static List<Location> SkyWarsLocation = new ArrayList<>();
    public static void load() {
        SkyWarsLocation.add(new Location(Bukkit.getWorld("SkyWars"), 49.5, 60, 22.5, 90, 0));
        SkyWarsLocation.add(new Location(Bukkit.getWorld("SkyWars"), 60.5, 60, 0.5, 90, 0));
        SkyWarsLocation.add(new Location(Bukkit.getWorld("SkyWars"), 49.5, 60, -21.5, 90, 0));
        SkyWarsLocation.add(new Location(Bukkit.getWorld("SkyWars"), 28.5, 60, -37.5, 90, 0));
        SkyWarsLocation.add(new Location(Bukkit.getWorld("SkyWars"), 13.5, 60, -59.5, 90, 0));
    }
}
