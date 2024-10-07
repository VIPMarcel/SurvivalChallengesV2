package de.vipmarcel.survivalchallenges.team.type;

import de.vipmarcel.survivalchallenges.SurvivalChallenges;
import de.vipmarcel.survivalchallenges.team.Team;
import org.bukkit.Color;
import org.bukkit.Material;

public class BlueTeam extends Team {

    public BlueTeam(SurvivalChallenges plugin) {
        super(plugin, "Blue", Color.fromRGB(85, 85, 255), Material.LIGHT_BLUE_TERRACOTTA);
    }

}
