package xyz.regulad.nocrystallootdestruction;

import lombok.Getter;
import org.bstats.bukkit.Metrics;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class NoCrystalLootDestruction extends JavaPlugin implements Listener {
    @Getter
    private static NoCrystalLootDestruction instance;
    @Getter
    private Metrics metrics;

    @EventHandler
    public void onItemHurt(final @NotNull EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.getDamager().getType().equals(EntityType.ENDER_CRYSTAL) && entityDamageByEntityEvent.getEntity().getType().equals(EntityType.DROPPED_ITEM)) {
            entityDamageByEntityEvent.setCancelled(true);
        }
    }

    @Override
    public void onEnable() {
        // Setup instance access
        instance = this;
        // Setup Metrics
        this.metrics = new Metrics(this, 14725);
    }

    @Override
    public void onDisable() {
        // Unload instance
        instance = null;
        // Let metrics garbage collect
        this.metrics = null;
    }
}
