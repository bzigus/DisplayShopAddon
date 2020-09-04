package me.bugzigus.DisplayShopAddon;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import xzot1k.plugins.ds.api.objects.LocationClone;
import xzot1k.plugins.ds.api.objects.Shop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class YmlFIle {

    private final DisplayShopAddon pluginData;

    public YmlFIle(DisplayShopAddon plugin) {

        this.pluginData = plugin;

    }


    public static boolean isUUID(String string) {
        try {
            UUID.fromString(string);
            return true;
        } catch (Exception ex) {
            return false;
        }


    }

    public static List<Integer> getNumbersInRange(int start, int end) {
        List<Integer> result = new ArrayList<>();
        for (int i = start; i < end; i++) {
            result.add(i);
        }
        return result;
    }

    public static void fileClear(Player player, int number) {

        String playerUUID = player.getUniqueId().toString();

        Plugin plugin = DisplayShopAddon.pluginInstance();
        File f = new File(plugin.getDataFolder() + File.separator + "PlayerDatabase" + File.separator + playerUUID + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);

        player.sendMessage("Shop"+ number + ".UUID");

        playerData.set("Shop"+ number + ".Enabled", null);
        playerData.set("Shop"+ number + ".UUID", null);
        playerData.set("Shop"+ number + ".ShopX", null);
        playerData.set("Shop"+ number + ".ShopY", null);
        playerData.set("Shop"+ number + ".ShopZ", null);
        playerData.set("Shop"+ number + ".ShopWorld", null);
        playerData.set("Shop"+ number + ".BlockX", null);
        playerData.set("Shop"+ number + ".BlockY", null);
        playerData.set("Shop"+ number + ".BlockZ", null);
        playerData.set("Shop"+ number + ".BlockWorld", null);

        try {
            playerData.save(f);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public boolean fileWrite(Player player, Block block, Shop shop, int number) {

        String playerUUID = player.getUniqueId().toString();
        String playerName = player.getName();
        //Block Location XYZ
        Location blockLocation = block.getLocation();
        int blockX = blockLocation.getBlockX();
        int blockY = blockLocation.getBlockY();
        int blockZ = blockLocation.getBlockZ();
        String blockWorld = blockLocation.getWorld().getName();

        //Shop Location XYZ
        LocationClone shopLocation = shop.getBaseLocation();
        String shopUUID = shop.getShopId().toString();
        double shopX = shopLocation.getX();
        double shopY = shopLocation.getY();
        double shopZ = shopLocation.getZ();
        String shopWorld = shopLocation.getWorldName();

        File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("DisplayShopAddon").getDataFolder() + File.separator + "PlayerDatabase");
        File f = new File(userdata, File.separator + playerUUID + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);

        if (!userdata.exists()) {
            userdata.mkdirs();
        }

        if (!f.exists()) {

            try {

                //Create this file
                f.createNewFile();

                //Data Sections
                playerData.createSection("Data");
                playerData.set("Data.Name", playerName);
                playerData.set("Data.UUID", playerUUID);

                //Linked Sections
                getInfo(number, blockX, blockY, blockZ, blockWorld, shopUUID, shopX, shopY, shopZ, shopWorld, f, playerData);

                pluginData.getReadLocation().getStorage().put(blockLocation, shop);

            } catch (IOException exception) {

                exception.printStackTrace();
            }

        } else {

            try {

                //Linked Sections
                getInfo(number, blockX, blockY, blockZ, blockWorld, shopUUID, shopX, shopY, shopZ, shopWorld, f, playerData);

                pluginData.getReadLocation().storage.put(blockLocation, shop);

            }
            catch (IOException e){

                e.printStackTrace();

            }

        }

        return true;
    }

    private static void getInfo(int number, int blockX, int blockY, int blockZ, String blockWorld, String shopUUID, double shopX, double shopY, double shopZ, String shopWorld, File f, FileConfiguration playerData) throws IOException {
        playerData.createSection("Shop" + number );
        playerData.set("Shop"+ number + ".Enabled", true);
        playerData.set("Shop"+ number + ".UUID", shopUUID);
        playerData.set("Shop"+ number + ".ShopX", shopX);
        playerData.set("Shop"+ number + ".ShopY", shopY);
        playerData.set("Shop"+ number + ".ShopZ", shopZ);
        playerData.set("Shop"+ number + ".ShopWorld", shopWorld);
        playerData.set("Shop"+ number + ".BlockX", blockX);
        playerData.set("Shop"+ number + ".BlockY", blockY);
        playerData.set("Shop"+ number + ".BlockZ", blockZ);
        playerData.set("Shop"+ number + ".BlockWorld", blockWorld);


        playerData.save(f);
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    //WIP
//    public boolean isBlock (Block block) {
//
//        if (pluginData.getConfig().getBoolean("Shop.AllowHopper") && !pluginData.getConfig().getBoolean("Shop.AllowChest")) {
//            if (block.getType() == Material.HOPPER) {
//                return true;
//            }
//        } else if (pluginData.getConfig().getBoolean("Shop.AllowHopper") && pluginData.getConfig().getBoolean("Shop.AllowChest")) {
//
//            if (block.getType() == Material.HOPPER || block.getType() == Material.CHEST) {
//                return true;
//            }
//
//        } else if (!pluginData.getConfig().getBoolean("Shop.AllowHopper") && pluginData.getConfig().getBoolean("Shop.AllowChest")) {
//            if (block.getType() == Material.CHEST) {
//                return true;
//            }
//        } else {
//
//            return false;
//        }
//        return true;
//    }


}
