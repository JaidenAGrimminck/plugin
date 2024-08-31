package rocks.jaiden.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Hello world!");

        //register events
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerRobotMonitor(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Goodbye world!");
    }
}