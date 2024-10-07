package de.vipmarcel.survivalchallenges.challenge;

import de.vipmarcel.survivalchallenges.SurvivalChallenges;
import de.vipmarcel.survivalchallenges.team.Team;
import de.vipmarcel.survivalchallenges.utils.SCLogger;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Challenge implements Listener {

    private final SurvivalChallenges plugin;

    private final String name;
    private final String description;
    private final ChallengeGoal challengeGoal;

    private boolean enabled = false;

    private final Set<Team> activeTeams = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private final Map<Team, Long> activeTeamsExpire = new ConcurrentHashMap<>();

    public Challenge(SurvivalChallenges plugin, String name, String description, ChallengeGoal challengeGoal) {
        this.plugin = plugin;
        this.name = name;
        this.description = description;
        this.challengeGoal = challengeGoal;
    }

    protected void onEnable() {};
    protected void onDisable() {};

    protected void onTeamEnter(Team team) {};
    protected void onTeamLeave(Team team) {};

    protected boolean canInitialize() { return true; };

    void checkExpiredTeams() {
        long currentTimeMillis = System.currentTimeMillis();

        for(Iterator<Map.Entry<Team, Long>> iterator = this.activeTeamsExpire.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry<Team, Long> entry = iterator.next();

            if(entry.getValue() < currentTimeMillis) {
                iterator.remove();

                this.removeTeam(entry.getKey());
            }
        }
    }

    public boolean enable() {
        if(this.isEnabled()) {
            return false;
        }

        this.onEnable();
        this.enabled = true;

        Bukkit.getServer().getPluginManager().registerEvents(this, this.plugin);
        SCLogger.info("Enabled Challenge " + this.getName());
        return true;
    }

    public boolean disable() {
        if(!this.isEnabled()) {
            return false;
        }

        this.activeTeams.forEach(this::removeTeam);

        this.activeTeams.clear();
        this.activeTeamsExpire.clear();

        this.enabled = false;
        HandlerList.unregisterAll(this);
        this.onDisable();

        SCLogger.info("Disabled Challenge " + this.getName());
        return true;
    }

    public void addTeam(long expireTime, Team... teams) {
        if(!this.isEnabled()) {
            return;
        }

        long currentTimeMillis = System.currentTimeMillis();

        for(Team team : teams) {
            this.activeTeams.add(team);

            if(expireTime > 0) {
                long currentExpireTime = this.activeTeamsExpire.getOrDefault(team, currentTimeMillis);

                currentExpireTime += expireTime;
                this.activeTeamsExpire.put(team, currentExpireTime);
            } else {
                this.activeTeamsExpire.remove(team);
            }

            this.onTeamEnter(team);
        }
    }

    public void removeTeam(Team... teams) {
        if(!this.isEnabled()) {
            return;
        }

        for(Team team : teams) {
            if(this.activeTeams.contains(team)) {
                this.activeTeams.remove(team);
                this.activeTeamsExpire.remove(team);
                this.onTeamLeave(team);
            }
        }
    }

    public boolean containsTeam(Team team) {
        return this.isEnabled() && team != null && this.activeTeams.contains(team);
    }

    public Team getPlayerTeam(UUID uuid) {
        for(Team team : this.getActiveTeams()) {
            if(team.containsPlayer(uuid)) {
                return team;
            }
        }

        return null;
    }

    public Set<Team> getActiveTeams() {
        return Collections.unmodifiableSet(this.activeTeams);
    }

    public SurvivalChallenges getPlugin() {
        return this.plugin;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public ChallengeGoal getChallengeGoal() {
        return this.challengeGoal;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

}
