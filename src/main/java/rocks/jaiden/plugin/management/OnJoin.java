package rocks.jaiden.plugin.management;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        ServerProfile playerProfile = ServerProfile.getProfile(event.getPlayer());

        boolean newPlayer = playerProfile.firstJoined() - System.currentTimeMillis() < 1000;

        if (newPlayer) {
            event.getPlayer().sendMessage("Welcome to the server!");
        } else {
            event.getPlayer().sendMessage("Welcome back!");
        }
    }
}
