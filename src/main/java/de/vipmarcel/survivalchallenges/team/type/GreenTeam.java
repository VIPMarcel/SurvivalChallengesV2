package de.vipmarcel.survivalchallenges.team.type;

import de.vipmarcel.survivalchallenges.SurvivalChallenges;
import de.vipmarcel.survivalchallenges.team.Team;
import org.bukkit.Color;
import org.bukkit.Material;

public class GreenTeam extends Team {

    public GreenTeam(SurvivalChallenges plugin) {
        super(plugin, "Green", Color.fromRGB(85, 255, 85), Material.GREEN_TERRACOTTA);
    }

}
