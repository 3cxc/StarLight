package com.github.starlight.chect.type;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Timer;
import java.util.TimerTask;

import static com.github.starlight.Util.Util.PlayerKB;
import static com.github.starlight.manager.ConfigManager.config;

/**
 * Fly检测(偏移值检测)模块
 * @author 3cxc
 * @Version 2.0
 * @since 3.6 版本加入
 */
public class FlyCheck implements Listener {
    private double x , new_x;
    private double y , new_y;
    private double z , new_z;
    private double toback;

    /**
     * 获取玩家坐标
     * @author 3cxc
     * @version 1.0
     * @since 3.5.0
     */
    public void getCE(PlayerMoveEvent event){//获取坐标
        x = event.getFrom().getX();
        y = event.getFrom().getY();
        z = event.getFrom().getZ();
        new_x = event.getTo().getX();
        new_y = event.getTo().getY();
        new_z = event.getTo().getZ();
    }

    /**
     * @param event
     * Fly检测(偏移值检测)<p>
     * delay为执行检测的延迟,默认为60ms<p>
     * toback安全距离(默认3.0,防止误判)<p>
     * actions偏移值(默认0.0005,预估可防止3.0005+ Fly)
     */
    @EventHandler
    public void FlyA(PlayerMoveEvent event){//Fly主检测A(基于坐标,偏移检测,延迟默认为60ms)
        long delay = config.getConfig().getInt("Chect.Fly.delay",60); //执行延迟,默认为60ms
        TimerTask timerTask = new TimerTask(){
            @Override
            public void run(){
                getCE(event);
                toback = 3.0; //这是一个安全距离,默认为3.0
                if (new_x - x >= toback + config.getConfig().getDouble("Chect.Fly.weight",0.0005)){//0.0005应该能检测到 3.0005+ fly
                    int tmp = 0;
                    for (int i = 0 ; i <= 5 ; i++){
                        if (new_y - y >= toback + config.getConfig().getDouble("Chect.Fly.weight",0.00005)){
                            ++tmp;
                        }
                    }
                    event.setCancelled(true);
                    PlayerKB(event,tmp,config.getConfig().getInt("Chect.Fly.VLMax",2),config.getConfig().getString("Chect.Fly.actions","kick"),"fly");
                }
            }
        };

        if (config.getConfig().getBoolean("Chect.Fly.active",true)){
            Timer timer = new Timer("FlyA");
            timer.schedule(timerTask,delay);//在指定的延迟(60ms)后执行检测
        }
    }

    /**
     * @param event
     * Fly检测(偏移值检测)<p>
     * delay为执行检测的延迟,默认为60ms<p>
     * toback安全距离(默认3.0,防止误判)<p>
     * actions偏移值(默认0.0005,预估可防止3.0005+ Fly)
     */
    @EventHandler
    public void FlyB(PlayerMoveEvent event){//Fly主检测B(基于坐标,偏移检测,延迟默认为60ms)
        long delay = config.getConfig().getInt("Chect.Fly.delay",60); //执行延迟,默认为60ms
        TimerTask timerTask = new TimerTask(){
            @Override
            public void run() {
                getCE(event);
                toback = 3.0; //这是一个安全距离,默认为3.0
                if (new_x - x >= toback + config.getConfig().getDouble("Chect.Fly.weight",0.0005)){//0.0005应该能检测到 3.0005+ fly
                    int tmp = 0;
                    for (int i = 0 ; i <= 5 ; i++){
                        if (new_z - z >= toback + config.getConfig().getDouble("Chect.Fly.weight",0.0005)){
                            ++tmp;
                        }
                    }
                    event.setCancelled(true);
                    PlayerKB(event,tmp,config.getConfig().getInt("Chect.Fly.VLMax",2),config.getConfig().getString("Chect.Fly.actions","kick"),"speed");
                }
            }
        };

        if (config.getConfig().getBoolean("Chect.Fly.active",true)){
            Timer timer = new Timer("FlyB");
            timer.schedule(timerTask,delay);//在指定的延迟(60ms)后执行检测
        }
    }
}
