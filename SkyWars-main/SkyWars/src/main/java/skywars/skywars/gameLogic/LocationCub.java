package skywars.skywars.gameLogic;

import org.bukkit.Location;
import org.bukkit.World;

public class LocationCub {
    int minX;
    int minY;
    int minZ;
    int maxX;
    int maxY;
    int maxZ;
    World world;
    //Создание начальных клеток
    public LocationCub(World world, int x1, int y1, int z1, int x2, int y2, int z2) {
        this.world  = world;
        minX = Math.min(x1, x2);
        minY = Math.min(y1, y2);
        minZ = Math.min(z1, z2);
        maxX = Math.max(x1, x2);
        maxY = Math.max(y1, y2);
        maxZ = Math.max(z1, z2);
    }
    public boolean contains(Location location) {
        return contains(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    public boolean contains(int x, int y, int z) {
        return x >= minX && x <= maxX &&
                y >= minY && y <= maxY &&
                z >= minZ && z <= maxZ;
    }
}
