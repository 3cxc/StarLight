package com.github.starlight.commands;

import com.github.starlight.logger.Logger;
import com.github.starlight.manager.ConfigManager;
import com.github.starlight.update.Update;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.github.starlight.manager.ConfigManager.*;
import static com.github.starlight.manager.ConfigManager.prefix;

/**
 * 命令处理类
 * 需要执行的命令的内容存放在这
 * @version 1.0
 * @since 3.7.0
 * @author 3cxc
 * @see commands
 */
public class data_commands {
    /**
     * 输出插件信息
     * @param sender 为要输出的玩家(或控制台)
     */
    public static void Info(CommandSender sender){
        if (Language.equals("zh_cn")){//如果语言设置为中文
            sender.sendMessage(ChatColor.GRAY+prefix+ChatColor.GRAY+"StarLight"+ChatColor.AQUA+"反作弊");
            sender.sendMessage(ChatColor.GRAY+prefix+ChatColor.AQUA+"版本: "+ChatColor.WHITE+Version+ChatColor.AQUA+"(LTS)");
            if (config.getConfig().getBoolean("debug",false)){
                sender.sendMessage(ChatColor.GRAY+prefix+ChatColor.AQUA+"插件架构: "+ChatColor.GRAY+"SFL");
                sender.sendMessage(ChatColor.GRAY+prefix+ChatColor.AQUA+"Speed模块版本: "+ChatColor.GRAY+"3.0");
                sender.sendMessage(ChatColor.GRAY+prefix+ChatColor.AQUA+"Fly模块版本: "+ChatColor.GRAY+"2.0");
            }
            sender.sendMessage(ChatColor.GRAY+prefix+ChatColor.GOLD+"插件开源地址: https://github.com/3cxc/VSS");
        }else {//不是中文就切为英文
            sender.sendMessage(ChatColor.GRAY+prefix+ChatColor.GRAY+"StarLight"+ChatColor.AQUA+"Anti cheating");
            sender.sendMessage(ChatColor.GRAY+prefix+ChatColor.AQUA+"Version: "+ChatColor.WHITE+Version+ChatColor.AQUA+"(LTS)");
            if (config.getConfig().getBoolean("debug",false)){
                sender.sendMessage(ChatColor.GRAY+prefix+ChatColor.AQUA+"Plugin architecture: "+ChatColor.GRAY+"SFL");
                sender.sendMessage(ChatColor.GRAY+prefix+ChatColor.AQUA+"Speed detection module version: "+ChatColor.GRAY+"3.0");
                sender.sendMessage(ChatColor.GRAY+prefix+ChatColor.AQUA+"Fly detection module version: "+ChatColor.GRAY+"2.0");
            }
            sender.sendMessage(ChatColor.GRAY+prefix+ChatColor.GOLD+"Plugin open source address: https://github.com/3cxc/VSS");
        }
    }//输出插件信息

    /**
     * 检查更新
     * @param sender 为要输出更新信息的玩家(或控制台)
     */
    public static void update(CommandSender sender){
        if (sender instanceof Player){
            if (sender.hasPermission("starlight.admin.update")){
                Update.SystemUpdate(sender);
            }else {
                sender.sendMessage(ChatColor.AQUA+prefix+ChatColor.RED+ ConfigManager.NoPermission);
            }
        }else {
            Update.SystemTimeUpdate();//发起一次更新检查
        }
    }//检查更新

    /**
     * 重载插件
     * @param sender 为要输出重载信息的玩家(或控制台)
     */
    public static void reload(CommandSender sender){
        if (sender instanceof Player){
            if (sender.hasPermission("starlight.admin.reload")){
                config.reloadConfig();
                ConfigManager configManager = new ConfigManager();
                configManager.Configload();
                sender.sendMessage(ChatColor.AQUA+prefix+ChatColor.GREEN+ConfigManager.ReloadMessage);
            }else {
                sender.sendMessage(ChatColor.AQUA+prefix+ChatColor.RED+ConfigManager.NoPermission);
            }
        }else {
            config.reloadConfig();
            ConfigManager configManager = new ConfigManager();
            configManager.Configload();
            Logger.LOGGER.info(ChatColor.GREEN+ConfigManager.ReloadMessage);
        }
    }//重载插件
}
