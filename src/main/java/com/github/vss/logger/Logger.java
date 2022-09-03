package com.github.vss.logger;

import com.github.vss.PluginAPI;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@SuppressWarnings("ResultOfMethodCallIgnored")
public enum Logger {
    LOGGER;

    private final JavaPlugin plugin = PluginAPI.INSTANCE.getPlugin();

    /**
     * 创建文件夹
     * @param name 为创建的文件夹名称
     */
    public void CreateFolder(String name){
        File Folder = new File(name);
        if (!Folder.exists()){
            Folder.mkdir();
            Logger.LOGGER.info(ChatColor.GREEN+"Log file generation completed！");
        }
    }
    /**
     * 此部分代码因无作用而被注释掉,但不代表在未来不会使用
     */


    /*
    public void CreateLog(String Name,String input, String VL) throws IOException{
        if (VL.equals("info")){
            File Log = new File("Log/"+Name);
            if (!Log.exists()){
                Log.createNewFile();
            }
            WriteLog("Log/"+Name,input);
            info(ConfigManager.LogMessage);
        }
    }

    public void WriteLog(String file,String input) throws IOException{
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(input);
        }
    }

    */


    public void warning(String message){
        plugin.getLogger().warning(message);
    }

    public void info(String message){
        plugin.getLogger().info(message);
    }
}
