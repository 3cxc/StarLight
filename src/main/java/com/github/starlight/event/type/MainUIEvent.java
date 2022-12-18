package com.github.starlight.event.type;

import com.github.starlight.commands.data_commands;
import com.github.starlight.inventory.SittingUI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MainUIEvent implements Listener {
    /**
     * 防止菜单内物品被移动(就是防刷物品)
     * @param event 为传入的事件
     */
    @EventHandler
    public void Item_Movement(InventoryClickEvent event){
        if (event.getView().getTitle().equals(ChatColor.GRAY + "菜单")){
            event.setCancelled(true);
        }
    }

    /**
     * 打开插件信息
     * @param event 为传入事件
     */
    @EventHandler
    public void ClickInfo(InventoryClickEvent event){
        try {
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA+"插件信息")){
                if (event.getView().getTitle().equals(ChatColor.GRAY + "菜单")){
                    Player player = (Player) event.getWhoClicked();
                    player.closeInventory();
                    data_commands.Info(event.getWhoClicked());
                }
            }
        }catch (NullPointerException ignored){}
    }

    /**
     * 打开检查更新
     * @param event 为传入事件
     */
    @EventHandler
    public void ClickUpdate(InventoryClickEvent event){
        try {
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN+"检查更新")){
                if (event.getView().getTitle().equals(ChatColor.GRAY + "菜单")){
                    Player player = (Player) event.getWhoClicked();
                    player.closeInventory();
                    data_commands.update(event.getWhoClicked());
                }
            }
        }catch (NullPointerException ignored){}
    }

    /**
     * 打开插件设置
     * @param event 为传入事件
     */
    @EventHandler
    public void ClickSitting(InventoryClickEvent event){
        try {
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.WHITE+"配置插件设置")){
                if (event.getView().getTitle().equals(ChatColor.GRAY + "菜单")){
                    Player player = (Player) event.getWhoClicked();
                    player.closeInventory();
                    SittingUI sittingUI = new SittingUI();
                    sittingUI.Main_UI((Player) event.getWhoClicked());
                }
            }
        }catch (NullPointerException ignored){}
    }
}
