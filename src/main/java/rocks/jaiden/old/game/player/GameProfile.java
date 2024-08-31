package rocks.jaiden.old.game.player;

import rocks.jaiden.old.management.ServerProfile;

public class GameProfile extends Person {
    private ServerProfile parent;

    public GameProfile(ServerProfile parent) {
        this.parent = parent;
    }
}
