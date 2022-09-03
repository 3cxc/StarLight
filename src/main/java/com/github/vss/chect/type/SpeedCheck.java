package com.github.vss.chect.type;

import com.github.vss.Util.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Timer;
import java.util.TimerTask;

import static com.github.vss.manager.ConfigManager.config;

public class SpeedCheck implements Listener {
    double x , new_x;
    double y , new_y;
    double z , new_z;
    double toback;

    public void getCE(PlayerMoveEvent event){//获取坐标
        x = event.getFrom().getX();
        y = event.getFrom().getY();
        z = event.getFrom().getZ();
        new_x = event.getTo().getX();
        new_y = event.getTo().getY();
        new_z = event.getTo().getZ();
    }

    public void PlayerKB(PlayerMoveEvent event,int VL,int VLMax,String actions, String reason){
        if (VL >= VLMax){
            Player player = event.getPlayer();
            if (!event.getPlayer().isOp()){
                if (actions.equals("kick")){
                    Utils.vss_Kickplayer(player,reason);
                    Utils.AllAdminWarning(player,reason);
                }else if (actions.equals("ban")){
                    Utils.vss_Banplayer(player,reason);
                }else {
                    Utils.AllAdminWarning(player,reason);
                }
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void SpeedVIO(PlayerMoveEvent event){//对于暴力Speed的检测
        getCE(event);
        int tmp = 0;
        for (int i = 0 ; i < 5 ; i++){
            if (new_x - x > 3.0 || new_z - z > 3.0){
                event.setCancelled(true);
                tmp ++;
            }
        }
        if (tmp >= 2 && !event.getPlayer().isOp()){
            Utils.vss_Kickplayer(event.getPlayer(),"speed");
        }
    }

    /**
     * Speed检测(偏移值检测)
     * delay为执行检测的延迟,默认为70ms
     * toback安全距离(默认3.0,防止误判)
     * actions偏移值(默认0.3,预估可防止3.03+ Speed)
     */
    @EventHandler
    public void SpeedA(PlayerMoveEvent event){//Speed主检测A(基于坐标,偏移检测,延迟默认为70ms)
        long delay = config.getConfig().getInt("Chect.Speed.delay",70); //执行延迟,默认为70ms
        TimerTask timerTask = new TimerTask(){
            @Override
            public void run(){
                getCE(event);
                toback = 3.0; //这是一个安全距离,默认为3.0
                if (new_x - x >= toback + config.getConfig().getDouble("Chect.Speed.weight",0.3)){
                    int tmp = 0;
                    for (int i = 0 ; i <= 5 ; i++){
                        if (new_y - y >= toback + config.getConfig().getDouble("Chect.Speed.weight",0.3)){
                            ++tmp;
                        }
                    }
                    PlayerKB(event,tmp,config.getConfig().getInt("Chect.Speed.VLMax",2),config.getConfig().getString("Chect.Speed.actions","kick"),"speed");
                }
            }
        };

        if (config.getConfig().getBoolean("Chect.Speed.active",true)){
            Timer timer = new Timer("SpeedA");
            timer.schedule(timerTask,delay);//在指定的延迟(70ms)后执行检测
        }
    }

    @EventHandler
    public void SpeedB(PlayerMoveEvent event){//Speed主检测A(基于坐标,偏移检测,延迟默认为70ms)
        long delay = config.getConfig().getInt("Chect.Speed.delay",70); //执行延迟,默认为70ms
        TimerTask timerTask = new TimerTask(){
            @Override
            public void run() {
                getCE(event);
                toback = 3.0; //这是一个安全距离,默认为3.0
                if (new_x - x >= toback + config.getConfig().getDouble("Chect.Speed.weight",0.3)){
                    int tmp = 0;
                    for (int i = 0 ; i <= 10 ; i++){
                        if (new_z - z >= toback + config.getConfig().getDouble("Chect.Speed.weight",0.3)){
                            ++tmp;
                        }
                    }
                    PlayerKB(event,tmp,config.getConfig().getInt("Chect.Speed.VLMax",2),config.getConfig().getString("Chect.Speed.actions","kick"),"speed");
                }
            }
        };

        if (config.getConfig().getBoolean("Chect.Speed.active",true)){
            Timer timer = new Timer("SpeedB");
            timer.schedule(timerTask,delay);//在指定的延迟(70ms)后执行检测
        }
    }
}
