package skywars.skywars.gameLogic;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;

public class ClassesItems {
    //Перегрузки для создания предметов
    public static ItemStack ItemCreate(Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        item.setItemMeta(meta);
        return item;
    }
    static ItemStack ItemCreate(Material material, String name, String lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        meta.setLore(Collections.singletonList(ChatColor.translateAlternateColorCodes('&', lore)));
        item.setItemMeta(meta);
        return item;
    }
    //Создание зелий из классов
    public static ItemStack PotionCreate(Material material, PotionEffectType type, String name, Color color, int duration, int level){
        ItemStack item = new ItemStack(material);
        PotionMeta meta = (PotionMeta) item.getItemMeta();
        PotionEffect effect = new PotionEffect(type, duration, level);
        meta.setColor(color);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        meta.addCustomEffect(effect, true);
        item.setItemMeta(meta);
        return item;
    }
}
