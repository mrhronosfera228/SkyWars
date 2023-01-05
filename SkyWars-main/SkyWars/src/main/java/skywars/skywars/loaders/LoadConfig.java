package skywars.skywars.loaders;

import skywars.skywars.MainClass;

public class LoadConfig {
    public static void load() {
        if (MainClass.getData().getConfig().getConfigurationSection("players") == null) {
            MainClass.getData().getConfig().createSection("players");
        }
    }
}
