package de.vipmarcel.survivalchallenges.challenge.type;

import de.vipmarcel.survivalchallenges.SurvivalChallenges;
import de.vipmarcel.survivalchallenges.challenge.Challenge;
import de.vipmarcel.survivalchallenges.challenge.ChallengeGoal;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class RandomItemDropChallenge extends Challenge {

    public RandomItemDropChallenge(SurvivalChallenges plugin) {
        super(plugin, "RandomItemDrop", "Every time an item drops it is an random item.", ChallengeGoal.KILL_THE_ENDER_DRAGON);
    }

    //TODO: complete challenge handling

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {

    }

    @EventHandler
    public void onBlockBreakEvent(EntityExplodeEvent event) {

    }

    @EventHandler
    public void onBlockBreakEvent(EntityDeathEvent event) {

    }

}
