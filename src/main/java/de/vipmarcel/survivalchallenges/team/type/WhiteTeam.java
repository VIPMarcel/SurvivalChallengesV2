package de.vipmarcel.survivalchallenges.team.type;

import de.vipmarcel.survivalchallenges.SurvivalChallenges;
import de.vipmarcel.survivalchallenges.team.Team;
import org.bukkit.Color;
import org.bukkit.Material;

public class WhiteTeam extends Team {

    public WhiteTeam(SurvivalChallenges plugin) {
        super(plugin, "White", Color.fromRGB(255, 255, 255), Material.WHITE_TERRACOTTA);
    }

}
