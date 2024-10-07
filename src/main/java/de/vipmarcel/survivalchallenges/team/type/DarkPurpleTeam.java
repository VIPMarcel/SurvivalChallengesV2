package de.vipmarcel.survivalchallenges.team.type;

import de.vipmarcel.survivalchallenges.SurvivalChallenges;
import de.vipmarcel.survivalchallenges.team.Team;
import org.bukkit.Color;
import org.bukkit.Material;

public class DarkPurpleTeam extends Team {

    public DarkPurpleTeam(SurvivalChallenges plugin) {
        super(plugin, "DarkPurple", Color.fromRGB(170, 0, 170), Material.PURPLE_TERRACOTTA);
    }

}
