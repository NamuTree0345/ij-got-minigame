package me.namutree0345.skillfight.skills;

import me.namutree0345.skillfight.objects.Skill;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class MassBow extends Skill {
    public MassBow(JavaPlugin plugin) {
        super(plugin);
        name = "연속 활발사";
        cooltime = 200;
    }

    @Override
    public void actCooltime(Player player, ItemStack itemStack) {
        super.actCooltime(player, itemStack);
    }

    @Override
    public void onActivate(Player player, ItemStack itemStack) {
        if(canUse) {
            actCooltime(player, itemStack);
            int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
                Arrow arrow = player.launchProjectile(Arrow.class);
                arrow.setGlowing(true);
                arrow.addScoreboardTag(player.getUniqueId().toString() + "-arrow-skill");
            }, 0L, 1L);
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> Bukkit.getScheduler().cancelTask(taskID), 60L);

        }

    }
}
