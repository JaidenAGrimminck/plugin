package rocks.jaiden.old.game.player;

import rocks.jaiden.old.game.items.GameItem;

import java.util.ArrayList;

public class GameInventory {
    private int maxHoldable;
    private int maxWeight;

    private ArrayList<GameItem> items;

    public GameInventory() {
        this.maxHoldable = 10;
        this.maxWeight = 100;
    }

}
