package me.namutree0345.skillfight;

import me.namutree0345.skillfight.commands.ClassInput;
import me.namutree0345.skillfight.events.SkillItemHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SkillFight extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("cc").setExecutor(new ClassInput());
        Bukkit.getServer().getPluginManager().registerEvents(new SkillItemHandler(this), this);
    }
}
