package me.namutree0345.skillfight.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class ClassInput implements CommandExecutor {

    ArrayList<String> getAL(String... items) {
        return new ArrayList<>(Arrays.asList(items));
    }

    ItemStack getItemStack(Material mat, String classname, String skill, String skillDescription) {
        ItemStack itemStack = new ItemStack(mat);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + classname);
        /*
        모루노루
        =========================
        - 스킬
        ㅁㄴㅇㄹ
        재미있는 폭탄을 소환합니다.
        */
        meta.setLore(getAL(ChatColor.DARK_GRAY + "=======================", ChatColor.WHITE + "- 스킬", ChatColor.GREEN + skill, ChatColor.LIGHT_PURPLE + skillDescription));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "콘솔이나 커맨드 블럭에서는 사용하실 수 없습니다.");
            return true;
        } else {
            Inventory inv = Bukkit.createInventory(null, 27, "클래스 선택");
            inv.setItem(10, getItemStack(Material.DIAMOND_SWORD, "검수", "???", "아직 없음"));
            inv.setItem(12, getItemStack(Material.BOW, "궁수", "연속 활발사", "연속으로 화살을 발사합니다. 3초 지속됩니다."));
            inv.setItem(14, getItemStack(Material.STICK, "닌자", "대쉬", "5칸 앞으로 빠르게 이동합니다. 이동과 동시에 좌클릭하면 공격도 같이 됩니다."));
            inv.setItem(16, getItemStack(Material.HEART_OF_THE_SEA, "힐러", "강한 심장", "50블럭 이내의 플레이어에 재생 효과를 줍니다. 본인도 효과를 가집니다. 20초 지속됩니다."));
            ((Player) sender).openInventory(inv);
        }
         return true;
    }
}
