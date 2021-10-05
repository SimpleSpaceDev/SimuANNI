package jp.simplespace.simuanni;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimuANNI extends JavaPlugin {
    private static Plugin plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        getServer().getPluginManager().registerEvents(new BlockListener(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        BlockListener.resetAllBlock();
    }
    public static Plugin getPlugin(){
        return plugin;
    }

}
