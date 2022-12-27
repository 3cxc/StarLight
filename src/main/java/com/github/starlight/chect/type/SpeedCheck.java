package com.github.starlight.chect.type;

import com.github.starlight.StarLightAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

import static com.github.starlight.Util.Util.PlayerKB;
import static com.github.starlight.manager.ConfigManager.config;

/**
 * Speed检测(偏移值检测)模块
 * @author 3cxc
 * @Version 3.0
 * @since 3.6 版本加入
 */
public class SpeedCheck implements Listener {
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
     * Speed检测(偏移值检测)<p>
     * delay为执行检测的延迟,默认为70ms<p>
     * toback安全距离(默认3.0,防止误判)<p>
     * actions偏移值(默认0.3,预估可防止3.03+ Speed)
     * @author 3cxc
     * @Version 2.0
     * @since 3.6 版本加入
     */
    @EventHandler
    public void SpeedA(PlayerMoveEvent event){//Speed主检测A(基于坐标,偏移检测,延迟默认为70ms)
        long delay = config.getConfig().getInt("Chect.Speed.delay",70); //执行延迟,默认为70ms
        TimerTask timerTask = new TimerTask(){
            @Override
            public void run(){
                getCE(event);
                toback = 3.0; //这是一个安全距离,默认为3.0
                if (new_x - x >= toback + config.getConfig().getDouble("Chect.Speed.weight",0.03)){
                    int tmp = 0;
                    for (int i = 0 ; i <= 5 ; i++){
                        if (new_y - y >= toback + config.getConfig().getDouble("Chect.Speed.weight",0.03)){
                            ++tmp;
                        }
                    }
                    event.setCancelled(true);
                    PlayerKB(event,tmp,config.getConfig().getInt("Chect.Speed.VLMax",2),config.getConfig().getString("Chect.Speed.actions","kick"),"speed");
                }
            }
        };

        if (config.getConfig().getBoolean("Chect.Speed.active",true)){
            Timer timer = new Timer("SpeedA");
            timer.schedule(timerTask,delay);//在指定的延迟(70ms)后执行检测
        }
    }

    /**
     * @param event
     * Speed检测(偏移值检测)<p>
     * delay为执行检测的延迟,默认为70ms<p>
     * toback安全距离(默认3.0,防止误判)<p>
     * actions偏移值(默认0.3,预估可防止3.03+ Speed)
     * @author 3cxc
     * @Version 2.0
     * @since 3.6 版本加入
     */
    @EventHandler
    public void SpeedB(PlayerMoveEvent event){//Speed主检测B(基于坐标,偏移检测,延迟默认为70ms)
        long delay = config.getConfig().getInt("Chect.Speed.delay",70); //执行延迟,默认为70ms
        TimerTask timerTask = new TimerTask(){
            @Override
            public void run() {
                getCE(event);
                toback = 3.0; //这是一个安全距离,默认为3.0
                if (new_x - x >= toback + config.getConfig().getDouble("Chect.Speed.weight",0.03)){
                    int tmp = 0;
                    for (int i = 0 ; i <= 5 ; i++){
                        if (new_z - z >= toback + config.getConfig().getDouble("Chect.Speed.weight",0.03)){
                            ++tmp;
                        }
                    }
                    event.setCancelled(true);
                    PlayerKB(event,tmp,config.getConfig().getInt("Chect.Speed.VLMax",2),config.getConfig().getString("Chect.Speed.actions","kick"),"speed");
                }
            }
        };

        if (config.getConfig().getBoolean("Chect.Speed.active",true)){
            Timer timer = new Timer("SpeedB");
            timer.schedule(timerTask,delay);//在指定的延迟(70ms)后执行检测
        }
    }

    public Map<String, Integer> map = new HashMap<>();//监视名单

    /**
     * @param event
     * Speed检测(偏移值检测)<p>
     * delay为执行检测的延迟,默认为70ms<p>
     * toback安全距离(默认3.0,防止误判)<p>
     * actions偏移值(默认0.3,预估可防止3.03+ Speed)
     * @author 3cxc
     * @Version Beta-1.0
     * @since 3.7 版本加入
     * <p><font color="red" >警告，这个检测仍在测试，可能不稳定</font>
     */
    @EventHandler
    public void SpeedC(PlayerMoveEvent event){//Speed主检测C(基于坐标,偏移检测,延迟默认为70ms)
        Player player = event.getPlayer();
        long delay = config.getConfig().getInt("Chect.Speed.delay",70); //执行延迟,默认为70ms
        new BukkitRunnable(){
            public void run(){
                getCE(event);
                toback = 3.0; //这是一个安全距离,默认为3.0
                if (!player.hasPermission("starlight.speed.bypass")){
                    if (new_x != x){
                        if (new_x - x > toback){
                            double tmp = new_x - x;
                            if (tmp >= config.getConfig().getDouble("Chect.Speed.weight",0.03)){
                                if (new_z != z){
                                    if (new_z - z <= 0.7){//第一种,X移动，Z不超过0.7格
                                        double tmp1 = tmp - config.getConfig().getDouble("Chect.Speed.weight",0.03);
                                        if (tmp1 >= 1){//大于等于1
                                            event.setCancelled(true);
                                            if (map.containsKey(player.getName())){
                                                map.put(player.getName(),map.get(player.getName()) + 1);
                                            }else {
                                                map.put(player.getName(),1);
                                            }
                                        } else if (tmp >= 0.07) {//如果大于0.07但小于1
                                            event.setCancelled(true);
                                            if (map.containsKey(player.getName())){
                                                map.put(player.getName(),map.get(player.getName()) + 1);
                                            }else {
                                                map.put(player.getName(),1);
                                            }
                                        }else if (tmp >= 0.03){//如果大于0.03但小于0.07
                                            event.setCancelled(true);
                                            if (map.containsKey(player.getName())){
                                                map.put(player.getName(),map.get(player.getName()) + 1);
                                            }else {
                                                map.put(player.getName(),1);
                                            }
                                        }else {//如果低于0.03
                                            event.setCancelled(true);
                                            if (map.containsKey(player.getName())){
                                                map.put(player.getName(),map.get(player.getName()) + 1);
                                            }else {
                                                map.put(player.getName(),1);
                                            }
                                            PlayerKB(event,map.get(player.getName()),2,"cancel","speed");
                                            map.remove(player.getName(),map.get(player.getName()));
                                        }
                                    }else if (new_z - z <= 1.03){
                                        event.setCancelled(true);
                                        if (map.containsKey(player.getName())){
                                            map.put(player.getName(),map.get(player.getName()) + 1);
                                        }else {
                                            map.put(player.getName(),1);
                                        }
                                    }else {
                                        event.setCancelled(true);
                                        if (map.containsKey(player.getName())){
                                            map.put(player.getName(),map.get(player.getName()) + 1);
                                        }else {
                                            map.put(player.getName(),1);
                                        }
                                    }
                                    PlayerKB(event,map.get(player.getName()),2,"cancel","speed");
                                    map.remove(player.getName(),map.get(player.getName()));
                                }//X移动且Z移动
                                event.setCancelled(true);
                                if (map.containsKey(player.getName())){
                                    if (map.get(player.getName()) >= 3){
                                        PlayerKB(event, map.get(player.getName()), 3,"cancel","speed");
                                        map.remove(player.getName(),map.get(player.getName()));
                                    }else {
                                        map.put(player.getName(), map.get(player.getName()) + 1);
                                    }
                                }

                            }else if (tmp > toback){
                                if (map.containsKey(player.getName())){
                                    if (map.get(player.getName()) >= 3){
                                        event.setCancelled(true);
                                        PlayerKB(event, map.get(player.getName()), 3,"cancel","speed");
                                        map.remove(player.getName(),map.get(player.getName()));
                                    }else {
                                        map.put(player.getName(), map.get(player.getName()) + 1);
                                        event.setCancelled(true);
                                    }
                                }
                            }
                        }
                    }//X变化时
                    if (new_z != z){
                        if (new_z - z > toback){
                            double tmp = new_z - z;
                            if (tmp >= config.getConfig().getDouble("Chect.Speed.weight",0.03)){
                                if (new_x != x){
                                    if (new_x - x <= 0.7){//第一种,Z移动，X不超过0.7格
                                        double tmp1 = tmp - config.getConfig().getDouble("Chect.Speed.weight",0.03);
                                        if (tmp1 >= 1){//大于等于1
                                            event.setCancelled(true);
                                            if (map.containsKey(player.getName())){
                                                map.put(player.getName(),map.get(player.getName()) + 1);
                                            }else {
                                                map.put(player.getName(),1);
                                            }
                                        } else if (tmp >= 0.07) {//如果大于0.07但小于1
                                            event.setCancelled(true);
                                            if (map.containsKey(player.getName())){
                                                map.put(player.getName(),map.get(player.getName()) + 1);
                                            }else {
                                                map.put(player.getName(),1);
                                            }
                                        }else if (tmp >= 0.03){//如果大于0.03但小于0.07
                                            event.setCancelled(true);
                                            if (map.containsKey(player.getName())){
                                                map.put(player.getName(),map.get(player.getName()) + 1);
                                            }else {
                                                map.put(player.getName(),1);
                                            }
                                        }else {//如果低于0.03
                                            event.setCancelled(true);
                                            if (map.containsKey(player.getName())){
                                                map.put(player.getName(),map.get(player.getName()) + 1);
                                            }else {
                                                map.put(player.getName(),1);
                                            }
                                            PlayerKB(event,map.get(player.getName()),2,"cancel","speed");
                                            map.remove(player.getName(),map.get(player.getName()));
                                        }
                                    }else if (new_x - x <= 1.03){
                                        event.setCancelled(true);
                                        if (map.containsKey(player.getName())){
                                            map.put(player.getName(),map.get(player.getName()) + 1);
                                        }else {
                                            map.put(player.getName(),1);
                                        }
                                    }else {
                                        event.setCancelled(true);
                                        if (map.containsKey(player.getName())){
                                            map.put(player.getName(),map.get(player.getName()) + 1);
                                        }else {
                                            map.put(player.getName(),1);
                                        }
                                    }
                                    PlayerKB(event,map.get(player.getName()),2,"cancel","speed");
                                    map.remove(player.getName(),map.get(player.getName()));
                                }//Z移动且X移动
                                event.setCancelled(true);
                                if (map.containsKey(player.getName())){
                                    if (map.get(player.getName()) >= 3){
                                        PlayerKB(event, map.get(player.getName()), 3,"cancel","speed");
                                        map.remove(player.getName(),map.get(player.getName()));
                                    }else {
                                        map.put(player.getName(), map.get(player.getName()) + 1);
                                    }
                                }

                            }else if (tmp > toback){
                                if (map.containsKey(player.getName())){
                                    if (map.get(player.getName()) >= 3){
                                        event.setCancelled(true);
                                        PlayerKB(event, map.get(player.getName()), 3,"cancel","speed");
                                        map.remove(player.getName(),map.get(player.getName()));
                                    }else {
                                        map.put(player.getName(), map.get(player.getName()) + 1);
                                        event.setCancelled(true);
                                    }
                                }
                            }
                        }
                    }//Z变化时
                }
            }
        }.runTaskLater(StarLightAPI.INSTANCE.getPlugin(),delay);
    }
}
