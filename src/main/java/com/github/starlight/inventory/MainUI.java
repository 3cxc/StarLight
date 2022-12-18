package com.github.starlight.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import static com.github.starlight.Util.Util.CreateItemNoLore;

/**
 * 插件主页面
 * @since 3.7.0
 * @author 3cxc
 */
public class MainUI {
    /**
     * 初始化插件主界面
     * @param player 为要打开页面的玩家
     */
    public void Main_UI(Player player){
        Inventory ui = Bukkit.createInventory(player,9, ChatColor.GRAY+"菜单");
        ui.setItem(0,CreateItemNoLore(Material.PAPER,ChatColor.AQUA+"插件信息",1));
        ui.setItem(1,CreateItemNoLore(Material.PLAYER_HEAD,ChatColor.RED+"查看违规玩家",1));
        ui.setItem(2,CreateItemNoLore(Material.COBWEB,ChatColor.GREEN+"检查更新",1));
        ui.setItem(3,CreateItemNoLore(Material.ANVIL,ChatColor.WHITE+"配置插件设置",1));
        player.openInventory(ui);
    }
}
