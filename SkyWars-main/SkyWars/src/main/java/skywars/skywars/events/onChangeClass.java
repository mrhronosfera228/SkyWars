package skywars.skywars.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import skywars.skywars.MainClass;

import java.util.Collections;
import java.util.List;


import static skywars.skywars.SkyWars.*;
import static skywars.skywars.gameLogic.ClassesItems.ItemCreate;
import static skywars.skywars.gameLogic.GameClasses.setClassItem;
import static skywars.skywars.loaders.LoadClasses.ClassItems;
import static skywars.skywars.loaders.LoadClasses.ClassItemsBought;

public class onChangeClass implements Listener {
    public onChangeClass() {

    }
    @EventHandler
    public void ChangeClass(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getPlayer().getItemInHand().equals(ItemCreate(Material.BOOK, "&5&lВыбор класса"))) {
            if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
            if (event.getHand() != EquipmentSlot.HAND) return;
            getClassInventory().put(player.getName(), Bukkit.createInventory(player, 54, "Class"));
            MainClass.getInstance().reloadConfig();
            MainClass.getData().save();
            List<String> PlayerClasses = getClasses().get(player.getName());
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
            player.openInventory(getClassInventory().get(player.getName()));
        }
    }
}
