package de.vipmarcel.survivalchallenges.team.type;

import de.vipmarcel.survivalchallenges.SurvivalChallenges;
import de.vipmarcel.survivalchallenges.team.Team;
import org.bukkit.Color;
import org.bukkit.Material;

public class PurpleTeam extends Team {

    public PurpleTeam(SurvivalChallenges plugin) {
        super(plugin, "Purple", Color.fromRGB(255, 85, 255), Material.PINK_TERRACOTTA);
    }

}
