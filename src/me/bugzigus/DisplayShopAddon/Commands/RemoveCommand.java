package me.bugzigus.DisplayShopAddon.Commands;

import me.bugzigus.DisplayShopAddon.YmlFIle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        Player player = (Player) sender;

        YmlFIle.fileClear(player);

        return true;
    }
}
