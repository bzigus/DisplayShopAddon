package me.bugzigus.DisplayShopAddon;

import me.bugzigus.DisplayShopAddon.Commands.LinkCommand;
import me.bugzigus.DisplayShopAddon.Commands.ReadCommand;
import me.bugzigus.DisplayShopAddon.Commands.RemoveCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

//TODO Make Hoppers Work with Display Shop

public class DisplayShopAddon extends JavaPlugin {

    public FileConfiguration myFileConfig = null;
    public File locations;


    @Override
    public final void onEnable() {

        ReadLocations rl = new ReadLocations();

        rl.readFiles();
        //Config Setup
        FileConfiguration config = this.getConfig();
        this.saveDefaultConfig();

        config.addDefault("youAreAwesome", true);
        config.options().copyDefaults(true);
        saveConfig();
        //Events
        getServer().getPluginManager().registerEvents(new ChestListener(), this);

        //Commands
        this.getCommand("linkshop").setExecutor(new LinkCommand());
        this.getCommand("unlinkshop").setExecutor(new RemoveCommand());
        this.getCommand("readconfig").setExecutor(new ReadCommand());

    }

    @Override
    public void onDisable() {
    }


}
