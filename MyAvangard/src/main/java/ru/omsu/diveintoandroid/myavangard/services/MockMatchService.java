package ru.omsu.diveintoandroid.myavangard.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import ru.omsu.diveintoandroid.myavangard.model.Match;
import ru.omsu.diveintoandroid.myavangard.model.MatchStatistic;

/**
 * MockMatchService
 *
 * @author Alex Korovyansky <korovyansk@gmail.com>
 * @author Anton Rozhkov <aurozhkov@gmail.com>
 */
public class MockMatchService implements MatchService {

    private static final String[] TEAMS = {"Lokomotiv", "Traktor", "Barys", "Ak Bars", "Salavat Ulaev"};
    private static final String[] TEAMS_LOGO = {
            "http://khlapp.khl.ru/images/logo/2cf68953c6cd4fabe9285a28a5b3267a.jpg",
            "http://khlapp.khl.ru/images/logo/17ea3fc69f3604cd90a40d56e867e97d.jpg",
            "http://khlapp.khl.ru/images/logo/36b3bbbb0355a0bf4e3a2a7a246ae452.jpg",
            "http://khlapp.khl.ru/images/logo/89eec748745b21cb5cd68131a21a9718.jpg",
            "http://khlapp.khl.ru/images/logo/96f0b4c89c1c77f10212b11dea3df26b.jpg"};

    private static final String OUR_TEAM = "Avangard";
    private static final String OUR_TEAM_LOGO = "http://khlapp.khl.ru/images/logo/7139590334ef1a1ee9b89162bc7583c4.jpg";

    private static final int COUNT_MATCHES = 50;

    private static List<Match> sMatches = generateMatches();

    private static List<Match> generateMatches() {
        final Random random = new Random(System.currentTimeMillis());
        final List<Match> matches = new ArrayList<Match>();
        for (int i = 0; i < COUNT_MATCHES; i++) {
            final Match match = new Match();
            final boolean homeMatch = isHomeMatch(random);
            match.matchId = i;
            match.team1Name = homeMatch ? OUR_TEAM : TEAMS[i % TEAMS.length];
            match.team1Logo = homeMatch ? OUR_TEAM_LOGO : TEAMS_LOGO[i % TEAMS.length];
            match.team2Name = homeMatch ? TEAMS[i % TEAMS.length] : OUR_TEAM;
            match.team2Logo = homeMatch ? TEAMS_LOGO[i % TEAMS.length] : OUR_TEAM_LOGO;
            match.result = String.format("%d:%d", random.nextInt(8), random.nextInt(8));
            match.time = new Date(2013, 8 + i/30, i % 30 +1);
            matches.add(match);
        }
        return matches;
    }

    private static boolean isHomeMatch(Random random) {
        return random.nextInt(2) == 0;
    }

    @Override
    public Match getMatch(long matchId) {
        return sMatches.get((int) matchId);
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

    @Override
    public List<Match> getMatches() {
        return sMatches;
    }
}
