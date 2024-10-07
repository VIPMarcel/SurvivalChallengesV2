package de.vipmarcel.survivalchallenges.team.type;

import de.vipmarcel.survivalchallenges.SurvivalChallenges;
import de.vipmarcel.survivalchallenges.team.Team;
import org.bukkit.Color;
import org.bukkit.Material;

public class RedTeam extends Team {

    public RedTeam(SurvivalChallenges plugin) {
        super(plugin, "Red", Color.fromRGB(255, 85, 85), Material.TERRACOTTA);
    }

}
