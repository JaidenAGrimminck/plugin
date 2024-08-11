package rocks.jaiden.plugin.game.player;

import rocks.jaiden.plugin.management.ServerProfile;

public class GameProfile extends Person {
    private ServerProfile parent;

    public GameProfile(ServerProfile parent) {
        this.parent = parent;
    }
}
