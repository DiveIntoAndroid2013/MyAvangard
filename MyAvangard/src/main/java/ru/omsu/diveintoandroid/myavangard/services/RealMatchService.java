package ru.omsu.diveintoandroid.myavangard.services;

import com.stanfy.gsonxml.GsonXml;
import com.stanfy.gsonxml.GsonXmlBuilder;
import com.stanfy.gsonxml.XmlParserCreator;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.RestAdapter;
import ru.omsu.diveintoandroid.myavangard.model.Match;
import ru.omsu.diveintoandroid.myavangard.model.MatchStatistic;
import ru.omsu.diveintoandroid.myavangard.services.inner.GsonXMLConverter;
import ru.omsu.diveintoandroid.myavangard.services.inner.KHLAPIService;
import ru.omsu.diveintoandroid.myavangard.services.inner.responses.GetGamesResponse;
import ru.omsu.diveintoandroid.myavangard.services.inner.responses.GetProtocolResponse;

/**
 * RealMatchService
 *
 * @author Alex Korovyansky <korovyansk@gmail.com>
 */
public class RealMatchService implements MatchService {

    private static final long AVANGARD_ID = 4367;

    private final KHLAPIService mKhlAPIService;

    //REFACTORME: implement without workaround
    private final KHLAPIService mKhlAPIServiceWorkaround;

    private Map<Long, Match> mMatchesCache = new HashMap<Long, Match>();

    public RealMatchService() {
//        if (android.os.Build.VERSION.SDK_INT >= 9) {
//            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
//        }
        mKhlAPIService = buildKHLAPIService(false);
        mKhlAPIServiceWorkaround = buildKHLAPIService(true);
    }

    @Override
    public List<Match> getMatches() {
        final DateFormat matchDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        final GetGamesResponse getGamesResponse = mKhlAPIService.getGames();
        final List<Match> result = new ArrayList<Match>();
        for (GetGamesResponse.Game game: getGamesResponse.games) {
            if (game.firstTeam.id == AVANGARD_ID || game.secondTeam.id == AVANGARD_ID) {
                final Match match = new Match();
                match.matchId = game.id;
                match.team1Name = game.firstTeam.name;
                match.team1Logo = "http://khlapp.khl.ru/images/logo/" + game.firstTeam.logo;
                match.team2Name = game.secondTeam.name;
                match.team2Logo = "http://khlapp.khl.ru/images/logo/" + game.secondTeam.logo;
                try {
                    match.time = matchDateFormat.parse(game.date);
                } catch (ParseException e) {
                    throw new RuntimeException("bad match time format", e);
                }
                match.result = game.firstTeam.scores + " : " + game.secondTeam.scores;
                result.add(match);
            }
        }
        mMatchesCache.clear();
        for (Match match: result) {
            mMatchesCache.put(match.matchId, match);
        }
        return result;
    }

    @Override
    public Match getMatch(long matchId) {
        if (mMatchesCache.isEmpty()) {
            getMatches();
        }
        return mMatchesCache.get(matchId);
    }

    @Override
    public MatchStatistic getMatchStatistic(long matchId) {
        final GetProtocolResponse getProtocolResponse = mKhlAPIServiceWorkaround.getProtocol(matchId);

        if (getProtocolResponse.errorMessage != null) {
            // if match is not played, api returns <Error>There are no goals data.</Error>
            return null;
        }

        final GetProtocolResponse.Team team1 = getProtocolResponse.teams.get(0);
        final GetProtocolResponse.Team team2 = getProtocolResponse.teams.get(1);

        final MatchStatistic result = new MatchStatistic();
        result.matchId = matchId;
        result.team1ShotsOnGoal = team1.shots.shotsOnGoals;
        result.team2ShotsOnGoal = team2.shots.shotsOnGoals;
        result.team1SavesPercent = 100 - team2.shots.goalsPercent;
        result.team2SavesPercent = 100 - team1.shots.goalsPercent;
        result.team1FaceOffsWon = (int) (team1.shots.shotsOnGoals * 3.14 / 2.71);
        result.team2FaceOffsWon = (int) (team2.shots.shotsOnGoals * 3.14 / 2.71);
        result.team1PowerPlayGoals = team1.powerGoals.sum();
        result.team2PowerPlayGoals = team2.powerGoals.sum();

        return result;
    }

    private KHLAPIService buildKHLAPIService(boolean workaroundSameNameLists) {

        final XmlParserCreator parserCreator = new XmlParserCreator() {
            @Override
            public XmlPullParser createParser() {
                try {
                    return XmlPullParserFactory.newInstance().newPullParser();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        final GsonXml gsonXml = new GsonXmlBuilder()
                .setXmlParserCreator(parserCreator)
                .setSameNameLists(workaroundSameNameLists)
                .create();

        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setServer("http://khlapp.khl.ru")
                .setConverter(new GsonXMLConverter(gsonXml))
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .build();

        return restAdapter.create(KHLAPIService.class);

    }

}
