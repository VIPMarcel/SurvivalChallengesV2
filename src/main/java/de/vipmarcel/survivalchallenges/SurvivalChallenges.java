package de.vipmarcel.survivalchallenges;

import de.vipmarcel.survivalchallenges.challenge.ChallengeManager;
import de.vipmarcel.survivalchallenges.command.SCAdminCommand;
import de.vipmarcel.survivalchallenges.command.TeamCommand;
import de.vipmarcel.survivalchallenges.team.TeamManager;
import de.vipmarcel.survivalchallenges.utils.InventoryUtil;
import de.vipmarcel.survivalchallenges.utils.SCLogger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SurvivalChallenges extends JavaPlugin {

    private ChallengeManager challengeManager;
    private TeamManager teamManager;

    private InventoryUtil inventoryUtil;

    boolean debug = false;

    @Override
    public void onEnable() {
        this.init();
        this.initCommands();
    }

    @Override
    public void onDisable() {
        if(this.challengeManager != null) {
            this.challengeManager.shutdown();
        }

        if(this.teamManager != null) {
            this.teamManager.shutdown();
        }

        Bukkit.getServer().getScheduler().cancelTasks(this);
    }

    private void init() {
        try {
            SCLogger.setVerbose(debug);

            this.challengeManager = new ChallengeManager(this);
            this.challengeManager.initialize();

            this.teamManager = new TeamManager(this);
            this.teamManager.initialize();

            this.inventoryUtil = new InventoryUtil(this);
        } catch (Exception e) {
            SCLogger.error("An error occured while enabling plugin", e);
        }
    }

    private void initCommands() {
        this.getCommand("team").setExecutor(new TeamCommand(this));
        this.getCommand("scadmin").setExecutor(new SCAdminCommand(this));
    }

    public ChallengeManager getChallengeManager() {
        return this.challengeManager;
    }

    public TeamManager getTeamManager() {
        return this.teamManager;
    }

    public InventoryUtil getInventoryUtil() {
        return this.inventoryUtil;
    }

}
