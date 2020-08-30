package me.bugzigus.DisplayShopAddon.Commands;

import me.bugzigus.DisplayShopAddon.DisplayShopAddon;
import me.bugzigus.DisplayShopAddon.YmlFIle;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
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
    HashMap<UUID, Shop> shopMaps = new HashMap<UUID, Shop>();

    Shop selectedShop;

    UUID key;

    Block block = null;
    int x, y, z;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (!(strings.length == 0)) {

            if (sender instanceof Player) {

                Player player = (Player) sender;

                if (YmlFIle.isUUID(strings[0])) {
                    UUID key = UUID.fromString(strings[0]);


                    block = player.getTargetBlock(null, 5);

                    shopMaps = DisplayShops.getPluginInstance().getManager().getShopMap();

                    if (shopMaps.containsKey(key)) {
                        selectedShop = shopMaps.get(key);

                        YmlFIle.fileWrite(player, block, selectedShop);
                    } else {

                        player.sendMessage(ChatColor.DARK_RED + "That ID is not in the system");

                    }

                } else {

                    player.sendMessage(ChatColor.DARK_RED + "That is not a correct UUID");

                }

            }
        } else {

            sender.sendMessage(ChatColor.DARK_RED + "You did not add a shop ID to link too. Do /displayshops copy");

        }
        return true;
    }
}
