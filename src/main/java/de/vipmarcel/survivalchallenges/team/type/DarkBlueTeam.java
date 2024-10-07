package de.vipmarcel.survivalchallenges.team.type;

import de.vipmarcel.survivalchallenges.SurvivalChallenges;
import de.vipmarcel.survivalchallenges.team.Team;
import org.bukkit.Color;
import org.bukkit.Material;

public class DarkBlueTeam extends Team {

    public DarkBlueTeam(SurvivalChallenges plugin) {
        super(plugin, "DarkBlue", Color.fromRGB(0, 0, 170), Material.BLUE_TERRACOTTA);
    }

}
