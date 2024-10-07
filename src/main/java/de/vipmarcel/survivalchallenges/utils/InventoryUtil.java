package de.vipmarcel.survivalchallenges.utils;

import de.vipmarcel.survivalchallenges.SurvivalChallenges;
import de.vipmarcel.survivalchallenges.team.Team;
import de.vipmarcel.survivalchallenges.team.type.*;
import de.vipmarcel.survivalchallenges.utils.builder.ItemBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

public class InventoryUtil {

    private final SurvivalChallenges plugin;

    public InventoryUtil(final SurvivalChallenges plugin) {
        this.plugin = plugin;
    }

    public Inventory getTeamsInventory() {
        Inventory inventory = Bukkit.createInventory(null, 54, Component.text("§6§oChallenges§r §8⮫ §7Choose the team!"));

        for(int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName(" ").build());
        }

            /*
                Line                x | o | o | o
             */
        this.setTeamItem(inventory, 9+1, WhiteTeam.class);
        this.setTeamItem(inventory, 9+9+1, GrayTeam.class);
        this.setTeamItem(inventory, 9+9+9+1, DarkGrayTeam.class);
        this.setTeamItem(inventory, 9+9+9+9+1, BlackTeam.class);

            /*
                Line                o | x | o | o
             */
        this.setTeamItem(inventory, 9+3, YellowTeam.class);
        this.setTeamItem(inventory, 9+9+3, GoldTeam.class);
        this.setTeamItem(inventory, 9+9+9+3, RedTeam.class);
        this.setTeamItem(inventory, 9+9+9+9+3, DarkRedTeam.class);

            /*
                Line                o | o | x | o
             */
        this.setTeamItem(inventory, 9+5, BlueTeam.class);
        this.setTeamItem(inventory, 9+9+5, DarkBlueTeam.class);
        this.setTeamItem(inventory, 9+9+9+5, AquaTeam.class);
        this.setTeamItem(inventory, 9+9+9+9+5, DarkAquaTeam.class);

            /*
                Line                o | o | o | x
             */
        this.setTeamItem(inventory, 9+7, PurpleTeam.class);
        this.setTeamItem(inventory, 9+9+7, DarkPurpleTeam.class);
        this.setTeamItem(inventory, 9+9+9+7, GreenTeam.class);
        this.setTeamItem(inventory, 9+9+9+9+7, DarkGreenTeam.class);

        return inventory;
    }

    private void setTeamItem(Inventory inventory, int slot, Class<? extends Team> teamClass) {
        Team team = this.plugin.getTeamManager().getTeam(teamClass);
        String[] lore = team.getActivePlayers().stream()
                .map(uuid -> "§8» §7" + Bukkit.getOfflinePlayer(uuid).getName())
                .toArray(String[]::new);

        inventory.setItem(slot, new ItemBuilder(team.getTeamMaterial())
                .setTeamName(team.getName(), team.getTeamColor())
                .setLore(lore)
                .build());
    }

}
