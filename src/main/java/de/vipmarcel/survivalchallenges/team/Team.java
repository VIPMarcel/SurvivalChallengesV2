package de.vipmarcel.survivalchallenges.team;

import de.vipmarcel.survivalchallenges.SurvivalChallenges;
import de.vipmarcel.survivalchallenges.utils.SCLogger;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Team implements Listener {

    private final SurvivalChallenges plugin;

    private final String name;
    private final Color teamColor;
    private final Material teamMaterial;

    private boolean enabled = false;

    private final Set<UUID> activePlayers = Collections.newSetFromMap(new ConcurrentHashMap<>());

    public Team(SurvivalChallenges plugin, String name, Color teamColor, Material teamMaterial) {
        this.plugin = plugin;
        this.name = name;
        this.teamColor = teamColor;
        this.teamMaterial = teamMaterial;
    }

    protected void onEnable() {};
    protected void onDisable() {};

    protected void onPlayerEnter(UUID uuid) {};
    protected void onPlayerLeave(UUID uuid) {};

    protected boolean canInitialize() { return true; };

    public boolean enable() {
        if(this.isEnabled()) {
            return false;
        }

        this.onEnable();
        this.enabled = true;

        Bukkit.getServer().getPluginManager().registerEvents(this, this.plugin);
        SCLogger.info("Enabled Team " + this.getName());
        return true;
    }

    public boolean disable() {
        if(!this.isEnabled()) {
            return false;
        }

        this.activePlayers.forEach(this::removePlayer);

        this.activePlayers.clear();

        this.enabled = false;
        HandlerList.unregisterAll(this);
        this.onDisable();

        SCLogger.info("Disabled Team " + this.getName());
        return true;
    }

    public void addPlayer(long expireTime, UUID... uuids) {
        if(!this.isEnabled()) {
            return;
        }

        for(UUID uuid : uuids) {
            this.activePlayers.add(uuid);
            this.onPlayerEnter(uuid);
        }
    }

    public void removePlayer(UUID... uuids) {
        if(!this.isEnabled()) {
            return;
        }

        for(UUID uuid : uuids) {
            if(this.activePlayers.contains(uuid)) {
                this.activePlayers.remove(uuid);
                this.onPlayerLeave(uuid);
            }
        }
    }

    public boolean containsPlayer(UUID uuid) {
        return this.isEnabled() && uuid != null && this.activePlayers.contains(uuid);
    }

    public Set<UUID> getActivePlayers() {
        return Collections.unmodifiableSet(this.activePlayers);
    }

    public SurvivalChallenges getPlugin() {
        return this.plugin;
    }

    public String getName() {
        return this.name;
    }

    public Color getTeamColor() {
        return this.teamColor;
    }

    public Material getTeamMaterial() {
        return this.teamMaterial;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

}
