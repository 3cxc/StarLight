package com.github.starlight.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static com.github.starlight.manager.ConfigManager.*;

/**
 * 安全警报命令(/sa)
 */
public class sa_commands implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("sa")){
            if (args.length == 0){//仅输入/sa时
                sender.sendMessage(ChatColor.GRAY+"/sa chect");
                return true;
            }
            if (args.length == 1){
                if (args[0].equalsIgnoreCase("chect")){
                    if (sender instanceof Player){
                        boolean tmp = false;
                        for (int i = 0; i < salist.size() ; i++){
                            if (sender.getName().equals(salist.get(i))){
                                tmp = true;
                            }
                            i++;
                        }
                        if (tmp){
                            salist.remove(sender.getName());
                            salist2.remove((Player) sender);
                            PlayerList.add(sender.getName());
                            config.getConfig().set("SecurityAlert.Whitelist",PlayerList);
                            config.saveConfig();
                            config.reloadConfig();
                            PlayerList = config.getConfig().getStringList("SecurityAlert.Whitelist");
                            sender.sendMessage(ChatColor.GREEN+"验证完成！下次即可不用验证！");
                        }else {
                            sender.sendMessage(ChatColor.RED+"您不用验证！");
                        }
                    }else {
                        config.reloadConfig();
                        config.getLogger().info(ChatColor.RED+SystemError);
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
            list.add("chect");
            return list;
        }
        return null;
    }
}
