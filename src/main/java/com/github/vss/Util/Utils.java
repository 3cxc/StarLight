package com.github.vss.Util;

import com.github.vss.logger.Logger;
import com.github.vss.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class Utils {

    /**
     * 一些常用的方法存储在这里,例如kick,ban,以及发送一条信息到所有玩家
     * @param player 为输入的玩家
     * @param reason 为原因
     * 以上两个在Kick和Ban的方法是通用的(reason是这里的所有方法都通用的)
     */

    public static void vss_Banplayer(Player player, String reason){
        UUID reason_id = player.getUniqueId();
        player.kickPlayer(ChatColor.BLUE+"C.A.S.S.I.E\n"+ChatColor.GREEN+ConfigManager.ChectBanMessage+"\n"+ChatColor.AQUA+ConfigManager.ChectReasonMessage+reason+ChatColor.GRAY+"\n"+ConfigManager.ChectReasonIDMessage+reason_id);
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "ban "+player.getName());
    }

    public static void vss_Kickplayer(Player player, String reason){
        UUID reason_id = player.getUniqueId();
        player.kickPlayer(ChatColor.BLUE+"C.A.S.S.I.E\n"+ChatColor.GREEN+ConfigManager.ChectKickMessage+"\n"+ChatColor.AQUA+ConfigManager.ChectReasonMessage+reason+ChatColor.GRAY+"\n"+ConfigManager.ChectReasonIDMessage+reason_id);
    }

    /**
     * 由于这一部分代码没用到所所以注释掉了
     * 这一部分代码就是负责发一条自定义信息给所有玩家的
     * @param reason (其实是input)就是自定义的信息
     */

    /*
    public static void AllPlayerChat(String input){
        ArrayList<Player> OnlinePlayerList = new ArrayList<>(Bukkit.getOnlinePlayers());
        for (int i = 0 ; i < OnlinePlayerList.size() ; i++){
            Player player = OnlinePlayerList.get(i).getPlayer();
            player.sendMessage(input);
        }
    }

     */

    public static void AllAdminWarning(Player WarningPlayer, String reason){
        Logger.LOGGER.info(ChatColor.BLUE+"[C.A.S.S.I.E]: "+ChatColor.RED+ConfigManager.ChectWarningnMessage+ChatColor.WHITE+WarningPlayer.getName()+ChatColor.GRAY+ConfigManager.ChectReasonMessage+reason);
        ArrayList<Player> OnlinePlayerList = new ArrayList<>(Bukkit.getOnlinePlayers());
        for (int i = 0 ; i < OnlinePlayerList.size() ; i++){
            Player player = OnlinePlayerList.get(i).getPlayer();
            if (player.isOp()){
                player.sendMessage(ChatColor.BLUE+"C.A.S.S.I.E: "+ChatColor.RED+ConfigManager.ChectWarningnMessage+ChatColor.WHITE+WarningPlayer.getName()+ChatColor.GRAY+ConfigManager.ChectReasonMessage+reason);
            }
        }
    }
}
