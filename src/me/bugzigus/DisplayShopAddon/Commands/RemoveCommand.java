package me.bugzigus.DisplayShopAddon.Commands;

import me.bugzigus.DisplayShopAddon.ReadLang;
import me.bugzigus.DisplayShopAddon.YmlFIle;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveCommand implements CommandExecutor {


    ReadLang rl = new ReadLang();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        Player player = (Player) sender;

        if (strings.length == 1) {

            if (YmlFIle.isNumeric(strings[0])) {

                int number = Integer.parseInt(strings[0]);

                YmlFIle.fileClear(player, number);

                player.sendMessage(rl.readFiles("UnlinkShop.success"));

            }

            else {

                player.sendMessage(rl.readFiles("UnlinkShop.incorrectUsage"));

            }

        } else {

            player.sendMessage(rl.readFiles("UnlinkShop.incorrectUsage"));

        }
        return true;
    }
}
