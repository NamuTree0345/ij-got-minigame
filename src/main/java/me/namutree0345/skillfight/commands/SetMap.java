package me.namutree0345.skillfight.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.StructureType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class SetMap implements CommandExecutor {

    public static StructureType gameMapType = StructureType.WOODLAND_MANSION;



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "콘솔이나 커맨드 블럭에서는 사용하실 수 없습니다.");
            return true;
        } else {
            if(args.length != 1) {
                sender.sendMessage("올바른 사용법: /sm <종류(mansion/pillager_outpost)>");
                return true;
            } else {
                if(StructureType.getStructureTypes().get(args[0]) == null) {
                    sender.sendMessage(ChatColor.RED + "올바르지 않은 맵 입니다!");
                    return true;
                }
                gameMapType = StructureType.getStructureTypes().get(args[0]);
                sender.sendMessage(ChatColor.GREEN + "맵이 " + ChatColor.GOLD + args[0] + ChatColor.GREEN + "으로 설정되었습니다.");
                return true;
            }
        }
    }
}
