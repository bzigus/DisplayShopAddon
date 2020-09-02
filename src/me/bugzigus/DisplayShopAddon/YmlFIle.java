package me.bugzigus.DisplayShopAddon;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("DisplayShopAddon");

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
            player.sendMessage("Successfully unlinked: " + playerData.getString("Shop" + number + ".UUID"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static boolean fileWrite(Player player, Block block, Shop shop, int number) {

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

        if (!f.exists()) {

            try {

                //Create this file
                f.createNewFile();

                //Data Sections
                playerData.createSection("Data");
                playerData.set("Data.Name", playerName);
                playerData.set("Data.UUID", playerUUID);

                //Linked Sections
                playerData.createSection("Shop1");
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


                player.sendMessage(ChatColor.GREEN + "Successfully linked this chest to " + shop.getShopId());
                playerData.save(f);

            } catch (IOException exception) {

                exception.printStackTrace();
            }

        } else {

            try {


                if (number == 1) {

                    if (!playerData.isConfigurationSection("Shop1")) {
                        playerData.createSection("Shop1");
                    } else {

                        player.sendMessage("Successfully set shop link " + number);

                    }
                    playerData.set("Shop1.Enabled", true);
                    playerData.set("Shop1.UUID", shopUUID);
                    playerData.set("Shop1.ShopX", shopX);
                    playerData.set("Shop1.ShopY", shopY);
                    playerData.set("Shop1.ShopZ", shopZ);
                    playerData.set("Shop1.ShopWorld", shopWorld);
                    playerData.set("Shop1.BlockX", blockX);
                    playerData.set("Shop1.BlockY", blockY);
                    playerData.set("Shop1.BlockZ", blockZ);
                    playerData.set("Shop1.BlockWorld", blockWorld);

                    playerData.save(f);
                    player.sendMessage(ChatColor.GREEN + "Successfully linked this chest to " + shop.getShopId());

                } else if (number == 2) {

                    if (!playerData.isConfigurationSection("Shop2")) {
                        playerData.createSection("Shop2");
                    } else {

                        player.sendMessage("Successfully set shop link " + number);

                    }
                    playerData.set("Shop2.Enabled", true);

                    playerData.set("Shop2.UUID", shopUUID);
                    playerData.set("Shop2.ShopX", shopX);
                    playerData.set("Shop2.ShopY", shopY);
                    playerData.set("Shop2.ShopZ", shopZ);
                    playerData.set("Shop2.ShopWorld", shopWorld);
                    playerData.set("Shop2.BlockX", blockX);
                    playerData.set("Shop2.BlockY", blockY);
                    playerData.set("Shop2.BlockZ", blockZ);
                    playerData.set("Shop2.BlockWorld", blockWorld);
                    playerData.save(f);

                    player.sendMessage(ChatColor.GREEN + "Successfully linked this chest to " + shop.getShopId());

                } else if (number == 3) {

                    if (!playerData.isConfigurationSection("Shop3")) {
                        playerData.createSection("Shop3");
                    } else {

                        player.sendMessage("Successfully set shop link " + number);

                    }
                    playerData.set("Shop3.Enabled", true);

                    playerData.set("Shop3.UUID", shopUUID);
                    playerData.set("Shop3.ShopX", shopX);
                    playerData.set("Shop3.ShopY", shopY);
                    playerData.set("Shop3.ShopZ", shopZ);
                    playerData.set("Shop3.ShopWorld", shopWorld);
                    playerData.set("Shop3.BlockX", blockX);
                    playerData.set("Shop3.BlockY", blockY);
                    playerData.set("Shop3.BlockZ", blockZ);
                    playerData.set("Shop3.BlockWorld", blockWorld);
                    playerData.save(f);

                    player.sendMessage(ChatColor.GREEN + "Successfully linked this chest to " + shop.getShopId());

                } else if (number == 4) {

                    if (!playerData.isConfigurationSection("Shop4")) {
                        playerData.createSection("Shop4");
                    } else {

                        player.sendMessage("Successfully set shop link " + number);

                    }
                    playerData.set("Shop4.Enabled", true);

                    playerData.set("Shop4.UUID", shopUUID);
                    playerData.set("Shop4.ShopX", shopX);
                    playerData.set("Shop4.ShopY", shopY);
                    playerData.set("Shop4.ShopZ", shopZ);
                    playerData.set("Shop4.ShopWorld", shopWorld);
                    playerData.set("Shop4.BlockX", blockX);
                    playerData.set("Shop4.BlockY", blockY);
                    playerData.set("Shop4.BlockZ", blockZ);
                    playerData.set("Shop4.BlockWorld", blockWorld);
                    playerData.save(f);

                    player.sendMessage(ChatColor.GREEN + "Successfully linked this chest to " + shop.getShopId());

                } else if (number == 5) {

                    if (!playerData.isConfigurationSection("Shop5")) {
                        playerData.createSection("Shop5");
                    } else {

                        player.sendMessage("Successfully set shop link " + number);

                    }
                    playerData.set("Shop5.Enabled", true);

                    playerData.set("Shop5.UUID", shopUUID);
                    playerData.set("Shop5.ShopX", shopX);
                    playerData.set("Shop5.ShopY", shopY);
                    playerData.set("Shop5.ShopZ", shopZ);
                    playerData.set("Shop5.ShopWorld", shopWorld);
                    playerData.set("Shop5.BlockX", blockX);
                    playerData.set("Shop5.BlockY", blockY);
                    playerData.set("Shop5.BlockZ", blockZ);
                    playerData.set("Shop5.BlockWorld", blockWorld);
                    playerData.save(f);

                    player.sendMessage(ChatColor.GREEN + "Successfully linked this chest to " + shop.getShopId());

                }
            }
            catch (IOException e){

                e.printStackTrace();

            }

        }

        return true;
    }

    public static void fileRead(Player player) {

        String playerUUID = player.getUniqueId().toString();

        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("DisplayShopAddon");
        File f = new File(plugin.getDataFolder() + File.separator + "PlayerDatabase" + File.separator + playerUUID + ".yml");

        //File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("DisplayShopAddon").getDataFolder() + File.separator + "PlayerDatabase");


        FileConfiguration playerData = YamlConfiguration.loadConfiguration(f);

        if (f.exists()) {

            String shopUUID = playerData.getString("Shop.UUID");
            Double shopX = playerData.getDouble("Shop.ShopX");
            Double shopY = playerData.getDouble("Shop.ShopY");
            Double shopZ = playerData.getDouble("Shop.ShopZ");
            Double blockX = playerData.getDouble("Shop.BlockX");
            Double blockY = playerData.getDouble("Shop.BlockY");
            Double blockZ = playerData.getDouble("Shop.BlockZ");

            player.sendMessage(ChatColor.GREEN + "Success!");
            player.sendMessage("Shop UUID: " + shopUUID);
            player.sendMessage("Shop XYZ: " + shopX + ", " + shopY + ", " + shopZ);
            player.sendMessage("Block XYZ: " + blockX + ", " + blockY + ", " + blockZ);

        } else {

            player.sendMessage("No info Found :( (Does the file Exist?)" + f.exists());

        }


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


}
