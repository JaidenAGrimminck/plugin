package rocks.jaiden.plugin.game.player;

import java.util.UUID;

public class Person {
    private UUID personalUUID;
    private GameInventory inventory;

    public Person() {
        personalUUID = UUID.randomUUID();
        inventory = new GameInventory();
    }

    public UUID getPersonalUUID() {
        return personalUUID;
    }

    public GameInventory getInventory() {
        return inventory;
    }
}
