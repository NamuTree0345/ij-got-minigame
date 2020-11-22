package me.namutree0345.skillfight.commands;

import me.namutree0345.skillfight.events.ClassSelectHandler;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class StartGame implements CommandExecutor {
    JavaPlugin plugin;

    public StartGame(JavaPlugin p) {
        plugin = p;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Bukkit.broadcastMessage("3초 후 플레이어 이동");
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            Player plr = (Player) sender;
            Location loc = plr.getWorld().locateNearestStructure(plr.getLocation(), SetMap.gameMapType, 1000, true);
            for(Player p : Bukkit.getOnlinePlayers()) {

                p.getInventory().clear();
                p.getInventory().addItem(new ItemStack(Material.LAVA_BUCKET));
                p.getInventory().addItem(new ItemStack(Material.WATER_BUCKET));
                p.getInventory().addItem(new ItemStack(Material.FLINT_AND_STEEL));
                p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 10));

                if(ClassSelectHandler.classSelectMap.containsKey(p.getUniqueId().toString())) {
                    if(ClassSelectHandler.classSelectMap.get(p.getUniqueId().toString()) == "sword") {
                        ItemStack is = new ItemStack(Material.NETHERITE_SWORD);
                        ItemMeta im = is.getItemMeta();
                        im.setDisplayName(ChatColor.WHITE + "숙련자의 검");
                        is.setItemMeta(im);
                        is.addEnchantment(Enchantment.KNOCKBACK, 2);
                        is.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                        is.addEnchantment(Enchantment.FIRE_ASPECT, 2);
                        p.getInventory().setItemInOffHand(new ItemStack(Material.SHIELD));
                        p.getInventory().addItem(is);
                        p.getInventory().setChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE));
                        p.getInventory().setHelmet(new ItemStack(Material.NETHERITE_HELMET));
                    } else if(ClassSelectHandler.classSelectMap.get(p.getUniqueId().toString()) == "bow") {

                        ItemStack b = new ItemStack(Material.BOW);
                        ItemMeta bmeta = b.getItemMeta();
                        bmeta.setDisplayName(ChatColor.WHITE + "숙련자의 활");
                        b.setItemMeta(bmeta);
                        b.addEnchantment(Enchantment.ARROW_INFINITE, 1);
                        b.addEnchantment(Enchantment.MENDING, 1);
                        p.getInventory().addItem(b);
                        p.getInventory().addItem(new ItemStack(Material.ARROW));

                        ItemStack a = new ItemStack(Material.TIPPED_ARROW);
                        PotionMeta metaa = (PotionMeta) a.getItemMeta();
                        metaa.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 5, 3), true);
                        a.setItemMeta(metaa);
                        p.getInventory().addItem(a);

                        ItemStack skill = new ItemStack(Material.STICK);
                        ItemMeta meta = skill.getItemMeta();
                        meta.setDisplayName(ChatColor.GREEN + "연속 활발사");
                        skill.setItemMeta(meta);
                        p.getInventory().addItem(skill);

                    } else if(ClassSelectHandler.classSelectMap.get(p.getUniqueId().toString()) == "ninja") {
                        p.getInventory().addItem(new ItemStack(Material.NETHERITE_SWORD));
                        ItemStack skill = new ItemStack(Material.STICK);
                        ItemMeta meta = skill.getItemMeta();
                        meta.setDisplayName(ChatColor.GREEN + "대쉬");
                        skill.setItemMeta(meta);
                        p.getInventory().addItem(skill);
                    } else if(ClassSelectHandler.classSelectMap.get(p.getUniqueId().toString()) == "healer") {
                        ItemStack potion1 = new ItemStack(Material.SPLASH_POTION);
                        PotionMeta potion1Meta = (PotionMeta) potion1.getItemMeta();
                        potion1Meta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 1200, 3), true);
                        potion1.setItemMeta(potion1Meta);
                        ItemStack potion2 = new ItemStack(Material.LINGERING_POTION);
                        PotionMeta potion2Meta = (PotionMeta) potion2.getItemMeta();
                        potion2Meta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 3), true);
                        potion2.setItemMeta(potion2Meta);

                        ItemStack skill = new ItemStack(Material.STICK);
                        ItemMeta meta = skill.getItemMeta();
                        meta.setDisplayName(ChatColor.GREEN + "강한 심장");
                        skill.setItemMeta(meta);

                        p.getInventory().addItem(potion1);
                        p.getInventory().addItem(potion2);
                        p.getInventory().addItem(skill);
                    }
                }

                assert loc != null;
                p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 255));
                loc.setY(loc.getY() + 100);
                p.teleport(Objects.requireNonNull(loc));
            }

        }, 60L);

        return true;

    }
}
