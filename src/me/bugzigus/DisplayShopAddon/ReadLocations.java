package me.bugzigus.DisplayShopAddon;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.Hash;
import org.bukkit.plugin.Plugin;
import xzot1k.plugins.ds.DisplayShops;
import xzot1k.plugins.ds.api.objects.Shop;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class ReadLocations {

    private Map<Location, Shop> storage = new HashMap<Location, Shop>();
    Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("DisplayShopAddon");
    File directory = new File(plugin.getDataFolder() + File.separator + "PlayerDatabase");
    private final File[] files = directory.listFiles();



    public void readFiles() {

        for (File f : files) {

            List<Integer> numbers = YmlFIle.getNumbersInRange(1, 5);
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

                    getStorage().put(blockLocation, shop);
                }
            }
        }
    }
    public Map<Location, Shop> getStorage() {
        return storage;

    }
}
