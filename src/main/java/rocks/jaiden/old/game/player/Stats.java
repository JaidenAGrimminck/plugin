package rocks.jaiden.old.game.player;

import java.util.ArrayList;
import java.util.HashMap;

public class Stats {
    enum AdultRace {
        Human(10, 10, 10, 10),
        Elf(8, 8, 12, 12),
        Dwarf(12, 12, 8, 8);

        private int endurance;
        private int strength;
        private int agility;
        private int intelligence;

        AdultRace(int endurance, int strength, int agility, int intelligence) {
            this.endurance = endurance;
            this.strength = strength;
            this.agility = agility;
            this.intelligence = intelligence;
        }

        public Stats create() {
            return new Stats(endurance, strength, agility, intelligence);
        }
    }

    private HashMap<String, Stat> stats;
    private ArrayList<StatModifier> modifiers;

    public Stats(int endurance, int strength, int agility, int intelligence) {
        stats = new HashMap<>();
        modifiers = new ArrayList<>();

        stats.put("endurance", new Stat("endurance", endurance));
        stats.put("strength", new Stat("strength", strength));
        stats.put("agility", new Stat("agility", agility));
        stats.put("intelligence", new Stat("intelligence", intelligence));
    }

    public class Stat {
        private int value;
        private String name;

        public Stat(String name) {
            this.name = name;
            this.value = 0;
        }

        public Stat(String name, int value) {
            this.name = name;
            this.value = value;
        }

    }

    public class StatModifier {
        private boolean temporary = false;
        private int expiration = 0;

        private int addition;
        private int multiplier;

        public StatModifier(int addition, int multiplier) {
            this.addition = addition;
            this.multiplier = multiplier;
        }

        public StatModifier setTemporary(int expiration) {
            this.temporary = true;
            this.expiration = expiration;
            return this;
        }
    }
}
