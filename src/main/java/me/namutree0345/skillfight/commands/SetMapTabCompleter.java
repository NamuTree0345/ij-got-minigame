package me.namutree0345.skillfight.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetMapTabCompleter implements TabCompleter {

    ArrayList<String> getAL(String... items) {
        return new ArrayList<>(Arrays.asList(items));
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length == 1) {
            return getAL("mansion", "pillager_outpost");
        }
        return null;
    }
}
