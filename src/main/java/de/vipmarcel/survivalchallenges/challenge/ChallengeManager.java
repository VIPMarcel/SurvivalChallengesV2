package de.vipmarcel.survivalchallenges.challenge;

import de.vipmarcel.survivalchallenges.SurvivalChallenges;
import de.vipmarcel.survivalchallenges.challenge.type.DoubleDamageChallenge;
import de.vipmarcel.survivalchallenges.challenge.type.NoRegenerationChallenge;
import de.vipmarcel.survivalchallenges.challenge.type.RandomItemDropChallenge;
import de.vipmarcel.survivalchallenges.team.Team;
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

public class ChallengeManager implements Listener, Runnable {

    private final SurvivalChallenges plugin;

    private final Map<Class<? extends Challenge>, Challenge> challengeByClass = new HashMap<>();
    private final Map<String, Challenge> challengeByName = new HashMap<>();

    private BukkitTask challengeTask;

    public ChallengeManager(SurvivalChallenges plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for(Challenge challenge : this.challengeByClass.values()) {
            if(challenge.isEnabled()) {
                challenge.checkExpiredTeams();
            }
        }

    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        for(Challenge challenge : this.challengeByClass.values()) {
            for (Team activeTeam : challenge.getActiveTeams()) {
                if(activeTeam.getActivePlayers().contains(event.getPlayer().getUniqueId())) {
                    activeTeam.removePlayer(event.getPlayer().getUniqueId());

                    if(challenge.getActiveTeams().isEmpty()) {
                        challenge.removeTeam(activeTeam);
                    }
                }
            }
        }
    }

    public void initialize() {
        this.registerChallenge(NoRegenerationChallenge.class, new NoRegenerationChallenge(this.plugin));
        this.registerChallenge(DoubleDamageChallenge.class, new DoubleDamageChallenge(this.plugin));
        this.registerChallenge(RandomItemDropChallenge.class, new RandomItemDropChallenge(this.plugin));

        for(Challenge challenge : this.challengeByClass.values()) {
            try {
                challenge.enable();
            } catch(Exception e) {
                SCLogger.error("Error by initializing challenge " + challenge.getClass().getSimpleName(), e);
            }
        }

        if(this.challengeTask == null) {
            this.challengeTask = Bukkit.getScheduler().runTaskTimer(this.plugin, this, 20L, 20L);
        }
    }

    private <T extends Challenge> void registerChallenge(Class<T> challengeClass, T challenge) {
        if(!challenge.canInitialize()) {
            SCLogger.info("Challenge " + challenge.getClass().getSimpleName() + " cannot be initialized!");
            return;
        }
        if(this.challengeByClass.putIfAbsent(challengeClass, challenge) != null) {
            SCLogger.info("Challenge " + challenge.getClass().getSimpleName() + " is already registered!");
            return;
        }

        String challengeName = challenge.getName().toLowerCase();

        if(this.challengeByName.putIfAbsent(challengeName, challenge) != null) {
            SCLogger.info("Challenge " + challenge.getClass().getSimpleName() + " is already registered!");
            return;
        }

        SCLogger.info("Challenge " + challenge.getClass().getSimpleName() + " has been initialized!");
    }

    public void shutdown() {
        for(Challenge challenge : this.challengeByClass.values()) {
            try {
                challenge.disable();
            } catch(Exception e) {
                SCLogger.error("Error by disabling challenge " + challenge.getClass().getSimpleName(), e);
            }
        }

        this.challengeByClass.clear();
        this.challengeByName.clear();

        if(this.challengeTask != null) {
            this.challengeTask.cancel();
            this.challengeTask = null;
        }

    }

    @SuppressWarnings("unchecked")
    public <T extends Challenge> T getChallenge(String challengeName) {
        Challenge challenge = this.challengeByName.get(challengeName.toLowerCase());
        return challenge != null ? (T) challenge : null;
    }

    public <T extends Challenge> T getChallenge(Class<T> challengeClass) {
        Challenge challenge = this.challengeByClass.get(challengeClass);
        return challenge != null ? challengeClass.cast(challenge) : null;
    }

    public boolean isRegistered(String challengeName) {
        return this.challengeByName.containsKey(challengeName.toLowerCase());
    }

    public boolean isRegistered(Class<?> challengeClass) {
        return this.challengeByClass.containsKey(challengeClass);
    }

    public Collection<Challenge> getChallengeList() {
        return Collections.unmodifiableCollection(this.challengeByClass.values());
    }

}
