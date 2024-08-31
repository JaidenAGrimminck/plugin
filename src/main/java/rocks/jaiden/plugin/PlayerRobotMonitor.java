package rocks.jaiden.plugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.net.URI;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class PlayerRobotMonitor implements Listener {

    WSClient client;
    public static UUID playerUUID;

    private Timer updateTimer;
    private TimerTask updateTask;

    private double lastX = 0;
    private double lastY = 0;

    private long lastUpdate = 0;

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();

        if (message.startsWith("connect")) {
            if (playerUUID == null) {
                playerUUID = event.getPlayer().getUniqueId();

                String ip = "localhost";

                if (message.split(" ").length > 1) {
                    String arg = message.split(" ")[1];
                    ip = arg;
                }

                String uri = "ws://192.168.43.1:23015";
                event.getPlayer().sendMessage("[server] connecting to " + uri);

                client = new WSClient();

                try {
                    client.connect(uri);
                } catch (Exception e) {
                    e.printStackTrace();
                    event.getPlayer().sendMessage("[server] failed to connect to " + uri);
                    return;
                }

                lastX = event.getPlayer().getLocation().getX();
                lastY = event.getPlayer().getLocation().getZ();
                lastUpdate = System.currentTimeMillis();

                final WSClient thisListener = this.client;

                updateTask = new TimerTask() {
                    @Override
                    public void run() {
                        if (playerUUID != null) {
                            Player player = Bukkit.getPlayer(playerUUID);

                            if (player == null || !player.isOnline()) {
                                playerUUID = null;
                                return;
                            }

                            try {
                                Location location = player.getLocation();
                                thisListener.sendLocation(
                                        location.getX(), location.getZ(), System.currentTimeMillis(),
                                        lastX, lastY, lastUpdate,
                                        location.getYaw()
                                );
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            lastX = player.getLocation().getX();
                            lastY = player.getLocation().getZ();
                            lastUpdate = System.currentTimeMillis();
                        }
                    }
                };

                updateTimer = new Timer();
                updateTimer.schedule(updateTask, 0, 50);
            }
        } else if (message.equalsIgnoreCase("stop") && playerUUID != null) {
            try {
                playerUUID = null;
                client.stopRobot();
                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(1000);
                        client.close();
                        updateTimer.cancel();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                thread.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            event.getPlayer().sendMessage(message);
        }
    }
}
