package rocks.jaiden.old.game.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GameItem implements Listener {
    private NamespacedKey key;

    public enum Status {
        F,
        E,
        D,
        C,
        B,
        A,
        S,
    }

    private String identifier;
    private String name;

    private Status status;

    private Material itemType;

    public GameItem(String identifier, String name, Status status) {
        this.identifier = identifier;
        this.name = name;
        this.status = status;
        this.key = new NamespacedKey("game", identifier);
    }

    public ItemStack createInstance() {
        ItemStack item = new ItemStack(this.itemType);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(this.name);

        item.setItemMeta(meta);

        return item;
    }

    @EventHandler
    public void onItemDespawn(ItemDespawnEvent event) {
//        if (event) {
//            event.setCancelled(true);
//        }
    }
}
