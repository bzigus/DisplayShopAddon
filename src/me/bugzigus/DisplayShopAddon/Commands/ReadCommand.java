package me.bugzigus.DisplayShopAddon.Commands;

import me.bugzigus.DisplayShopAddon.YmlFIle;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class ReadCommand implements CommandExecutor {


    HashMap<UUID, Location> hashMap = new HashMap<UUID,Location>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {

            Player player = (Player) commandSender;

            YmlFIle.fileRead(player);

        }

        return true;
    }
}
