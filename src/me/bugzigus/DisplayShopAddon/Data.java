package me.bugzigus.DisplayShopAddon;

import me.vagdedes.mysql.database.SQL;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.Hash;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import xzot1k.plugins.ds.api.objects.Shop;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Data implements Serializable {

    private static transient final long serialVersionUID = -1681012206529286330L;

    public final HashMap<UUID, Location> blockSnapShot;

    public boolean saveData(String filePath) {
        try {

            FileOutputStream fileOut = new FileOutputStream(filePath);
            GZIPOutputStream gzOut = new GZIPOutputStream(fileOut);
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(gzOut);
            out.close();
            return true;

        } catch (IOException e) {

            e.printStackTrace();
            return false;
        }
    }


    public static Data loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            Data data = (Data) in.readObject();
//            in.close();
            return data;
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public static void getBlock (Block block, Shop shop, UUID UUID) {

        Location blockLocation;

        HashMap<UUID, Location> blockSnapShot = new HashMap<UUID, Location>();
        blockSnapShot.put(UUID, block.getLocation());

        new Data(blockSnapShot).saveData("Tutorial.data");
        Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");


    }

    public Data(HashMap<UUID, Location> blockSnapShot) {

        this.blockSnapShot = blockSnapShot;
    }

    public Data(Data loadedData) {
        this.blockSnapShot = loadedData.blockSnapShot;
    }

}
