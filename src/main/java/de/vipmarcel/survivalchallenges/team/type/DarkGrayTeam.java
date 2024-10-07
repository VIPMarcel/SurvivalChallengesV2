package de.vipmarcel.survivalchallenges.team.type;

import de.vipmarcel.survivalchallenges.SurvivalChallenges;
import de.vipmarcel.survivalchallenges.team.Team;
import org.bukkit.Color;
import org.bukkit.Material;

public class DarkGrayTeam extends Team {

    public DarkGrayTeam(SurvivalChallenges plugin) {
        super(plugin, "DarkGray", Color.fromRGB(85, 85, 85), Material.GRAY_TERRACOTTA);
    }

}
