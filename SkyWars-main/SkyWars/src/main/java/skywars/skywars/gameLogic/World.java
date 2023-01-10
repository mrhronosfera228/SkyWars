package skywars.skywars.gameLogic;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import skywars.skywars.MainClass;

import java.io.File;
import java.io.IOException;

public class World {
    //Копирование мира
    public static void copyWorld(final String oldDirectory, final String name) {
        try {
            final File dest = new File("./");
            final File source = new File("./" + oldDirectory + "/");
            FileUtils.copyDirectory(source, dest);
            Bukkit.getServer().createWorld(new WorldCreator(name));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Удаление старого мира
    public static void deleteWorld(final String world) {
        MainClass.getInstance().getServer().unloadWorld(world, true);
        final File dir = new File("./" + world);
        try {
            FileUtils.deleteDirectory(dir);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
