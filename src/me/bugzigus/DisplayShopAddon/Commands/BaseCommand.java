package me.bugzigus.DisplayShopAddon.Commands;

import me.bugzigus.DisplayShopAddon.DisplayShopAddon;
import me.bugzigus.DisplayShopAddon.ReadLang;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class BaseCommand implements CommandExecutor {

    String term;
    LinkCommand linkCommand = new LinkCommand();
    UnlinkCommand unlinkCommand = new UnlinkCommand();
    ReadLang rl = new ReadLang();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (strings.length > 0) {

            term = strings[0];
            if (strings[0].equals("help")) {
                help(sender);

            } else if (strings[0].equals("link")) {
                linkCommand.link(sender, strings);

            } else if (strings[0].equals("unlink")) {
                unlinkCommand.unlink(sender, strings);

            } else {

                sender.sendMessage(rl.readFiles("Commands.BaseCommand.incorrectUsage1") + term);

            }

        } else {

            sender.sendMessage(rl.readFiles("Commands.BaseCommand.incorrectUsage"));

        }

        return true;
    }



    public void help (CommandSender sender) {
        Player player = (Player) sender;

        Plugin plugin = DisplayShopAddon.pluginInstance();

        if (plugin.getConfig().getBoolean("Help.Enabled")) {
            player.sendMessage(ChatColor.DARK_GRAY + "-----------------------------------");
            player.sendMessage(ChatColor.GRAY + "-" + ChatColor.BLUE + " /dsa link (1-" + plugin.getConfig().getInt("Shops.MaxAmount") + ") (shop-ID)");
            player.sendMessage(ChatColor.GRAY + "-" + ChatColor.BLUE + " /dsa unlink (1-" + plugin.getConfig().getInt("Shops.MaxAmount") + ")");
            player.sendMessage(ChatColor.GRAY + "-" + ChatColor.BLUE + " /dsa help - Displays this info");
            player.sendMessage(ChatColor.DARK_GRAY + "-----------------------------------");

        }
    }


}
