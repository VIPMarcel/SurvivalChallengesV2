package de.vipmarcel.survivalchallenges.challenge.type;

import de.vipmarcel.survivalchallenges.SurvivalChallenges;
import de.vipmarcel.survivalchallenges.challenge.Challenge;
import de.vipmarcel.survivalchallenges.challenge.ChallengeGoal;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class NoRegenerationChallenge extends Challenge {

    public NoRegenerationChallenge(SurvivalChallenges plugin) {
        super(plugin, "NoRegeneration", "If you loose live, you will never regain it.", ChallengeGoal.KILL_THE_ENDER_DRAGON);
    }

    @EventHandler
    public void onEntityDamageEvent(EntityRegainHealthEvent event) {
        if(event.getEntity() instanceof Player player) {
            if(this.containsTeam(this.getPlayerTeam(player.getUniqueId()))) {
                event.setCancelled(true);
            }
        }
    }

}
