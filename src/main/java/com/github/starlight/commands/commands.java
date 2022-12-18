package com.github.starlight.commands;

import com.github.starlight.inventory.MainUI;
import com.github.starlight.manager.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static com.github.starlight.manager.ConfigManager.SystemError;
import static com.github.starlight.manager.ConfigManager.prefix;

/**
 * 插件主命令(/sl)
 */
public class commands implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("sl") || command.getName().equalsIgnoreCase("starlight")){
            if (args.length == 0){//如果只输入了/starlight或sl
                sender.sendMessage(ChatColor.GREEN+"/starlight reload|update|info|gui");
                return true;
            }
            if (args.length == 1){
                if (args[0].equalsIgnoreCase("reload")){//重载插件
                    data_commands.reload(sender);
                }
                if (args[0].equalsIgnoreCase("update")){//检查更新
                    data_commands.update(sender);
                }
                if (args[0].equalsIgnoreCase("info")){//关于插件的信息
                    data_commands.Info(sender);
                }
                if (args[0].equalsIgnoreCase("gui")){
                    if (sender instanceof Player){
                        if (sender.hasPermission("starlight.admin")){
                            try {
                                MainUI mainUI = new MainUI();
                                mainUI.Main_UI((Player) sender);
                            } catch (NullPointerException ignored) {}
                        }else {
                            sender.sendMessage(ChatColor.AQUA+prefix+ChatColor.RED+ConfigManager.NoPermission);
                        }
                    }else {
                        System.out.println(ChatColor.RED+SystemError);
                    }
                }//插件主界面
            }
        }
        return false;
    }

    /**
     * 当插件命令被输入时返回可用命令列表
     * @return list 是可用命令列表
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1){
            List<String> list = new ArrayList<>();
            list.add("reload");
            list.add("update");
            list.add("info");
            list.add("gui");
            return list;
        }
        return null;
    }
}
