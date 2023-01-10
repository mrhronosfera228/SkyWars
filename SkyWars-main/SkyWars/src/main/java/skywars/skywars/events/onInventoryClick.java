package skywars.skywars.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static skywars.skywars.SkyWars.getClassInventory;
import static skywars.skywars.gameLogic.GameClasses.ClickClass;
import static skywars.skywars.loaders.LoadClasses.ClassItems;
import static skywars.skywars.loaders.LoadClasses.ClassItemsBought;

public class onInventoryClick implements Listener {
    public onInventoryClick() {

    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        if (event.getInventory().equals(getClassInventory().get(player.getName()))) {
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
}
