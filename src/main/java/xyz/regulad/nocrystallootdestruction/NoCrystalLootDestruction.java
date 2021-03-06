package xyz.regulad.nocrystallootdestruction;

import lombok.Getter;
import org.bstats.bukkit.Metrics;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class NoCrystalLootDestruction extends JavaPlugin implements Listener {
    @Getter
    private static NoCrystalLootDestruction instance;
    @Getter
    private Metrics metrics;

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onItemHurt(final @NotNull EntityDamageByEntityEvent damageEvent) {
        if (damageEvent.getDamager() instanceof EnderCrystal && damageEvent.getEntity() instanceof Item) {
            damageEvent.setCancelled(true);
        }
    }

    @Override
    public void onEnable() {
        // Setup instance access
        instance = this;
        // Setup Metrics
        this.metrics = new Metrics(this, 14725);
        // Register events
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Unload instance
        instance = null;
        // Let metrics garbage collect
        this.metrics = null;
    }
}
