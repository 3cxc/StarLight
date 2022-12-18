package com.github.starlight.event.type;

import com.github.starlight.inventory.SittingUI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static com.github.starlight.manager.ConfigManager.config;

public class SittingUIEvent implements Listener {
    /**
     * 防止菜单内物品被移动(就是防刷物品)
     * @param event 为传入的事件
     */
    @EventHandler
    public void Item_Movement(InventoryClickEvent event){
        if (event.getView().getTitle().equals(ChatColor.GRAY + "插件设置")){
            event.setCancelled(true);
        }
    }

    /**
     * 自动更新选项设置
     * @param event 为传入事件
     */
    @EventHandler
    public void ClickUpdate(InventoryClickEvent event){
        try {
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GRAY+"自动更新")){
                if (event.getView().getTitle().equals(ChatColor.GRAY + "插件设置")){
                    if (config.getConfig().getBoolean("Update",true)){
                     config.getConfig().set("Update",false);
                    }else {
                        config.getConfig().set("Update",true);
                    }
                    config.saveConfig();
                    config.reloadConfig();
                    event.getWhoClicked().closeInventory();
                    SittingUI ui = new SittingUI();
                    ui.Main_UI((Player) event.getWhoClicked());
                }
            }
        }catch (NullPointerException ignored){}
    }

    /**
     * 插件警报选项设置
     * @param event 为传入事件
     */
    @EventHandler
    public void ClickAlert(InventoryClickEvent event){
        try {
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GRAY+"插件警报")){
                if (event.getView().getTitle().equals(ChatColor.GRAY + "插件设置")){
                    if (config.getConfig().getBoolean("alert",true)){
                        config.getConfig().set("alert",false);
                    }else {
                        config.getConfig().set("alert",true);
                    }
                    config.saveConfig();
                    config.reloadConfig();
                    event.getWhoClicked().closeInventory();
                    SittingUI ui = new SittingUI();
                    ui.Main_UI((Player) event.getWhoClicked());
                }
            }
        }catch (NullPointerException ignored){}
    }

    /**
     * Debug选项设置
     * @param event 为传入事件
     */
    @EventHandler
    public void ClickDebug(InventoryClickEvent event){
        try {
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED+"Debug")){
                if (event.getView().getTitle().equals(ChatColor.GRAY + "插件设置")){
                    if (config.getConfig().getBoolean("debug",true)){
                        config.getConfig().set("debug",false);
                    }else {
                        config.getConfig().set("debug",true);
                    }
                    config.saveConfig();
                    config.reloadConfig();
                    event.getWhoClicked().closeInventory();
                    SittingUI ui = new SittingUI();
                    ui.Main_UI((Player) event.getWhoClicked());
                }
            }
        }catch (NullPointerException ignored){}
    }

    /**
     * 安全警报选项设置
     * @param event 为传入事件
     */
    @EventHandler
    public void ClickSA(InventoryClickEvent event){
        try {
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.WHITE+"安全警报")){
                if (event.getView().getTitle().equals(ChatColor.GRAY + "插件设置")){
                    if (config.getConfig().getBoolean("SecurityAlert.active",true)){
                        config.getConfig().set("SecurityAlert.active",false);
                    }else {
                        config.getConfig().set("SecurityAlert.active",true);
                    }
                    config.saveConfig();
                    config.reloadConfig();
                    event.getWhoClicked().closeInventory();
                    SittingUI ui = new SittingUI();
                    ui.Main_UI((Player) event.getWhoClicked());
                }
            }
        }catch (NullPointerException ignored){}
    }
}
