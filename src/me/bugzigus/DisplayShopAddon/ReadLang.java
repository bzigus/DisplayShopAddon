package me.bugzigus.DisplayShopAddon;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReadLang {
    private Map<String, String> langMap = new HashMap<String, String>();
    Plugin plugin = DisplayShopAddon.pluginInstance();

    File directory = new File(plugin.getDataFolder().getPath());
    File f = new File(directory + File.separator +"lang.yml");

    String[] langOptions;

    public void saveDefault(){

        if (!f.exists()) {

            FileConfiguration langFile = YamlConfiguration.loadConfiguration(f);

            plugin.saveResource("lang.yml", false);

        }

    }

    public String readFiles(String name) {

        FileConfiguration langFile = YamlConfiguration.loadConfiguration(f);

        String lang = langFile.getString(name);

        lang = ChatColor.translateAlternateColorCodes('&', lang);

        return lang;
    }

}
