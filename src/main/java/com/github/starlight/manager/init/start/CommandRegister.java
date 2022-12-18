package com.github.starlight.manager.init.start;

import com.github.starlight.StarLightAPI;
import com.github.starlight.commands.commands;
import com.github.starlight.commands.sa_commands;
import com.github.starlight.logger.Logger;
import com.github.starlight.manager.init.Initable;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import static com.github.starlight.manager.ConfigManager.PluginError;

public class CommandRegister implements Initable {
    /**
     * 当插件启动完成时要执行的操作
     * <p>通常用以注册命令
     * @see Initable
     * @implNote Start()方法
     * <p><font color="red" >不建议第三方开发者调用，有可能会破坏接口</font>
     */
    public void Start(){
        JavaPlugin plugin = StarLightAPI.INSTANCE.getPlugin();
        PluginCommand commandA = plugin.getCommand("starlight");
        PluginCommand commandB = plugin.getCommand("sl");
        PluginCommand commandC = plugin.getCommand("sa");
        if (commandA != null){
            commandA.setExecutor(new commands());
            commandA.setTabCompleter(new commands());
        }
        if (commandB != null){
            commandB.setExecutor(new commands());
            commandB.setTabCompleter(new commands());
        }
        if (commandC != null){
            commandC.setExecutor(new sa_commands());
            commandC.setTabCompleter(new sa_commands());
        }
        if (commandA == null || commandB == null || commandC == null){//出现错误时卸载插件
            Logger.LOGGER.info(ChatColor.RED+PluginError);
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        }
    }
}
