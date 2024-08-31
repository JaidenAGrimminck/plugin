package rocks.jaiden.old.management;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class ServerProfile {

    private static ArrayList<ServerProfile> sessionPlayers = new ArrayList<ServerProfile>();

    private UUID uuid;
    private double firstJoin;

    public ServerProfile(Player player) {
        this.uuid = player.getUniqueId();
        this.firstJoin = System.currentTimeMillis();

        sessionPlayers.add(this);
    }

    public UUID getUUID() {
        return uuid;
    }

    public double firstJoined() {
        return firstJoin;
    }

    public static ServerProfile getProfile(Player player) {
        for (ServerProfile profile : sessionPlayers) {
            if (profile.getUUID().equals(player.getUniqueId())) {
                return profile;
            }
        }

        return new ServerProfile(player);
    }
}
