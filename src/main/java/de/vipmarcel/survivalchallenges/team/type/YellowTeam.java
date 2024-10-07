package de.vipmarcel.survivalchallenges.team.type;

import de.vipmarcel.survivalchallenges.SurvivalChallenges;
import de.vipmarcel.survivalchallenges.team.Team;
import org.bukkit.Color;
import org.bukkit.Material;

public class YellowTeam extends Team {

    public YellowTeam(SurvivalChallenges plugin) {
        super(plugin, "Yellow", Color.fromRGB(255, 255, 85), Material.YELLOW_TERRACOTTA);
    }

}
