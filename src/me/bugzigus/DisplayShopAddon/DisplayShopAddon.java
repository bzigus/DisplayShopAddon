package me.bugzigus.DisplayShopAddon;

import me.bugzigus.DisplayShopAddon.Commands.LinkCommand;
import me.bugzigus.DisplayShopAddon.Commands.ReadCommand;
import me.bugzigus.DisplayShopAddon.Commands.RemoveCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.Main;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

//TODO Make Hoppers Work with Display Shop

public class DisplayShopAddon extends JavaPlugin {

    public FileConfiguration myFileConfig = null;
    public File locations;

    private ReadLocations readLocations;

    private static DisplayShopAddon pluginInstance;

    public static Plugin pluginInstance() {
        return pluginInstance;
    }

    @Override
    public final void onEnable() {

        DisplayShopAddon.pluginInstance = this;

        //Bstats
        int pluginId = 8750;
        Metrics metrics = new Metrics(this, pluginId);

        //Config Setup
        FileConfiguration config = this.getConfig();
        this.saveDefaultConfig();

        config.addDefault("youAreAwesome", true);
        config.options().copyDefaults(true);
        saveConfig();

        ReadLang rl = new ReadLang();

        rl.saveDefault();

        locationReader();

        //Events
        getServer().getPluginManager().registerEvents(new ChestListener(this), this);

        //Commands
        this.getCommand("linkshop").setExecutor(new LinkCommand());
        this.getCommand("unlinkshop").setExecutor(new RemoveCommand());
        this.getCommand("readconfig").setExecutor(new ReadCommand(this));

    }

    @Override
    public void onDisable() {
    }

    public void locationReader () {

        readLocations = new ReadLocations();
        readLocations.readFiles();

    }

    public ReadLocations getReadLocation() {
        return readLocations;
    }

}
