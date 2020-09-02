package me.bugzigus.DisplayShopAddon;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import xzot1k.plugins.ds.DisplayShops;
import xzot1k.plugins.ds.api.objects.Shop;

import java.io.File;
import java.util.List;
import java.util.Map;

public class ChestListener implements Listener {


    private final DisplayShopAddon plugin;

    public ChestListener(DisplayShopAddon plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void chestInteraction(InventoryMoveItemEvent event) {

        Map<Location, Shop> storage = plugin.getReadLocation().getStorage();

        //thing to make it work
        InventoryHolder eventHolder = event.getDestination().getHolder();

        if (eventHolder instanceof BlockState) {
            // Is it the right chest?
            Location blockLocation = ((BlockState) eventHolder).getLocation();

            if (storage.containsKey(blockLocation)) {
                Shop shop = storage.get(blockLocation);

                // Get items to compare
                ItemStack inventoryItem = event.getItem();
                ItemStack shopItem = shop.getShopItem();

                Inventory chestInventory = event.getDestination();

                // Compare the items added
                if (inventoryItem.isSimilar(shopItem)) {
                    int previousStock = shop.getStock();
                    int stock = previousStock + event.getItem().getAmount();
                    if (DisplayShops.getPluginInstance().getManager().getMaxStock(shop) > stock) {
                        shop.setStock(stock);
                        Bukkit.getScheduler().runTaskLater(plugin, () -> {

                            chestInventory.remove(inventoryItem);

                        }, 1L);
                    }
                }
            }
        }
    }
}