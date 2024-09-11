package rocks.jaiden.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Plugin loaded!");

        //register events
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerRobotMonitor(), this);
    }

    @Override
    public void onDisable() {
        PlayerRobotMonitor.instance.client.close();

        getLogger().info("Shutting down!");
    }
}