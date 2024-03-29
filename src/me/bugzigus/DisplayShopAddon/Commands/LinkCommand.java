package me.bugzigus.DisplayShopAddon.Commands;

import me.bugzigus.DisplayShopAddon.DisplayShopAddon;
import me.bugzigus.DisplayShopAddon.ReadLang;
import me.bugzigus.DisplayShopAddon.YmlFIle;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import xzot1k.plugins.ds.DisplayShops;
import xzot1k.plugins.ds.api.objects.Shop;

import java.util.HashMap;
import java.util.UUID;

public class LinkCommand {

    //Hashmap for finding
    HashMap<UUID, Shop> shopMaps = new HashMap<UUID, Shop>();

    Shop selectedShop;

    UUID key;

    ReadLang rl = new ReadLang();

    Block block = null;
    int x, y, z;

    public boolean link(CommandSender sender, String[] strings) {

        Plugin plugin = DisplayShopAddon.pluginInstance();

        YmlFIle ymlFIle = new YmlFIle((DisplayShopAddon) plugin);

        //Correct Length
        if (strings.length == 3) {

            //Make sure the sender is a player
            if (sender instanceof Player) {

                Player player = (Player) sender;

                //Check if the UUID given really is a UUID
                if (YmlFIle.isUUID(strings[2])) {
                    UUID key = UUID.fromString(strings[2]);

                    //Check if number really is a number
                    if (YmlFIle.isNumeric(strings[1])) {

                        int number = Integer.parseInt(strings[1]);

                        if (plugin.getConfig().getInt("Shops.MaxAmount") >= number) {

                            block = player.getTargetBlock(null, 5);

                            Material materialChest = Material.CHEST;
                            Material materialHopper = Material.HOPPER;

                            //Make sure the block player is looking at is a chest
                            if (materialChest == block.getType()) {

                                shopMaps = DisplayShops.getPluginInstance().getManager().getShopMap();

                                //Get the shop for ID of the shop
                                if (shopMaps.containsKey(key)) {
                                    selectedShop = shopMaps.get(key);

                                    //Write it to the .yml file
                                    ymlFIle.fileWrite(player, block, selectedShop, number);

                                    player.sendMessage(rl.readFiles("Commands.LinkShop.success"));

                                    //All of messages you could receive

                                } else {

                                    player.sendMessage(rl.readFiles("Commands.LinkShop.incorrectID"));

                                }
                            } else if (materialHopper == block.getType()) {

                                //Get the shop for ID of the shop
                                if (shopMaps.containsKey(key)) {
                                    selectedShop = shopMaps.get(key);

                                    //Write it to the .yml file
                                    ymlFIle.fileWrite(player, block, selectedShop, number);

                                    player.sendMessage(rl.readFiles("Commands.LinkShop.success"));

                                    //All of messages you could receive

                                } else {

                                    player.sendMessage(rl.readFiles("Commands.LinkShop.incorrectID"));

                                }



                            } else {

                                player.sendMessage(rl.readFiles("Commands.LinkShop.notChest"));

                            }
                        } else {

                            player.sendMessage(rl.readFiles("Commands.LinkShop.incorrectUsage"));

                        }

                    } else {

                        player.sendMessage(rl.readFiles("Commands.LinkShop.incorrectUsage"));

                    }

                } else {

                    player.sendMessage(rl.readFiles("Commands.LinkShop.incorrectUsage"));
                }

            }
        } else {

            sender.sendMessage(rl.readFiles("Commands.LinkShop.incorrectUsage"));

        }
        return true;
    }
}

