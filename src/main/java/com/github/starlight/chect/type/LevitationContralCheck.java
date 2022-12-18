package com.github.starlight.chect.type;

import com.github.starlight.StarLightAPI;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.scheduler.BukkitRunnable;

@Deprecated
public class LevitationContralCheck implements Listener {
    @EventHandler
    public void LCA(InventoryOpenEvent event){//背包行走判定
        Location OldLocation = event.getPlayer().getLocation();
        new BukkitRunnable(){//异步执行
            @Override
            public void run(){
                if (event.getPlayer().getLocation() != OldLocation & !event.isCancelled()){//检查坐标
                    event.setCancelled(true);//拉回
                }
            }
        }.runTaskTimer(StarLightAPI.INSTANCE.getPlugin(),1000,1000);//异步执行,70ms检查一次
    }
}
