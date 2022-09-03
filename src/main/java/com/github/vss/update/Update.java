package com.github.vss.update;

import com.github.vss.VSS;
import com.github.vss.manager.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Objects;

import static com.github.vss.manager.ConfigManager.Version;

public class Update {
    /**
     * 负责更新检查的代码
     * @param sender 为结果发送者(更新检查完成后会把结果发送)
     */
    @SuppressWarnings("CharsetObjectCanBeUsed")
    public static void SystemUpdate(CommandSender sender){
        new BukkitRunnable(){
            public void run() {
                String webver = ""; //从Github上面获取的版本号(临时变量)
                String su = "OK";
                try {
                    URL url = new URL("https://raw.githubusercontent.com/3cxc/VSS/master/version.txt");
                    InputStream is = url.openStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    webver = br.readLine();
                    is.close();
                    br.close();
                }catch (Throwable ignored) {
                    sender.sendMessage(ChatColor.RED+ConfigManager.UpdateError);
                    su = "ERROR";
                }
                if (Objects.equals(Version, webver) && !su.equals("OK")){
                    sender.sendMessage(ChatColor.AQUA+ConfigManager.UpdateNoNewVersion);
                }else{
                    sender.sendMessage(ChatColor.AQUA+ConfigManager.replaceArgs(ConfigManager.UpdateNewVersion, webver));
                }
            }
        }.runTaskAsynchronously(VSS.getPlugin(VSS.class));
    }
}
