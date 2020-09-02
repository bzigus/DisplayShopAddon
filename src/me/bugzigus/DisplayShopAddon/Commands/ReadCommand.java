package me.bugzigus.DisplayShopAddon.Commands;

import me.bugzigus.DisplayShopAddon.DisplayShopAddon;
import me.bugzigus.DisplayShopAddon.ReadLocations;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class ReadCommand implements CommandExecutor {


    private final DisplayShopAddon plugin;

    public ReadCommand(DisplayShopAddon plugin) {

        this.plugin = plugin;

    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (sender instanceof Player) {



            Player player = (Player) sender;

            player.sendMessage(plugin.getReadLocation().getStorage().keySet().toString());
            player.sendMessage(String.valueOf(plugin.getReadLocation().getStorage().containsKey(player.getTargetBlock(null, 5).getLocation())));
            player.sendMessage(player.getTargetBlock(null, 5).getLocation().toString());


        }



        return true;
    }
}
