package skywars.skywars;

import org.bukkit.plugin.java.JavaPlugin;
import skywars.skywars.loaders.*;

import static skywars.skywars.SkyWars.copyWorld;

public final class MainClass extends JavaPlugin {
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
        getServer().getPluginManager().registerEvents(new SkyWars(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static MainClass getInstance() {
        return instance;
    }

    public static SkyWarsConfig getData() {
        return instance.data;
    }
}
