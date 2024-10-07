package de.vipmarcel.survivalchallenges.team.type;

import de.vipmarcel.survivalchallenges.SurvivalChallenges;
import de.vipmarcel.survivalchallenges.team.Team;
import org.bukkit.Color;
import org.bukkit.Material;

public class GrayTeam extends Team {

    public GrayTeam(SurvivalChallenges plugin) {
        super(plugin, "Gray", Color.fromRGB(170, 170, 170), Material.LIGHT_GRAY_TERRACOTTA);
    }

}
