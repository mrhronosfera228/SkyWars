package skywars.skywars;

import org.apache.commons.io.FileUtils;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import skywars.skywars.loaders.LoadChest;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static skywars.skywars.loaders.LoadClasses.ClassItems;
import static skywars.skywars.loaders.LoadClasses.ClassItemsBought;
import static skywars.skywars.loaders.LocationLoads.SkyWarsLocation;
import static skywars.skywars.loaders.SkyWarsCubLoad.SkyWarsCuboid;
public class SkyWars implements Listener {
    public static boolean GameStart = false;
    private Map<String, Inventory> ClassInventory = new HashMap<>();
    private static List<String> AlivePlayers = new ArrayList<>();
    private HashMap<Player, Integer> falldamage = new HashMap<>();
    public static boolean InLobby = true;
    private static Map<String, String> Class = new HashMap<>();
    private static ItemStack ClassItem;
    private static Map<String, List<String>> Classes = new HashMap<>();
    public static FileConfiguration config = MainClass.getData().getConfig();
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (InLobby) {
            inCub(player);
            loadInConfig(player);
            player.getInventory().clear();
            player.getInventory().addItem(ClassItem);
            player.setGameMode(GameMode.SURVIVAL);
            if (Bukkit.getOnlinePlayers().size() > 1) {
                GameStart();
            }
        } else {
            player.setGameMode(GameMode.SPECTATOR);
        }
    }
    //Отключение возможности ломать блоки, пока не началась игра
    @EventHandler
    public void onBreakBlock(BlockBreakEvent event) {
        if (!GameStart) {
            event.setCancelled(true);
        }
    }
    //Отключение возможности ставить блоки, пока не началась игра
    @EventHandler
    public void onPlacedBlock(BlockPlaceEvent event) {
        if (!GameStart) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.SPECTATOR && GameStart) {
            AlivePlayers.remove(player.getName());
            if (AlivePlayers.size() <= 1) {
                GameEnd();
            }
        }
    }
    //Старт игры
    public static void GameStart() {
        InLobby = false;
        Bukkit.getScheduler().runTaskLater(MainClass.getInstance(), () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                AlivePlayers.add(p.getName());
                p.closeInventory();
                p.getInventory().clear();
                GiveClassItems(p);
                new Location(Bukkit.getWorld("SkyWars"), p.getLocation().getX(), p.getLocation().getY() - 1, p.getLocation().getZ()).getBlock().setType(Material.AIR);
            }
            GameStart = true;
        }, 200);
        Bukkit.getScheduler().runTaskLater(MainClass.getInstance(), () -> {
            LoadChest.SetDefaultChest();
            LoadChest.SetMidChest();
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendTitle(ChatColor.translateAlternateColorCodes('&', "&6&Сундуки обновлены"), "", 10, 20, 40);
            }
        }, 2600);
    }
    //Конец игры
    public static void GameEnd() {
        Player player = Bukkit.getPlayer(AlivePlayers.get(0));
        player.sendTitle(ChatColor.translateAlternateColorCodes('&', "&6&lVICTORY"), "", 10, 20, 40);
        Bukkit.getScheduler().runTaskLater(MainClass.getInstance(), () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.kickPlayer(("Сервер перезагружается"));
            }
            deleteWorld("SkyWars");
            Bukkit.getServer().reload();
        }, 200);
    }
    //Получение предметов с класса
    @EventHandler
    public void InventoryClicked(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        if (event.getInventory().equals(ClassInventory.get(player.getName()))) {
            ClickClass(player, ClassItemsBought.get(0), event, event.getInventory(), clicked, ClassItems.get(0), "Scout", 2500);
            ClickClass(player, ClassItemsBought.get(1), event, event.getInventory(), clicked, ClassItems.get(1), "Archer", 300);
            ClickClass(player, ClassItemsBought.get(2), event, event.getInventory(), clicked, ClassItems.get(2), "Tank", 2000);
            ClickClass(player, ClassItemsBought.get(3), event, event.getInventory(), clicked, ClassItems.get(3), "Hiller", 1500);
            ClickClass(player, ClassItemsBought.get(4), event, event.getInventory(), clicked, ClassItems.get(4), "Miner", 1000);
            ClickClass(player, ClassItemsBought.get(5), event, event.getInventory(), clicked, ClassItems.get(5), "Bomber", 3000);
            ClickClass(player, ClassItemsBought.get(6), event, event.getInventory(), clicked, ClassItems.get(6), "Builder", 2000);
            ClickClass(player, ClassItemsBought.get(7), event, event.getInventory(), clicked, ClassItems.get(7), "Farmer", 2400);
            ClickClass(player, ClassItemsBought.get(8), event, event.getInventory(), clicked, ClassItems.get(8), "Trapper", 2200);

        }
    }
    //Реализация смены метода класса
    @EventHandler
    public void ChangeClass(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getPlayer().getItemInHand().equals(ItemCreate(Material.BOOK, "&5&lВыбор класса"))) {
            if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
            if (event.getHand() != EquipmentSlot.HAND) return;
            ClassInventory.put(player.getName(), Bukkit.createInventory(player, 54, "Class"));
            MainClass.getInstance().reloadConfig();
            MainClass.getData().save();
            List<String> PlayerClasses = Classes.get(player.getName());
            if (PlayerClasses == null) {
                PlayerClasses = Collections.singletonList("");
            }
            setClassItem(player, "Scout", PlayerClasses, ClassItemsBought.get(0), ClassItems.get(0));
            setClassItem(player, "Archer", PlayerClasses, ClassItemsBought.get(1), ClassItems.get(1));
            setClassItem(player, "Tank", PlayerClasses, ClassItemsBought.get(2), ClassItems.get(2));
            setClassItem(player, "Hiller", PlayerClasses, ClassItemsBought.get(3), ClassItems.get(3));
            setClassItem(player, "Miner", PlayerClasses, ClassItemsBought.get(4), ClassItems.get(4));
            setClassItem(player, "Bomber", PlayerClasses, ClassItemsBought.get(5), ClassItems.get(5));
            setClassItem(player, "Builder", PlayerClasses, ClassItemsBought.get(6), ClassItems.get(6));
            setClassItem(player, "Farmer", PlayerClasses, ClassItemsBought.get(7), ClassItems.get(7));
            setClassItem(player, "Trapper", PlayerClasses, ClassItemsBought.get(8), ClassItems.get(8));
            player.openInventory(ClassInventory.get(player.getName()));
        }
    }
    @EventHandler
    public void PlayerGetDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            //Реализация смерти игрока
            if (event.getCause().equals(EntityDamageEvent.DamageCause.VOID)) {
                event.setCancelled(true);
                player.setGameMode(GameMode.SPECTATOR);
                AlivePlayers.remove(player.getName());
                if (AlivePlayers.size() <= 1) {
                    GameEnd();
                }
            }
            //Отключение урона от падения
            if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                if (falldamage.get(player) == 0) {
                    falldamage.put(player, 1);
                    event.setCancelled(true);
                }
            }
        }
    }
    //Реализация смерти игрока
    @EventHandler
    public void PlayerDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            player.setGameMode(GameMode.SPECTATOR);
            AlivePlayers.remove(player.getName());
            if (AlivePlayers.size() <= 1) {
                GameEnd();
            }
        }
    }

    //Телепортация игроков в стартовую позицию
    public void inCub(Player p) {
        for (int i = 0; i < SkyWarsCuboid.size(); i++) {
            boolean stop = true;
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (SkyWarsCuboid.get(i).contains(player.getLocation())) {
                    stop = false;
                }
            }
            if (stop) {
                p.getPlayer().teleport(SkyWarsLocation.get(i));
            }
        }
    }
    //Прогрузка конфигов
    public void loadInConfig(Player player) {
        if (config.getConfigurationSection("players." + player.getName()) == null) {
            config.createSection("players." + player.getName());
            config.createSection("players." + player.getName() + ".name");
            config.createSection("players." + player.getName() + ".Class");
            config.createSection("players." + player.getName() + ".balance");
            MainClass.getInstance().reloadConfig();
            MainClass.getData().save();
            Classes.put(player.getName(), config.getStringList("players." + player.getName() + ".Class"));
        }
    }
    @EventHandler
    public void OnDropInLobby(PlayerDropItemEvent event) {
        if (!GameStart) {
            event.setCancelled(true);
        }
    }
    //Получение класса
    @EventHandler
    public void onPlayerClicked(PlayerInteractEvent event) {
        if (event.getItem() != null) {
            if ((event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) && event.getItem().equals(ClassItem)) {
                Player p = event.getPlayer();
                p.openInventory(ClassInventory.get(p.getName()));
            }
        }
    }
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
    //Перегрузки для создания предметов
    private static ItemStack ItemCreate(Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        item.setItemMeta(meta);
        return item;
    }
    private static ItemStack ItemCreate(Material material, String name, String lore) {
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
    public void setClassItem(Player player, String className, List<String> PlayerClasses, ItemStack itemClass, ItemStack itemNety) {
        if (PlayerClasses.contains(className)) {
            ClassInventory.get(player.getName()).setItem(0, itemClass);
        } else {
            ClassInventory.get(player.getName()).setItem(0, itemNety);
        }
    }
    //Покупка класса
    public void ClickClass(Player player, ItemStack itemClass, InventoryClickEvent event, Inventory inventory1, ItemStack clicked, ItemStack itemClassNety, String ClassName, int sum) {
        if (ClassInventory.get(player.getName()) != null) {
            if (inventory1.getName().equalsIgnoreCase(ClassInventory.get(player.getName()).getName())) {
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
                        List<String> PlayerClasses = Classes.get(player.getName());
                        PlayerClasses.add(ClassName);
                        Classes.put(player.getName(), PlayerClasses);
                        config.set("players." + player.getName() + ".Class", Classes.get(player.getName()));
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
