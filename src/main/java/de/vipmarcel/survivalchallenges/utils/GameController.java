package de.vipmarcel.survivalchallenges.utils;

public class GameController {

    /*
        set this boolean to 'true' if you wish to eliminate the whole team when a player dies
     */
    private static final boolean eliminateTeamWhenPlayerDies = false;

    /*
        set this boolean to 'true' if you wish that the last team wins when the last enemy team eliminates
     */
    private static final boolean lastTeamWins = false;



    public static boolean isEliminateWholeTeam() {
        return eliminateTeamWhenPlayerDies;
    }

    public static boolean isLastTeamWins() {
        return lastTeamWins;
    }

}
