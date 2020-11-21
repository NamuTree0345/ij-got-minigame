package me.namutree0345.skillfight.skills;

import me.namutree0345.skillfight.objects.Skill;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;

public class HealNearPlayers extends Skill {
    // 50블럭 이내 플레이어 힐
    public HealNearPlayers(JavaPlugin plugin) {
        super(plugin);
        name = "강한 심장";
        cooltime = 600;
    }

    @Override
    public void actCooltime(Player player, ItemStack itemStack) {
        super.actCooltime(player, itemStack);
    }

    @Override
    public void onActivate(Player player, ItemStack itemStack) {
        if(canUse) {
            actCooltime(player, itemStack);
            System.out.println("uhju");
            int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                @Override
                public void run() {
                    Collection<Player> plrs = player.getLocation().getNearbyPlayers(50);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 20));
                    plrs.forEach(player1 -> {
                        player1.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 20));
                    });

                }
            }, 0L, 20L);
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    Bukkit.getScheduler().cancelTask(taskID);
                }
            }, 400L);

        }

    }
}
