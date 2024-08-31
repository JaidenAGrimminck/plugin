package rocks.jaiden.old.game.economy;

import rocks.jaiden.old.game.items.GameItem;

public class Economy {
    //the backing of the economy, i.e. what you can use the currency to exchange for
    private GameItem backing;

    //how much of the currency is needed to get one backing
    private double currencyToBacking;

    //how much of the currency is in circulation
    private double inCirculation;

    //how much of the currency is stored by banks/in reserve/etc (the government)
    private double stored;

    public Economy(GameItem backing, double currencyToBacking, double inCirculation, double stored) {
        this.backing = backing;
        this.currencyToBacking = currencyToBacking;
        this.inCirculation = inCirculation;
        this.stored = stored;
    }
}
