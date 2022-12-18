package com.github.starlight.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

import static com.github.starlight.Util.Util.CreateItemLore;
import static com.github.starlight.manager.ConfigManager.config;

public class SittingUI {
    public void Main_UI(Player player){
        Inventory ui = Bukkit.createInventory(player,9, ChatColor.GRAY+"插件设置");
        //设置Lore
        List<String> updatelist = new ArrayList<>();
        updatelist.add(ChatColor.AQUA+"状态: "+ChatColor.GOLD+config.getConfig().getBoolean("Update"));
        List<String> alertlist = new ArrayList<>();
        alertlist.add(ChatColor.AQUA+"状态: "+ChatColor.GOLD+config.getConfig().getBoolean("alert"));
        List<String> debuglist = new ArrayList<>();
        debuglist.add(ChatColor.AQUA+"状态: "+ChatColor.GOLD+config.getConfig().getBoolean("debug"));
        List<String> SAlist = new ArrayList<>();
        SAlist.add(ChatColor.AQUA+"状态: "+ChatColor.GOLD+config.getConfig().getBoolean("SecurityAlert.active"));
        //设置物品
        ui.setItem(0,CreateItemLore(Material.ANVIL,ChatColor.GRAY+"自动更新",1,updatelist));
        ui.setItem(1,CreateItemLore(Material.ANVIL,ChatColor.GRAY+"插件警报",1,alertlist));
        ui.setItem(2,CreateItemLore(Material.ANVIL,ChatColor.RED+"Debug",1,debuglist));
        ui.setItem(3,CreateItemLore(Material.ANVIL,ChatColor.WHITE+"安全警报",1,SAlist));
        player.openInventory(ui);
    }
}
