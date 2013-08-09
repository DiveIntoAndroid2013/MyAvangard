package ru.omsu.diveintoandroid.myavangard.services;

import ru.omsu.diveintoandroid.myavangard.model.Match;
import ru.omsu.diveintoandroid.myavangard.model.MatchStatistic;

/**
 * MockMatchService
 *
 * @author Alex Korovyansky <korovyansk@gmail.com>
 */
public class MockMatchService implements MatchService {


    @Override
    public Match getMatch(long matchId) {
        final Match match = new Match();
        match.matchId = matchId;
        match.team1Name = "Авангард";
        match.team1Logo = "";
        match.team2Name = "Динамо";
        match.team2Logo = "";
        match.result = "10:0";
        return match;
    }

    @Override
    public MatchStatistic getMatchStatistic(long matchId) {
        final MatchStatistic matchStatistic = new MatchStatistic();
        matchStatistic.matchId = matchId;

        matchStatistic.team1FaceOffsWon = 40;
        matchStatistic.team1PowerPlayGoals = 5;
        matchStatistic.team1SavesPercent = 100;
        matchStatistic.team1ShotsOnGoal = 60;

        matchStatistic.team2FaceOffsWon = 5;
        matchStatistic.team2PowerPlayGoals = 0;
        matchStatistic.team2SavesPercent = 83.3;
        matchStatistic.team2ShotsOnGoal = 10;

        return matchStatistic;
    }


}
