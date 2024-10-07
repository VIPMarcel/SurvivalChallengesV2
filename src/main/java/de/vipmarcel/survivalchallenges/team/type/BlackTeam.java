package de.vipmarcel.survivalchallenges.team.type;

import de.vipmarcel.survivalchallenges.SurvivalChallenges;
import de.vipmarcel.survivalchallenges.team.Team;
import org.bukkit.Color;
import org.bukkit.Material;

public class BlackTeam extends Team {

    public BlackTeam(SurvivalChallenges plugin) {
        super(plugin, "Black", Color.fromRGB(0, 0, 0), Material.BLACK_TERRACOTTA);
    }

}
