package rocks.jaiden.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Hello world!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Goodbye world!");
    }
}