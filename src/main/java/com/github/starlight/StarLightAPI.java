package com.github.starlight;

import com.github.starlight.manager.ConfigManager;
import com.github.starlight.manager.InitManager;
import com.github.starlight.update.Update;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import static com.github.starlight.manager.ConfigManager.*;
import static org.bukkit.Bukkit.getServer;


/**
 * StarLightAPI
 * @version 2.0
 * @author 3cxc
 * @since 3.6.0
 */
public enum StarLightAPI {
    INSTANCE;

    private JavaPlugin plugin;

    private InitManager initManager;

    /**
     * 对JavaPlugin的获取
     * @see JavaPlugin
     * @return plugin
     */
    public JavaPlugin getPlugin(){
        return this.plugin;
    }

    /**
     * 插件加载阶段
     * @param Plugin 为本插件
     * @since 3.6.0
     */
    public void load(JavaPlugin Plugin){
        this.plugin = Plugin;
        plugin.getConfig().options().copyDefaults();
        plugin.saveDefaultConfig();
        File file1 = new File(plugin.getDataFolder() + "messages/" + "zh_cn.yml");
        File file2 = new File(plugin.getDataFolder() + "messages/" + "en_us.yml");
        if (!file1.exists()){
            plugin.saveResource("messages/zh_cn.yml", false);//生成语言文件
        }
        if (!file2.exists()){
            plugin.saveResource("messages/en_us.yml", false);
        }
    }

    /**
     * 插件运行阶段
     * @param Plugin 为本插件
     * @since 3.6.0
     */
    public void start(JavaPlugin Plugin){
        this.plugin = Plugin;
        ConfigManager configManager = new ConfigManager();
        configManager.Configload();
        UpdateTimer(plugin);
        SA_Timer(plugin);
        this.initManager = new InitManager();
        this.initManager.start();
        plugin.getLogger().info(ChatColor.GREEN+ConfigManager.EnableMessage);
    }

    /**
     * 定时更新检查
     * @param Plugin 为本插件
     * @see Update
     * @since 3.6.0
     */
    public void UpdateTimer(JavaPlugin Plugin){
        this.plugin = Plugin;
        long delay = 1000 * 60 * 3;//三秒后进行第一次检查
        long period = 1000 * 60 * 30;//每隔半小时检查一次更新
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Update.SystemTimeUpdate();
            }
        };
        Timer timer = new Timer("TimerUpdate");
        timer.schedule(timerTask,delay,period);
    }

    /**
     * 插件卸载阶段
     * @param Plugin 为本插件
     * @since 3.6.0
     */
    public void stop(JavaPlugin Plugin){
        this.plugin = Plugin;
        this.initManager = new InitManager();
        this.initManager.stop();
    }

    /**
     * 每5分钟就 踢出/封禁 在打击名单里的玩家
     * @author 3cxc
     * @Version 2.0
     */
    public void SA_Timer(JavaPlugin Plugin){
        this.plugin = Plugin;
        new BukkitRunnable() {
            @Override
            public void run() {
                for (int i = 0 ; i < salist2.size() ; i++){
                    if (config.getConfig().getString("SecurityAlert.actions", "ban").equals("kick")){
                        salist2.get(i).kickPlayer(ban + salist2.get(i).getUniqueId());
                    }else {
                        plugin.getServer().dispatchCommand(getServer().getConsoleSender(), "ban " +  salist2.get(i).getName() + " " + ban + salist2.get(i).getUniqueId());
                    }
                }

            }
        }.runTaskTimer(plugin, 5 * 60 * 1000, 5 * 60 * 1000);
    }
}
