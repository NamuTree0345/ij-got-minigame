package me.namutree0345.skillfight.objects;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Skill {
    public String name;

    // cooltime in tick
    public int cooltime;
    public int namunCooltime = 0;
    public Boolean canUse = true;

    public JavaPlugin plugin;
    public void onActivate(Player player, ItemStack itemStack) {}
    public void actCooltime(Player player, ItemStack itemStack) {
        if(!canUse) {
            System.out.println("canUseNot");
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "이 스킬을 사용하려면 " + namunCooltime + "만큼이 필요합니다."));
        } else {
            canUse = false;
            namunCooltime = cooltime / 20;
            int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> namunCooltime--, 0, 20);
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                Bukkit.getScheduler().cancelTask(id);
                canUse = true;
            }, cooltime);
        }

    }

    public Skill(JavaPlugin plugin) {
        this.plugin = plugin;
    }
}
