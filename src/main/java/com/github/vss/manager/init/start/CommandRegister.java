package com.github.vss.manager.init.start;

import com.github.vss.PluginAPI;
import com.github.vss.commands.commands;
import com.github.vss.logger.Logger;
import com.github.vss.manager.init.Initable;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandRegister implements Initable {
    public void Start(){
        JavaPlugin plugin = PluginAPI.INSTANCE.getPlugin();
        PluginCommand commandA = plugin.getCommand("vss");
        if (commandA != null){
            commandA.setExecutor(new commands());
            commandA.setTabCompleter(new commands());
        }
        if (commandA == null){//出现错误时关闭服务器
            Logger.LOGGER.warning(ChatColor.BLUE+"C.A.S.S.I.E: "+ChatColor.RED+"The command processing system is not loaded, the server will be shut down！");
            plugin.getServer().shutdown();
        }
    }
}
