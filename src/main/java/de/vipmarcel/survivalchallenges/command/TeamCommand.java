package de.vipmarcel.survivalchallenges.command;

import de.vipmarcel.survivalchallenges.SurvivalChallenges;
import de.vipmarcel.survivalchallenges.team.Team;
import de.vipmarcel.survivalchallenges.team.type.*;
import de.vipmarcel.survivalchallenges.utils.SCLogger;
import de.vipmarcel.survivalchallenges.utils.builder.ItemBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class TeamCommand implements CommandExecutor {

    private final SurvivalChallenges plugin;

    public TeamCommand(SurvivalChallenges plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] arguments) {

        if(commandSender instanceof Player player) {

            player.playSound(player.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 4.354889F, 8.796223F);
            player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 5.2296549F, 1.299592F);
            player.openInventory(this.plugin.getInventoryUtil().getTeamsInventory());

        } else {
            SCLogger.warn("This command is only for players!");
        }

        return true;
    }

}
