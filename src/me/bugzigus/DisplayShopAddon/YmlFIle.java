package me.bugzigus.DisplayShopAddon;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import xzot1k.plugins.ds.api.objects.LocationClone;
import xzot1k.plugins.ds.api.objects.Shop;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class YmlFIle {

    public static boolean isUUID(String string) {
        try {
            UUID.fromString(string);
            return true;
        } catch (Exception ex) {
            return false;
        }


    }

    public static void fileClear(Player player) {

        String playerUUID = player.getUniqueId().toString();
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("DisplayShopAddon");

        File f = new File(plugin.getDataFolder() + File.separator + "PlayerDatabase" + File.separator + playerUUID + ".yml");

        f.delete();
    }

    public static boolean fileWrite(Player player, Block block, Shop shop) {

        String playerUUID = player.getUniqueId().toString();
        String playerName = player.getName();
        //Block Location XYZ
        int blockX = block.getX();
        int blockY = block.getY();
        int blockZ = block.getZ();

        //Shop Location XYZ
        LocationClone shopLocation = (LocationClone) shop.getBaseLocation();
        String shopUUID = shop.getShopId().toString();
        double shopX = shopLocation.getX();
        double shopY = shopLocation.getY();
        double shopZ = shopLocation.getZ();

        File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("DisplayShopAddon").getDataFolder() + File.separator + "PlayerDatabase");
        File f = new File(userdata, File.separator + playerUUID + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);

        if (!f.exists()){

            try {

                //Create this file
                f.createNewFile();

                //Data Sections
                playerData.createSection("Data");
                playerData.set("Data.Name", playerName);
                playerData.set("Data.UUID", playerUUID);

                //Linked Sections
                playerData.createSection("Shop");
                playerData.set("Shop.UUID", shopUUID);
                playerData.set("Shop.ShopX", shopX);
                playerData.set("Shop.ShopY", shopY);
                playerData.set("Shop.ShopZ", shopZ);
                playerData.set("Shop.BlockX", blockX);
                playerData.set("Shop.BlockY", blockY);
                playerData.set("Shop.BlockZ", blockZ);


                player.sendMessage(ChatColor.GREEN + "Successfully linked this chest to " + shop.getShopId());
                playerData.save(f);

            } catch (IOException exception) {

                exception.printStackTrace();
            }

        } else {

            player.sendMessage(ChatColor.DARK_RED + "You already have a chest linked!");

        }

        return true;
    }

    public static void fileRead(Player player)  {

        String playerUUID = player.getUniqueId().toString();

        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("DisplayShopAddon");


        //File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("DisplayShopAddon").getDataFolder() + File.separator + "PlayerDatabase");
        File f = new File(plugin.getDataFolder() + File.separator + "PlayerDatabase" + File.separator + playerUUID + ".yml");


        FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);

        if (f.exists()) {

            String shopUUID = playerData.getString("Shop.UUID");
            Double shopX = playerData.getDouble("Shop.shopX");
            Double shopY = playerData.getDouble("Shop.shopY");
            Double shopZ = playerData.getDouble("Shop.shopZ");
            Double blockX = playerData.getDouble("Shop.blockX");
            Double blockY = playerData.getDouble("Shop.blockY");
            Double blockZ = playerData.getDouble("Shop.blockZ");

            player.sendMessage(ChatColor.GREEN + "Success!");
            player.sendMessage("Shop UUID: " + shopUUID);
            player.sendMessage("Shop XYZ: " + shopX + ", " + shopY + ", " + shopZ);
            player.sendMessage("Block XYZ: " + blockX + ", " + blockY + ", " + blockZ);

        } else {

            player.sendMessage("No info Found :( (Does the file Exist?)" + f.exists());

        }


//        String shopUUID = plugin.getConfig().getConfigurationSection(f.getPath()).get("Shop.UUID").toString();
//
    }

}
