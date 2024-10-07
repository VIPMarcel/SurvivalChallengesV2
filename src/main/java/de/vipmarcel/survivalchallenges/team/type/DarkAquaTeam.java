package de.vipmarcel.survivalchallenges.team.type;

import de.vipmarcel.survivalchallenges.SurvivalChallenges;
import de.vipmarcel.survivalchallenges.team.Team;
import org.bukkit.Color;
import org.bukkit.Material;

public class DarkAquaTeam extends Team {

    public DarkAquaTeam(SurvivalChallenges plugin) {
        super(plugin, "DarkAqua", Color.fromRGB(0, 170, 170), Material.CYAN_CONCRETE);
    }

}
