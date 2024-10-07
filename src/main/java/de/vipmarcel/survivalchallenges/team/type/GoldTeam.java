package de.vipmarcel.survivalchallenges.team.type;

import de.vipmarcel.survivalchallenges.SurvivalChallenges;
import de.vipmarcel.survivalchallenges.team.Team;
import org.bukkit.Color;
import org.bukkit.Material;

public class GoldTeam extends Team {

    public GoldTeam(SurvivalChallenges plugin) {
        super(plugin, "Gold", Color.fromRGB(255, 170, 0), Material.ORANGE_TERRACOTTA);
    }

}
