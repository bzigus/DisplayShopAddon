package me.bugzigus.DisplayShopAddon;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xzot1k.plugins.ds.DisplayShops;
import xzot1k.plugins.ds.api.objects.Shop;

import java.util.HashMap;
import java.util.UUID;

public class LinkCommand implements CommandExecutor {

    //Hashmap for finding
    HashMap<UUID, Shop> shops = new HashMap<UUID, Shop>();

    Shop selectedShop;

    Block block = null;
    int x, y, z;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (!(strings.length == 0)) {

            if (sender instanceof Player) {

                Player player = (Player) sender;

                UUID UUID = player.getUniqueId();
                block = player.getTargetBlock(null, 5);

                shops = DisplayShops.getPluginInstance().getManager().getShopMap();

                selectedShop = shops.get(strings[0]);

                Data.getBlock(block, selectedShop, UUID);

            }
        } else {

            sender.sendMessage(ChatColor.DARK_RED + "You did not add a shop ID to link too. Do /displayshops copy");

        }
        return true;
    }
}
