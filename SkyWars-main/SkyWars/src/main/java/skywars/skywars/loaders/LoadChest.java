package skywars.skywars.loaders;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoadChest {
    private static List<Location> defaultChest = new ArrayList<>();
    private static Random random = new Random();
    private static List<Location> midChest = new ArrayList<>();
    private static List<ItemStack> DefaultItems = new ArrayList<>();
    private static List<ItemStack> MidItems = new ArrayList<>();

    public static void load() {
        midChest.add(new Location(Bukkit.getWorld("SkyWars"), 0.5, 56, -0.5));
        midChest.add(new Location(Bukkit.getWorld("SkyWars"), -0.5, 56, 0.5));
        midChest.add(new Location(Bukkit.getWorld("SkyWars"), 1.5, 56, 0.5));
        midChest.add(new Location(Bukkit.getWorld("SkyWars"), 0.5, 56, 1.5));
        defaultChest.add(new Location(Bukkit.getWorld("SkyWars"), 48.5, 41, -37.5));
        defaultChest.add(new Location(Bukkit.getWorld("SkyWars"), 26.5, 46, -47.5));
        defaultChest.add(new Location(Bukkit.getWorld("SkyWars"), 38.5, 46, -37.5));
        defaultChest.add(new Location(Bukkit.getWorld("SkyWars"), 48.5, 41, -47.5));
        defaultChest.add(new Location(Bukkit.getWorld("SkyWars"), 98.5, 46, -97.5));
        defaultChest.add(new Location(Bukkit.getWorld("SkyWars"), 48.5, 41, -37.5));
        defaultChest.add(new Location(Bukkit.getWorld("SkyWars"), 68.5, 46, -67.5));
        defaultChest.add(new Location(Bukkit.getWorld("SkyWars"), 78.5, 41, -77.5));
        defaultChest.add(new Location(Bukkit.getWorld("SkyWars"), 18.5, 46, -17.5));
        defaultChest.add(new Location(Bukkit.getWorld("SkyWars"), 58.5, 41, -57.5));
        defaultChest.add(new Location(Bukkit.getWorld("SkyWars"), 58.5, 46, -57.5));
        defaultChest.add(new Location(Bukkit.getWorld("SkyWars"), 98.5, 41, -97.5));
        defaultChest.add(new Location(Bukkit.getWorld("SkyWars"), 78.5, 46, -77.5));
        SetMidChest();
        SetDefaultChest();
    }
    public static void SetMidChest() {
        for (int i = 0; i < midChest.size(); i++) {
            Chest chest = (Chest) midChest.get(i).getBlock().getState();
            ArrayList<Integer> slots = new ArrayList<>();
            for (int j = 0; j < 6;) {
                int items = random.nextInt(20);
                int itemSlot = random.nextInt(27);
                if (!slots.contains(itemSlot)) {
                    slots.add(itemSlot);
                    chest.getBlockInventory().setItem(slots.get(j), MidItems.get(items));
                    j++;
                }
            }
        }
    }
    public static void SetDefaultChest() {
        for (int i = 0; i < defaultChest.size(); i++) {
            Chest chest = (Chest) defaultChest.get(i).getBlock().getState();
            ArrayList<Integer> slots = new ArrayList<>();
            for (int j = 0; j < 6;) {
                int items = random.nextInt(20);
                int itemSlot = random.nextInt(27);
                if (!slots.contains(itemSlot)) {
                    slots.add(itemSlot);
                    chest.getBlockInventory().setItem(slots.get(j), DefaultItems.get(items));
                    j++;
                }
            }
        }
    }
}
