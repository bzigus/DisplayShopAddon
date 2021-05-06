package me.bugzigus.DisplayShopAddon.Commands;

import me.bugzigus.DisplayShopAddon.ReadLang;
import me.bugzigus.DisplayShopAddon.YmlFIle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnlinkCommand {


    ReadLang rl = new ReadLang();

    public boolean unlink(CommandSender sender, String[] strings) {

        Player player = (Player) sender;

        if (strings.length == 2) {

            if (YmlFIle.isNumeric(strings[1])) {

                int number = Integer.parseInt(strings[1]);

                YmlFIle.fileClear(player, number);

                player.sendMessage(rl.readFiles("Commands.UnlinkShop.success"));

            }

            else {

                player.sendMessage(rl.readFiles("Commands.UnlinkShop.incorrectUsage"));

            }

        } else {

            player.sendMessage(rl.readFiles("Commands.UnlinkShop.incorrectUsage"));

        }
        return true;
    }
}
