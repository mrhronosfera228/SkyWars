package skywars.skywars.gameLogic;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import skywars.skywars.MainClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static skywars.skywars.SkyWars.*;
import static skywars.skywars.gameLogic.ClassesItems.*;

public class GameClasses {
    private static Map<String, String> Class = new HashMap<>();

    //Реализация получения набора при старте игры
    public static void GiveClassItems(Player p) {
        switch(Class.get(p.getName())){
            case "&aScout":
                p.getInventory().addItem(ItemCreate(Material.STONE_SWORD, "Каменный меч"));
                p.getInventory().addItem(ItemCreate(PotionCreate(Material.POTION, PotionEffectType.SPEED, "Зелье скорости II", Color.WHITE, 800, 2).getType(), "Каменный меч"));
                break;
            case "&aArcher":
                p.getInventory().addItem(ItemCreate(Material.BOW, "Лук"));
                p.getInventory().addItem(new ItemStack(Material.ARROW, 8));
                break;
            case "&aTank":
                p.getInventory().addItem(ItemCreate(Material.DIAMOND_HELMET,"Алмазный шлем"));
                p.getInventory().addItem(ItemCreate(Material.IRON_LEGGINGS,"Железные поножи"));
                break;
            case "&aHiller":
                p.getInventory().addItem(PotionCreate(Material.POTION,PotionEffectType.REGENERATION, "Зелье регенерации I", Color.PURPLE, 800,1));
                p.getInventory().addItem(PotionCreate(Material.POTION,PotionEffectType.HEAL, "Зелье лечения II", Color.RED, 0,2));
                break;
            case "&aMiner":
                p.getInventory().addItem(ItemCreate(Material.IRON_PICKAXE,"Железная кирка"));
                p.getInventory().addItem(ItemCreate(Material.IRON_AXE,"Железный топор"));
                break;
            case "&aBomber":
                p.getInventory().addItem(new ItemStack(Material.TNT, 12));
                p.getInventory().addItem(ItemCreate(Material.FLINT,"Огниво"));
                break;
            case "&aBuilder":
                p.getInventory().addItem(new ItemStack(Material.STONE, 64));
                p.getInventory().addItem(new ItemStack(Material.WOOD, 16));
                break;
            case "&aFarmer":
                p.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 4));
                p.getInventory().addItem(new ItemStack(Material.BREAD, 8));
                break;
            case "&aTrapper":
                p.getInventory().addItem(new ItemStack(Material.WEB, 16));
                p.getInventory().addItem(ItemCreate(Material.SLIME_BALL,"Шар слизи"));
                break;
            default: break;
        }
    }
    //Получение класса
    public static void setClassItem(Player player, String className, List<String> PlayerClasses, ItemStack itemClass, ItemStack itemNety) {
        if (PlayerClasses.contains(className)) {
            getClassInventory().get(player.getName()).setItem(0, itemClass);
        } else {
            getClassInventory().get(player.getName()).setItem(0, itemNety);
        }
    }
    //Покупка класса
    public static void ClickClass(Player player, ItemStack itemClass, InventoryClickEvent event, Inventory inventory1, ItemStack clicked, ItemStack itemClassNety, String ClassName, int sum) {
        if (getClassInventory().get(player.getName()) != null) {
            if (inventory1.getName().equalsIgnoreCase(getClassInventory().get(player.getName()).getName())) {
                if (itemClass != null) {
                    if (clicked.equals(itemClass)) {
                        event.setCancelled(true);
                        Class.put(player.getName(), "Scout");
                        player.closeInventory();
                    }
                }
                if (itemClassNety != null) {
                    if (clicked.equals(itemClassNety)) {
                        event.setCancelled(true);
                        Inventory buy = Bukkit.createInventory(player, 27, "ClassBuy");
                        buy.setItem(15, ItemCreate(Material.REDSTONE_BLOCK, "&4&lОтмена"));
                        buy.setItem(11, ItemCreate(Material.EMERALD_BLOCK, "&a&lКупить", "&6&l2500 монет"));
                        player.openInventory(buy);
                    }
                }
            }
            if (inventory1.getName().equalsIgnoreCase("ClassBuy")) {
                if (clicked.equals(ItemCreate(Material.REDSTONE_BLOCK, "&4&lОтмена"))) {
                    event.setCancelled(true);
                    player.closeInventory();
                }
                if (clicked.equals(ItemCreate(Material.EMERALD_BLOCK, "&a&lКупить", "&6&l2500 монет"))) {
                    event.setCancelled(true);
                    if (config.getInt("players." + player.getName() + ".balance") >= sum) {
                        player.closeInventory();
                        config.set("players." + player.getName() + ".balance", config.getInt("players." + player.getName() + ".balance") - sum);
                        List<String> PlayerClasses = getClasses().get(player.getName());
                        PlayerClasses.add(ClassName);
                        getClasses().put(player.getName(), PlayerClasses);
                        config.set("players." + player.getName() + ".Class", getClasses().get(player.getName()));
                        Class.put(player.getName(), ClassName);
                        MainClass.getInstance().reloadConfig();
                        MainClass.getData().save();
                    } else {
                        player.sendMessage("Недостаточно монет.");
                    }
                }
            }
        }
    }
}
