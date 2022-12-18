package com.github.starlight.update;

import com.github.starlight.StarLight;
import com.github.starlight.logger.Logger;
import com.github.starlight.manager.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.github.starlight.manager.ConfigManager.Version;
import static com.github.starlight.manager.ConfigManager.config;

@SuppressWarnings("deprecation")
public class Update {
    /**
     * 负责更新检查的代码
     * @param sender 为结果发送者(更新检查完成后会把结果发送)
     * @author 3cxc
     * @Version 3.0
     * @return 返回boolean值(true则为检查到更新)
     */
    public static boolean SystemUpdate(CommandSender sender){
        String tmp = CheckUpdate();//获取Github上的插件版本号
        if (tmp != null){
            if (Version.equals(tmp)){
                sender.sendMessage(ChatColor.AQUA+ConfigManager.UpdateNoNewVersion);
                return false;
            }else {
                sender.sendMessage(ChatColor.AQUA+ConfigManager.replaceArgs(ConfigManager.UpdateNewVersion, tmp));
                return true;
            }
        }else {
            sender.sendMessage(ChatColor.RED+ConfigManager.UpdateError);
            return false;
        }
    }

    /**
     * 负责更新检查的代码(用作定时更新，发送对象为控制台)
     * @author 3cxc
     * @Version 3.0
     * @return 返回boolean值(true则为检查到更新)
     */

    public static boolean SystemTimeUpdate(){
        String tmp = CheckUpdate();//获取Github上的插件版本号
        if (tmp != null){
            if (Version.equals(tmp)){
                Logger.LOGGER.info(ChatColor.AQUA+ConfigManager.UpdateNoNewVersion);
                return false;
            }else {
                Logger.LOGGER.info(ChatColor.AQUA+ConfigManager.replaceArgs(ConfigManager.UpdateNewVersion, tmp));
                return true;
            }
        }else {
            Logger.LOGGER.info(ChatColor.RED+ConfigManager.UpdateError);
            return false;
        }
    }

    /**
     * 负责拉取最新版本号的代码
     * @author 3cxc
     * @Version 3.0
     * @exception java.io.IOException
     * @exception java.net.MalformedURLException
     * @exception java.io.UnsupportedEncodingException
     * @return 返回Github上的插件版本号(不负责处理)
     */
    public static String CheckUpdate(){
        final String[] webver = {null}; //从Github上面获取的版本号
        new BukkitRunnable(){
            public void run() {
                //noinspection CatchMayIgnoreException
                try {
                    URL url = new URL("https://raw.githubusercontent.com/3cxc/starlight/master/.github/workflows/version.txt");
                    InputStream is = url.openStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                    webver[0] = br.readLine();
                    is.close();
                    br.close();
                }catch (Throwable ignored) {
                    if (config.getConfig().getBoolean("debug",false)){
                        ignored.printStackTrace();
                    }
                }//如果出错了就返回null
            }
        }.runTaskAsynchronously(StarLight.getPlugin(StarLight.class));
        return webver[0];
    }
}
