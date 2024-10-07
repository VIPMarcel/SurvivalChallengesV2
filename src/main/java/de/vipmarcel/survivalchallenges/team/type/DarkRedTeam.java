package de.vipmarcel.survivalchallenges.team.type;

import de.vipmarcel.survivalchallenges.SurvivalChallenges;
import de.vipmarcel.survivalchallenges.team.Team;
import org.bukkit.Color;
import org.bukkit.Material;

public class DarkRedTeam extends Team {

    public DarkRedTeam(SurvivalChallenges plugin) {
        super(plugin, "DarkRed", Color.fromRGB(170, 0, 0), Material.RED_TERRACOTTA);
    }

}
