package com.github.vss.commands;

import com.github.vss.logger.Logger;
import com.github.vss.manager.ConfigManager;
import com.github.vss.update.Update;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;


import java.util.ArrayList;
import java.util.List;

import static com.github.vss.manager.ConfigManager.*;

public class commands implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("vss") && command.getName().equalsIgnoreCase("VSS")){
            if (args.length == 0){
                sender.sendMessage(ChatColor.GREEN+"/vss reload|update|version");
                return true;
            }
            if (args.length == 1){
                if (args[0].equalsIgnoreCase("reload")){
                    if (sender != null){
                        if (sender.hasPermission("vss.admin.reload") && sender.hasPermission("vss.admin")){
                            config.reloadConfig();
                            sender.sendMessage(ChatColor.GREEN+ConfigManager.ReloadMessage);
                        }else {
                            sender.sendMessage(ChatColor.RED+ConfigManager.NoPermission);
                        }
                    }else {
                        config.reloadConfig();
                        Logger.LOGGER.info(ChatColor.GREEN+ConfigManager.ReloadMessage);
                    }
                }
                if (args[0].equalsIgnoreCase("update")){
                    if (sender != null){
                        if (sender.hasPermission("vss.admin.update") && sender.hasPermission("vss.admin")){
                            Update.SystemUpdate(sender);
                        }else {
                            sender.sendMessage(ChatColor.RED+ConfigManager.NoPermission);
                        }
                    }else {
                        Update.SystemUpdate(null);
                    }
                }
                if (args[0].equalsIgnoreCase("version")){
                    if (Language.equals("zh_cn")){
                        sender.sendMessage(ChatColor.BLUE+"[C.A.S.S.I.E] "+ChatColor.AQUA+"插件版本为"+ChatColor.WHITE+Version);
                        sender.sendMessage(ChatColor.BLUE+"[C.A.S.S.I.E] "+ChatColor.AQUA+"作者的Github地址: https://github.com/3cxc/VSS");
                        sender.sendMessage(ChatColor.BLUE+"C.A.S.S.I.E: "+ChatColor.GREEN+"中央自动安全系统正在运作");
                        sender.sendMessage(ChatColor.BLUE+"Vanilla Security System: "+ChatColor.GREEN+"原版安全系统(反作弊)正在运作");
                    }else {
                        sender.sendMessage(ChatColor.BLUE+"[C.A.S.S.I.E] "+ChatColor.AQUA+"Plugin Version: "+ChatColor.WHITE+Version);
                        sender.sendMessage(ChatColor.BLUE+"[C.A.S.S.I.E] "+ChatColor.AQUA+"Author's GitHub address: https://github.com/3cxc/VSS");
                        sender.sendMessage(ChatColor.BLUE+"C.A.S.S.I.E: "+ChatColor.GREEN+"The central automatic security system is in operation");
                        sender.sendMessage(ChatColor.BLUE+"Vanilla Security System: "+ChatColor.GREEN+"Vanilla security system (anti cheating) is working");
                    }
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1){
            List<String> list = new ArrayList<>();
            list.add("reload");
            list.add("update");
            list.add("version");
            return list;
        }
        return null;
    }
}
