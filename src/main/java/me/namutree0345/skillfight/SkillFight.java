package me.namutree0345.skillfight;

import me.namutree0345.skillfight.commands.ClassInput;
import me.namutree0345.skillfight.commands.SetMap;
import me.namutree0345.skillfight.commands.SetMapTabCompleter;
import me.namutree0345.skillfight.commands.StartGame;
import me.namutree0345.skillfight.events.ClassSelectHandler;
import me.namutree0345.skillfight.events.SkillItemHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class SkillFight extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("cc").setExecutor(new ClassInput());
        getCommand("start").setExecutor(new StartGame(this));
        PluginCommand setMap = getCommand("sm");
        assert setMap != null;
        setMap.setExecutor(new SetMap());
        setMap.setTabCompleter(new SetMapTabCompleter());
        Bukkit.getServer().getPluginManager().registerEvents(new SkillItemHandler(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ClassSelectHandler(), this);
    }
}
