package me.namutree0345.skillfight.events;

import me.namutree0345.skillfight.skills.Dash;
import me.namutree0345.skillfight.skills.HealNearPlayers;
import me.namutree0345.skillfight.skills.MassBow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class SkillItemHandler implements Listener {

    // massbow map
    HashMap<String, MassBow> playerMBSkillMap = new HashMap<>();
    HashMap<String, HealNearPlayers> playerHNPSkillMap = new HashMap<>();
    HashMap<String, Dash> playerDSkillMap = new HashMap<>();
    JavaPlugin plugin;

    public SkillItemHandler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRightclick(PlayerInteractEvent event) {
        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(event.getPlayer().getInventory().getItemInMainHand() != null) {
                ItemStack onHand = event.getPlayer().getInventory().getItemInMainHand();
                if(onHand.hasItemMeta()) {
                    switch (onHand.getItemMeta().getDisplayName()) {
                        case "§a연속 활발사":
                            if(playerMBSkillMap.containsKey(event.getPlayer().getUniqueId().toString())) {
                                playerMBSkillMap.get(event.getPlayer().getUniqueId().toString()).onActivate(event.getPlayer(), onHand);
                            } else {
                                MassBow massBow = new MassBow(plugin);
                                massBow.onActivate(event.getPlayer(), onHand);
                                playerMBSkillMap.put(event.getPlayer().getUniqueId().toString(), massBow);
                            }
                            break;
                        case "§a강한 심장":
                            if(playerHNPSkillMap.containsKey(event.getPlayer().getUniqueId().toString())) {
                                playerHNPSkillMap.get(event.getPlayer().getUniqueId().toString()).onActivate(event.getPlayer(), onHand);
                            } else {
                                HealNearPlayers hnp = new HealNearPlayers(plugin);
                                hnp.onActivate(event.getPlayer(), onHand);
                                playerHNPSkillMap.put(event.getPlayer().getUniqueId().toString(), hnp);
                            }
                            break;
                        case "§a대쉬":
                            if(playerDSkillMap.containsKey(event.getPlayer().getUniqueId().toString())) {
                                playerDSkillMap.get(event.getPlayer().getUniqueId().toString()).onActivate(event.getPlayer(), onHand);
                            } else {
                                Dash d = new Dash(plugin);
                                d.onActivate(event.getPlayer(), onHand);
                                playerDSkillMap.put(event.getPlayer().getUniqueId().toString(), d);
                            }
                            break;

                    }
                }
            }
        }
    }
}
