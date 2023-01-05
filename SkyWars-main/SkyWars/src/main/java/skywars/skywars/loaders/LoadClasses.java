package skywars.skywars.loaders;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadClasses {
    public static List<ItemStack> ClassItemsBought = new ArrayList<>();
    public static List<ItemStack> ClassItems = new ArrayList<>();

    public static void load() {
        ClassItemsBought.add(ItemCreate(Material.STONE_SWORD, "&aScout", new ArrayList<>(Arrays.asList("&8&lСодержит: ", "&7&lКаменный меч", "&5&lЗелье скорости  II", "&6&lВыбрать класс."))));
        ClassItemsBought.add(ItemCreate(Material.BOW, "&aArcher", new ArrayList<>(Arrays.asList("&8&lСодержит: ", "&7&lЛук", "&5&l8 стрел  ", "&6&lВыбрать класс."))));
        ClassItemsBought.add(ItemCreate(Material.IRON_CHESTPLATE, "&aTank", new ArrayList<>(Arrays.asList("&8&lСодержит: ", "&7&lАлмазный шлем", "&5&lЖелезные поножи", "&6&lВыбрать класс."))));
        ClassItemsBought.add(ItemCreate(Material.BLAZE_ROD, "&aHiller", new ArrayList<>(Arrays.asList("&8&lСодержит: ", "&7&lЗелье регенерации I", "&5&lЗелье лечения II", "&6&lВыбрать класс."))));
        ClassItemsBought.add(ItemCreate(Material.IRON_PICKAXE, "&aMiner", new ArrayList<>(Arrays.asList("&8&lСодержит: ", "&7&lЖелезную кирку", "&5&lЖелезный топор", "&6&lВыбрать класс."))));
        ClassItemsBought.add(ItemCreate(Material.TNT, "&aBomber", new ArrayList<>(Arrays.asList("&8&lСодержит: ", "&7&l12 блоков динамита", "&5&lЗажигалку", "&6&lВыбрать класс."))));
        ClassItemsBought.add(ItemCreate(Material.STONE, "&aBuilder", new ArrayList<>(Arrays.asList("&8&lСодержит: ", "&7&l64 каменных блока", "&5&l16 досок", "&6&lВыбрать класс."))));
        ClassItemsBought.add(ItemCreate(Material.CAKE ,"&aFarmer", new ArrayList<>(Arrays.asList("&8&lСодержит: ", "&7&l4 стейков", "&5&l8 хлеба  ", "&6&lВыбрать класс."))));
        ClassItemsBought.add(ItemCreate(Material.SLIME_BALL, "&aTrapper", new ArrayList<>(Arrays.asList("&8&lСодержит: ", "&7&l16 блоков паутины", "&5&lШар слизи", "&6&lВыбрать класс."))));
        ClassItems.add(ItemCreate(Material.STONE_SWORD, "&aScout", new ArrayList<>(Arrays.asList("&8&lСодержит: ", "&7&lКаменный меч", "&5&lЗелье скорости  ", "&6&lКупить класс."))));
        ClassItems.add(ItemCreate(Material.BOW, "&aArcher", new ArrayList<>(Arrays.asList("&8&lСодержит: ", "&7&lЛук", "&5&l8 стрел  ", "&6&lКупить класс."))));
        ClassItems.add(ItemCreate(Material.IRON_CHESTPLATE, "&aTank", new ArrayList<>(Arrays.asList("&8&lСодержит: ", "&7&lАлмазный шлем", "&5&lЖелезные поножи  ", "&6&lКупить класс."))));
        ClassItems.add(ItemCreate(Material.BLAZE_ROD, "&aHiller", new ArrayList<>(Arrays.asList("&8&lСодержит: ", "&7&lЗелье регенерации I", "&5&lЗелье лечения II", "&6&lКупить класс."))));
        ClassItems.add(ItemCreate(Material.IRON_PICKAXE, "&aMiner", new ArrayList<>(Arrays.asList("&8&lСодержит: ", "&7&lЖелезную кирку", "&5&lЖелезный топор", "&6&lКупить класс."))));
        ClassItems.add(ItemCreate(Material.TNT, "&aBomber", new ArrayList<>(Arrays.asList("&8&lСодержит: ", "&7&l12 блоков динамита", "&5&lЗажигалку", "&6&lКупить класс."))));
        ClassItems.add(ItemCreate(Material.STONE, "&aBuilder", new ArrayList<>(Arrays.asList("&8&lСодержит: ", "&7&l64 каменных блока", "&5&l16 блоков дерева", "&6&lКупить класс."))));
        ClassItems.add(ItemCreate(Material.CAKE ,"&aFarmer", new ArrayList<>(Arrays.asList("&8&lСодержит: ", "&7&l8 стейков", "&5&l4 хлеба  ", "&6&lКупить класс."))));
        ClassItems.add(ItemCreate(Material.SLIME_BALL, "&aTrapper", new ArrayList<>(Arrays.asList("&8&lСодержит: ", "&7&l16 блоков паутины", "&5&lШар слизи", "&6&lКупить класс."))));
    }
    public static ItemStack ItemCreate(Material material, String name, List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        meta.setLore(toColor(lore));
        item.setItemMeta(meta);
        return item;
    }
    public static List<String> toColor(List<String> lore) {
        List<String> newLore = new ArrayList<>();
        for (int i = 0; i < lore.size(); i++) {
            newLore.add(ChatColor.translateAlternateColorCodes('&', lore.get(i)));
        }
        return newLore;
    }
}
