package me.bugzigus.DisplayShopAddon;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class readLocations {

    Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("DisplayShopAddon");
    File directory = new File(plugin.getDataFolder() + File.separator + "PlayerDatabase");
    File[] files = directory.listFiles();

}
