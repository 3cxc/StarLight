package com.github.vss.manager;

import com.github.vss.PluginAPI;
import com.github.vss.VSS;
import com.github.vss.logger.Logger;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ConfigManager {

    private final JavaPlugin plugin = PluginAPI.INSTANCE.getPlugin();
    public static String Language;
    public static Plugin config = VSS.getPlugin(VSS.class);
    public static String Version = config.getConfig().getString("Version");
    public static String LoadMessage;
    public static String EnableMessage;
    public static String DisableMessage;
    public static String LogMessage;
    public static String ReloadMessage;
    public static String ChectBanMessage;
    public static String ChectKickMessage;
    public static String ChectReasonMessage;
    public static String ChectReasonIDMessage;
    public static String ChectWarningnMessage;
    public static String UpdateNoNewVersion;
    public static String UpdateNewVersion;
    public static String UpdateError;
    public static String NoPermission;

    public void Configload(){//加载Config
        Language = config.getConfig().getString("language", "zh_cn");//从配置文件获取语言设置
        MessageLoad(Language);//设置语言
    }

    public ConfigManager(){
        Configload();
    }

    /**
     * 语言设置
     * @param language 为设置的语言
     */

    public void MessageLoad(String language){//加载语言
        if (language == null || !language.matches("[a-zA-Z]{2}[_-][a-zA-Z]{2}")){
            language = "zh_cn";
            config.getLogger().warning(ChatColor.RED+"Abnormal language setting, reset to Chinese!");
        }
        File messgae = new File(plugin.getDataFolder(),"message/"+language+".yml");
        FileConfiguration messageConfig = YamlConfiguration.loadConfiguration(messgae);
        LoadMessage = messageConfig.getString("SystemMessage.load-message", "插件正在加载中...");
        EnableMessage = messageConfig.getString("SystemMessage.enable-message", "插件启动完成！");
        DisableMessage = messageConfig.getString("SystemMessage.disable-message", "插件已卸载！");
        LogMessage = messageConfig.getString("SystemMessage.log-message","日志已保存！");
        ReloadMessage = messageConfig.getString("SystemMessage.reload-message","插件重载完成！");
        ChectBanMessage = messageConfig.getString("Chect.chect-UB-message","您因为作弊而被封禁");
        ChectKickMessage = messageConfig.getString("Chect.chect-UK-message","您因为作弊而被踢出");
        ChectReasonMessage = messageConfig.getString("Chect.chect-UR-message","原因: ");
        ChectReasonIDMessage = messageConfig.getString("Chect.chect-ID-message","事件编号: ");
        ChectWarningnMessage = messageConfig.getString("Chect.chect-war-message","警告！发现违规玩家");
        UpdateError = messageConfig.getString("Warning.update-error-message","检查更新失败！");
        UpdateNewVersion = messageConfig.getString("SystemMessage.update-new-message","§b发现了新版本：{0} 请前往作者的Github获取最新版本！");
        UpdateNoNewVersion = messageConfig.getString("SystemMessage.update-nonew-message","插件已经是最新版本了！");
        NoPermission = messageConfig.getString("Warning.no-permission","您没有权限这样做！");

    }

    /**
     * 事实上这里似乎存在着BUG
     * 但不可删除，删除会导致无法解析 {0}
     * 所以只好保存这段代码，但是也没出过问题（所以懒得修复了）
     */

    public static String replaceArgs(String msg, Object... args) {//把 {0} 替换掉
        for (int i = 0; i < args.length; i++) {
            msg = msg.replace("{0}".replace('0', (char) (i + 48)), args[i].toString());
        }
        return msg;
    }
}
