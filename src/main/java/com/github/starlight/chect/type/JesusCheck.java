package com.github.starlight.chect.type;

import com.github.starlight.StarLightAPI;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

@Deprecated
public class JesusCheck implements Listener {

    @EventHandler
    public void JesusA(PlayerMoveEvent event){//拦截Jesus(水上行走)
        new BukkitRunnable(){
            @Override
            public void run() {//异步执行
                Player player = event.getPlayer();
                Location location = event.getFrom();
                if (player.getWorld().getBlockAt(location.getBlockX(),location.getBlockY() - 1,location.getBlockZ()).getType() == Material.WATER){//判断是否在水上行走
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException ignored) {
                        Location location1 = event.getTo();
                        if (player.getWorld().getBlockAt(location1.getBlockX(),location1.getBlockY() - 1,location1.getBlockZ()).getType() == Material.WATER){
                            event.setCancelled(true);//拉回
                        }
                    }
                }
            }
        }.runTaskAsynchronously(StarLightAPI.INSTANCE.getPlugin());
    }
}
