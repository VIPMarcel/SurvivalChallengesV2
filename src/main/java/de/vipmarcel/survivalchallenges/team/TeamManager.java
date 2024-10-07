package de.vipmarcel.survivalchallenges.team;

import de.vipmarcel.survivalchallenges.SurvivalChallenges;
import de.vipmarcel.survivalchallenges.team.type.*;
import de.vipmarcel.survivalchallenges.utils.SCLogger;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TeamManager implements Listener, Runnable {

    private final SurvivalChallenges plugin;

    private final Map<Class<? extends Team>, Team> teamByClass = new HashMap<>();
    private final Map<String, Team> teamByName = new HashMap<>();

    private BukkitTask teamTask;

    public TeamManager(SurvivalChallenges plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        for(Team team : this.teamByClass.values()) {
            team.removePlayer(event.getPlayer().getUniqueId());
        }
    }

    @Override
    public void run() {}

    public void initialize() {
        this.registerTeam(AquaTeam.class, new AquaTeam(this.plugin));
        this.registerTeam(BlackTeam.class, new BlackTeam(this.plugin));
        this.registerTeam(BlueTeam.class, new BlueTeam(this.plugin));
        this.registerTeam(DarkAquaTeam.class, new DarkAquaTeam(this.plugin));
        this.registerTeam(DarkBlueTeam.class, new DarkBlueTeam(this.plugin));
        this.registerTeam(DarkGrayTeam.class, new DarkGrayTeam(this.plugin));
        this.registerTeam(DarkGreenTeam.class, new DarkGreenTeam(this.plugin));
        this.registerTeam(DarkPurpleTeam.class, new DarkPurpleTeam(this.plugin));
        this.registerTeam(DarkRedTeam.class, new DarkRedTeam(this.plugin));
        this.registerTeam(GoldTeam.class, new GoldTeam(this.plugin));
        this.registerTeam(GrayTeam.class, new GrayTeam(this.plugin));
        this.registerTeam(GreenTeam.class, new GreenTeam(this.plugin));
        this.registerTeam(PurpleTeam.class, new PurpleTeam(this.plugin));
        this.registerTeam(RedTeam.class, new RedTeam(this.plugin));
        this.registerTeam(WhiteTeam.class, new WhiteTeam(this.plugin));
        this.registerTeam(YellowTeam.class, new YellowTeam(this.plugin));

        for(Team team : this.teamByClass.values()) {
            try {
                team.enable();
            } catch(Exception e) {
                SCLogger.error("Error by initializing team " + team.getClass().getSimpleName(), e);
            }
        }

        if(this.teamTask == null) {
            this.teamTask = Bukkit.getScheduler().runTaskTimer(this.plugin, this, 20L, 20L);
        }
    }

    private <T extends Team> void registerTeam(Class<T> teamClass, T team) {
        if(!team.canInitialize()) {
            SCLogger.info("Team " + team.getClass().getSimpleName() + " cannot be initialized!");
            return;
        }
        if(this.teamByClass.putIfAbsent(teamClass, team) != null) {
            SCLogger.info("Team " + team.getClass().getSimpleName() + " is already registered!");
            return;
        }

        String teamName = team.getName().toLowerCase();

        if(this.teamByName.putIfAbsent(teamName, team) != null) {
            SCLogger.info("Team " + team.getClass().getSimpleName() + " is already registered!");
            return;
        }

        SCLogger.info("Team " + team.getClass().getSimpleName() + " has been initialized!");
    }

    public void shutdown() {
        for(Team team : this.teamByClass.values()) {
            try {
                team.disable();
            } catch(Exception e) {
                SCLogger.error("Error by disabling team " + team.getClass().getSimpleName(), e);
            }
        }

        this.teamByClass.clear();
        this.teamByName.clear();

        if(this.teamTask != null) {
            this.teamTask.cancel();
            this.teamTask = null;
        }

    }

    @SuppressWarnings("unchecked")
    public <T extends Team> T getTeam(String teamName) {
        Team team = this.teamByName.get(teamName.toLowerCase());
        return team != null ? (T) team : null;
    }

    public <T extends Team> T getTeam(Class<T> teamClass) {
        Team team = this.teamByClass.get(teamClass);
        return team != null ? teamClass.cast(team) : null;
    }

    public boolean isRegistered(String teamName) {
        return this.teamByName.containsKey(teamName.toLowerCase());
    }

    public boolean isRegistered(Class<?> teamClass) {
        return this.teamByClass.containsKey(teamClass);
    }

    public Collection<Team> getTeamList() {
        return Collections.unmodifiableCollection(this.teamByClass.values());
    }

}
