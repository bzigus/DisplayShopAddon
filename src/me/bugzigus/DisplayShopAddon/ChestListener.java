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

public class ChestListener implements Listener {

    @EventHandler
    public void chestInteraction(InventoryMoveItemEvent event) {

        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("DisplayShopAddon");
        File directory = new File(plugin.getDataFolder() + File.separator + "PlayerDatabase");
        File[] files = directory.listFiles();

        List<Integer> numbers = YmlFIle.getNumbersInRange(1, 5);

        for (File f : files) {
            FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);

            for (int number : numbers) {

                if (playerData.getBoolean("Shop" + number + ".Enabled") == true) {
                    //Getting locations from storage
                    Double shopX = playerData.getDouble("Shop" + number + ".ShopX");
                    Double shopY = playerData.getDouble("Shop" + number + ".ShopY");
                    Double shopZ = playerData.getDouble("Shop" + number + ".ShopZ");
                    String shopWorld = playerData.getString("Shop" + number + ".ShopWorld");
                    Double blockX = playerData.getDouble("Shop" + number + ".BlockX");
                    Double blockY = playerData.getDouble("Shop" + number + ".BlockY");
                    Double blockZ = playerData.getDouble("Shop" + number + ".BlockZ");
                    String blockWorld = playerData.getString("Shop" + number + ".BlockWorld");

                    //Getting world info
                    World sWorld = Bukkit.getWorld(shopWorld);
                    World bWorld = Bukkit.getWorld(blockWorld);

                    //Get a Location from World & XYZ
                    Location shopLocation = new Location(sWorld, shopX, shopY, shopZ);
                    Location blockLocation = new Location(bWorld, blockX, blockY, blockZ);

                    Shop shop = DisplayShops.getPluginInstance().getManager().getShop(shopLocation);

                    //thing to make it work
                    InventoryHolder eventHolder = event.getDestination().getHolder();

                    if (eventHolder instanceof BlockState) {

                        // Is it the right chest?
                        Location eventLocation = ((BlockState) eventHolder).getLocation();

                        if (eventLocation.distance(blockLocation) == 0) {

                            // Get items to compare
                            ItemStack inventoryItem = event.getItem();
                            ItemStack shopItem = shop.getShopItem();

                            // Compare the items added
                            if (inventoryItem.isSimilar(shopItem)) {

                                // Add Stock to shop
                                int previousStock = shop.getStock();
                                int stock = previousStock + event.getItem().getAmount();
                                shop.setStock(stock);
                                event.getItem().setAmount(0);

                            }
                        }
                    }
                }
            }
        }
    }
}
