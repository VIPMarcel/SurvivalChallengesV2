package de.vipmarcel.survivalchallenges.team.type;

import de.vipmarcel.survivalchallenges.SurvivalChallenges;
import de.vipmarcel.survivalchallenges.team.Team;
import org.bukkit.Color;
import org.bukkit.Material;

public class DarkGreenTeam extends Team {

    public DarkGreenTeam(SurvivalChallenges plugin) {
        super(plugin, "DarkGreen", Color.fromRGB(0, 170, 0), Material.GREEN_TERRACOTTA);
    }

}
