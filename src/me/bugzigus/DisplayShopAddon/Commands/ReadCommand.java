package me.bugzigus.DisplayShopAddon.Commands;

import me.bugzigus.DisplayShopAddon.ReadLocations;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class ReadCommand implements CommandExecutor {


    HashMap<UUID, Location> hashMap = new HashMap<UUID, Location>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (sender instanceof Player) {

            ReadLocations rl = new ReadLocations();

            Player player = (Player) sender;

            player.sendMessage(rl.getStorage().keySet().toString());
            player.sendMessage(String.valueOf(rl.getStorage().get(player.getTargetBlock(null, 5).getLocation())));
            player.sendMessage(player.getTargetBlock(null, 5).getLocation().toString());


        }

        return true;
    }
}
