package me.bugzigus.DisplayShopAddon;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import xzot1k.plugins.ds.DisplayShops;
import xzot1k.plugins.ds.api.objects.Shop;

public class HopperListener implements Listener {

    double hopperLocationX = 0;
    double hopperLocationY = 0;
    double hopperLocationZ = 0;
    World hopperLocationWorld = null;

//    @EventHandler
//    public void HopperPickUpEvent(InventoryPickupItemEvent event) {
//        //Get location of the hopper
//        hopperLocationX = event.getInventory().getLocation().getX();
//        hopperLocationY = event.getInventory().getLocation().getY();
//        hopperLocationZ = event.getInventory().getLocation().getZ();
//        hopperLocationWorld = event.getInventory().getLocation().getWorld();
//
//        //Make it a Location
//        Location hopperLocation = new Location(hopperLocationWorld, hopperLocationX, hopperLocationY, hopperLocationZ);
//
//        //Find if it is too close to the chest (meaning it within a 1 block radius)
//        if (DisplayShops.getPluginInstance().getManager().isTooClose(hopperLocation)) {
//
//            Shop hopperShop = DisplayShops.getPluginInstance().getManager().getShop(hopperLocation);
//
//
//        }
//
//    }

}
