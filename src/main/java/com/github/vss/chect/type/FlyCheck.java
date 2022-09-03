package com.github.vss.chect.type;

import com.github.vss.Util.Utils;
import com.github.vss.VSS;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;
import static com.github.vss.manager.ConfigManager.config;

public class FlyCheck implements Listener {
    double x , new_x;
    double y , new_y;
    double z , new_z;

    public void getCE(PlayerMoveEvent event){//获取坐标
        x = event.getFrom().getX();
        y = event.getFrom().getY();
        z = event.getFrom().getZ();
        new_x = event.getTo().getX();
        new_y = event.getTo().getY();
        new_z = event.getTo().getZ();
    }

    @EventHandler
    public void FlyVIO(PlayerMoveEvent event){//暴力飞检测(非偏移值检测)
        Player player = event.getPlayer();
        getCE(event);
        long delay = config.getConfig().getInt("Chect.Fly.delay",60);//检测延迟,默认60ms
        if (config.getConfig().getBoolean("Chect.Fly.active",true)){
            new BukkitRunnable(){
                public void run(){
                    int VL = 0;
                    for (int i = 0 ; i <= 20 ;i++){
                        if (new_x - x >= 0.05 && new_y - y <= 1.5 && y >= 0.0005){ //0.0005应该能检测到 1.01+fly
                            ++VL;
                        }
                        if (new_z - z >= 0.05 && new_y - y <= 1.5 && y >= 0.0005){
                            ++VL;
                        }
                    }
                    if (VL >= config.getConfig().getInt("Chect.Fly.VLMax",13) & !player.isOp()){//检测VL值是否违规
                        if (Objects.equals(config.getConfig().getString("Chect.Fly.actions","kick"), "kick")){
                            Utils.vss_Kickplayer(player,"Fly");
                            Utils.AllAdminWarning(player,"fly");
                        }else {
                            Utils.vss_Banplayer(player,"Fly");
                            Utils.AllAdminWarning(player,"fly");
                        }
                    }
                }
            }.runTaskLaterAsynchronously(VSS.getPlugin(VSS.class), delay); //在指定的延迟(60ms)后异步执行检测
        }
    }
}
