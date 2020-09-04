package me.bugzigus.DisplayShopAddon.Commands;

import me.bugzigus.DisplayShopAddon.DisplayShopAddon;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class HelpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        Player player = (Player) sender;

        Plugin plugin = DisplayShopAddon.pluginInstance();

        if (plugin.getConfig().getBoolean("Help.Enabled")) {
            player.sendMessage(ChatColor.DARK_GRAY + "-----------------------------------");
            player.sendMessage(ChatColor.GRAY + "-" + ChatColor.BLUE + " /linkshop (1-" + plugin.getConfig().getInt("Shops.MaxAmount") + ") (shop-ID)");
            player.sendMessage(ChatColor.GRAY + "-" + ChatColor.BLUE + " /unlinkshop (1-" + plugin.getConfig().getInt("Shops.MaxAmount") + ")");
            player.sendMessage(ChatColor.DARK_GRAY + "-----------------------------------");

        }
        return true;
    }
}
