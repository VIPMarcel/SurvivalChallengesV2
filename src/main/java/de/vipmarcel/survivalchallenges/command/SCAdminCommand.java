package de.vipmarcel.survivalchallenges.command;

import de.vipmarcel.survivalchallenges.SurvivalChallenges;
import de.vipmarcel.survivalchallenges.utils.SCLogger;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SCAdminCommand implements CommandExecutor {

    private final SurvivalChallenges plugin;

    public SCAdminCommand(SurvivalChallenges plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] arguments) {

        if(commandSender instanceof Player player) {

            player.playSound(player.getLocation(), Sound.ITEM_GOAT_HORN_SOUND_0, 4.354889F, 8.796223F);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 5.2296549F, 1.299592F);
            player.openInventory(this.plugin.getInventoryUtil().getTeamsInventory());

        } else {
            SCLogger.warn("This command is only for players!");
        }

        return true;
    }

}
