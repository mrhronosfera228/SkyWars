package skywars.skywars;

import org.bukkit.plugin.java.JavaPlugin;
import skywars.skywars.loaders.*;

import static skywars.skywars.gameLogic.World.copyWorld;

public class MainClass extends JavaPlugin {
    private static MainClass instance;
    private SkyWarsConfig data;
    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        copyWorld("oldworld", "SkyWars");
        data = new SkyWarsConfig("SkyWars.yml");
        LoadConfig.load();
        LoadClasses.load();
        LoadChest.load();
        LocationLoads.load();
        SkyWarsCubLoad.load();
        EventsLoader.load();

    }

    @Override
    public void onDisable() {

    }

    public static MainClass getInstance() {
        return instance;
    }

    public static SkyWarsConfig getData() {
        return instance.data;
    }
}
