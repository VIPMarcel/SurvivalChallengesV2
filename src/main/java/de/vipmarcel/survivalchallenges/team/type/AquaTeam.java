package de.vipmarcel.survivalchallenges.team.type;

import de.vipmarcel.survivalchallenges.SurvivalChallenges;
import de.vipmarcel.survivalchallenges.team.Team;
import org.bukkit.Color;
import org.bukkit.Material;

public class AquaTeam extends Team {

    public AquaTeam(SurvivalChallenges plugin) {
        super(plugin, "Aqua", Color.fromRGB(85, 255, 255), Material.CYAN_WOOL);
    }

}
