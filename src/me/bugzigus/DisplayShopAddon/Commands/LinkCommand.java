package me.bugzigus.DisplayShopAddon.Commands;

import me.bugzigus.DisplayShopAddon.YmlFIle;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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

        //Correct Length
        if (strings.length == 2) {

            //Make sure the sender is a player
            if (sender instanceof Player) {

                Player player = (Player) sender;

                //Check if the UUID given really is a UUID
                if (YmlFIle.isUUID(strings[1])) {
                    UUID key = UUID.fromString(strings[1]);

                    //Check if number really is a number
                    if (YmlFIle.isNumeric(strings[0])) {

                        int number = Integer.parseInt(strings[0]);


                        block = player.getTargetBlock(null, 5);

                        Material material = Material.CHEST;

                        //Make sure the block player is looking at is a chest
                        if (block.getType() == material) {

                            shopMaps = DisplayShops.getPluginInstance().getManager().getShopMap();

                            //Get the shop for ID of the shop
                            if (shopMaps.containsKey(key)) {
                                selectedShop = shopMaps.get(key);

                                //Write it to the .yml file
                                YmlFIle.fileWrite(player, block, selectedShop, number);

                                //All of messages you could receive

                            } else {

                                player.sendMessage(ChatColor.DARK_RED + "That ID is not in the system");

                            }
                        } else {

                            player.sendMessage(ChatColor.DARK_RED + "The block you are looking at is not a chest!");

                        }

                    } else {

                        player.sendMessage(ChatColor.DARK_RED + "Are you sure you add a number");
                        sender.sendMessage(ChatColor.DARK_RED + "Command Usage: /linkshop (1-5) (ShopID)");

                    }

                } else {

                    player.sendMessage(ChatColor.DARK_RED + "That is not a correct UUID");
                }

            }
        } else {

            sender.sendMessage(ChatColor.DARK_RED + "You did not add a shop ID to link too. Do /displayshops copy");
            sender.sendMessage(ChatColor.DARK_RED + "Command Usage: /linkshop (1-5) (ShopID)");

        }
        return true;
    }
}

