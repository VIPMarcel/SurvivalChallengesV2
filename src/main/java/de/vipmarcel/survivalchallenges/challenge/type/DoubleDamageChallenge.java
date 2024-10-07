package de.vipmarcel.survivalchallenges.challenge.type;

import de.vipmarcel.survivalchallenges.SurvivalChallenges;
import de.vipmarcel.survivalchallenges.challenge.Challenge;
import de.vipmarcel.survivalchallenges.challenge.ChallengeGoal;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

public class DoubleDamageChallenge extends Challenge {

    public DoubleDamageChallenge(SurvivalChallenges plugin) {
        super(plugin, "DoubleDamage", "If you get damage, it multiplies with 2x.", ChallengeGoal.KILL_THE_ENDER_DRAGON);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player player) {
            if(this.containsTeam(this.getPlayerTeam(player.getUniqueId()))) {
                event.setDamage(event.getDamage() * 2);
            }
        }
    }

}
