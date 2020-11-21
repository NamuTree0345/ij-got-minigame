package me.namutree0345.skillfight.skills;

import com.destroystokyo.paper.ParticleBuilder;
import me.namutree0345.skillfight.objects.Skill;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Dash extends Skill {

    public Dash(JavaPlugin plugin) {
        super(plugin);
        name = "대시";
        cooltime = 2;
    }

    @Override
    public void actCooltime(Player player, ItemStack itemStack) {
        super.actCooltime(player, itemStack);
    }

    @Override
    public void onActivate(Player player, ItemStack itemStack) {
        if(canUse) {
            actCooltime(player, itemStack);
            Location ml = player.getLocation();

            ml.setX(ml.getX() + ml.getDirection().getX() * 5);
            ml.setZ(ml.getZ() + ml.getDirection().getZ() * 5);

            ParticleBuilder pb = new ParticleBuilder(Particle.EXPLOSION_LARGE);
            pb.location(player.getLocation());
            pb.spawn();
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 5, 1);
            player.teleport(ml);
        }

    }
}
