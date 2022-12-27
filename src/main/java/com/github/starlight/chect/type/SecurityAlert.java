package com.github.starlight.chect.type;

import com.github.starlight.StarLightAPI;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

import static com.github.starlight.manager.ConfigManager.*;

/**
 * 安全警报系统(SA)
 *
 * @author 3cxc
 * @since 3.7.0
 * @version 2.0
 */
public class SecurityAlert implements Listener{
    /**
     *
     * @param event 监测移动事件 如果玩家没有验证，禁止移动
     * @author 3cxc
     */
    @EventHandler
    public void Move(PlayerMoveEvent event) {
        boolean tmp = false;
        for (int i = 0; i < salist.size(); i++) {//检测是否在非法IP名单中
            if (event.getPlayer().getName().equals(salist.get(i))) {
                tmp = true;
            }
        }
        if (tmp) {//如果在名单中
            event.setCancelled(true);//拦截移动
            event.getPlayer().sendMessage(ChatColor.AQUA+"[SA] "+ChatColor.RED + "请先输入命令/sa chect 验证你的IP！");//反馈
        }
    }
    /**
     *
     * @param event IP地址检查 如果玩家IP违规，就要求验证
     * @author 3cxc
     */
    @EventHandler
    public void AddressCheck(PlayerLoginEvent event) {
        if (!event.getPlayer().hasPermission("starlight.ipcheck.bypass")){
            new BukkitRunnable(){//异步执行防卡服
                @Override
                public void run(){
                    boolean tmp = false;
                    for (int i = 0 ; i < PlayerList.size() ; i++){//验证是否在白名单里
                        if (PlayerList.get(i).contains(event.getPlayer().getName())){
                            tmp = true;//是则返回True
                        }
                    }
                    if (!tmp){//如果不在白名单
                        List<String> list = config.getConfig().getStringList("SecurityAlert.AddressList");//地址验证
                        int cs = 0;
                        for (int i = 0; i < list.size() ; i++) {
                            if (!event.getHostname().contains(list.get(i))) {
                                cs++;
                            }
                        }
                        if (cs >= 2 & !salist.contains(event.getPlayer().getName())) {//如果IP违规
                            salist.add(event.getPlayer().getName());//记录到系统里
                            salist2.add(event.getPlayer());
                        }
                    }
                }
            }.runTaskAsynchronously(StarLightAPI.INSTANCE.getPlugin());
        }
    }
    /**
     * @param event 未验证前的命令检查，防止玩家执行非法命令
     * @author 3cxc
     */
    @EventHandler
    public void CommandCheck(PlayerCommandPreprocessEvent event) {//未验证前的命令检查
        boolean tmp = false;
        for (int i = 0; i < salist.size(); i++) {//检测是否在违规IP名单
            if (event.getPlayer().getName().equals(salist.get(i))) {
                tmp = true;
            }
        }
        if (tmp){//如果在打击名单
            boolean tmp2 = false;
            for (int i = 0 ; i < config.getConfig().getStringList("SecurityAlert.CommandWhiteList").size() ; i++){//检查是不是白名单命令
                if (event.getMessage().contains(config.getConfig().getStringList("SecurityAlert.CommandWhiteList").get(i))){
                    tmp2 = true;
                }
            }
            if (!tmp2) {//如果不是白名单命令
                event.setCancelled(true);//拦截命令执行
                event.getPlayer().sendMessage(ChatColor.AQUA+"[SA] "+ChatColor.RED + "请先输入命令/sa chect 验证！否则无法使用大部分命令！");
            }
        }
    }
}
