package skywars.skywars;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import skywars.skywars.events.*;

public class EventsLoader {
    private static PluginManager pm;
    public static void load() {
        pm = Bukkit.getServer().getPluginManager();
        register(new onBreakBlock());
        register(new onChangeClass());
        register(new onDropInLobby());
        register(new onInventoryClick());
        register(new onPlacedBlock());
        register(new onPlayerClicked());
        register(new onPlayerDeath());
        register(new onPlayerGetDamage());
        register(new onPlayerJoin());
        register(new onPlayerLeave());
    }
    private static void register(Listener l) {
        pm.registerEvents(l, MainClass.getInstance());
    }
}
