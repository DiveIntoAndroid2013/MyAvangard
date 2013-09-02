package ru.omsu.diveintoandroid.myavangard.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ru.omsu.diveintoandroid.myavangard.R;
import ru.omsu.diveintoandroid.myavangard.model.Match;
import ru.omsu.diveintoandroid.myavangard.model.MatchStatistic;
import ru.omsu.diveintoandroid.myavangard.services.MockMatchService;

/**
 * MatchInfoActivity
 *
 * @author Alex Korovyansky <korovyansk@gmail.com>
 */
public class MatchInfoActivity extends Activity {

    public static final String INTENT_KEY_MATCH_ID = "matchId";
    private static final String TAG = MatchInfoActivity.class.getSimpleName();

    private Match mMatch;
    private MatchStatistic mMatchStatistic;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_info);
        initMatchData();
        prepareUI();
    }

    protected void onRestart() {
        super.onRestart();
        Log.v(TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy");
    }

    private void initMatchData() {
        final Intent intent = getIntent();
        if (!intent.hasExtra(INTENT_KEY_MATCH_ID)) {
            throw new RuntimeException("intent should have " + INTENT_KEY_MATCH_ID + " parameter");
        }
        final int matchId = getIntent().getIntExtra(INTENT_KEY_MATCH_ID, -1);
        final MockMatchService mockMatchService = new MockMatchService();
        mMatch = mockMatchService.getMatch(matchId);
        mMatchStatistic = mockMatchService.getMatchStatistic(matchId);
    }

    private void prepareUI() {
        setTitle(mMatch.team1Name + " vs " + mMatch.team2Name);
        final TextView resultTextView = (TextView) findViewById(R.id.match_info_result);
        resultTextView.setText(mMatch.result);

        prepareField(R.id.match_info_shots, R.string.text_statistics_shots,
                String.valueOf(mMatchStatistic.team1ShotsOnGoal),
                String.valueOf(mMatchStatistic.team2ShotsOnGoal));

        prepareField(R.id.match_info_saves, R.string.text_statistics_saves,
                mMatchStatistic.team1SavesPercent + "%",
                mMatchStatistic.team2SavesPercent + "%");

        prepareField(R.id.match_info_face_off_won, R.string.text_statistics_face_off_won,
                String.valueOf(mMatchStatistic.team1FaceOffsWon),
                String.valueOf(mMatchStatistic.team2FaceOffsWon));

        prepareField(R.id.match_info_power_play_goals, R.string.text_statistics_power_play_goals,
                String.valueOf(mMatchStatistic.team1PowerPlayGoals),
                String.valueOf(mMatchStatistic.team2PowerPlayGoals));

    }

    private void prepareField(int idField, int idEventTitle, String team1result, String team2result) {
        final RelativeLayout statisticsField = (RelativeLayout)findViewById(idField);
        ((TextView)statisticsField.findViewById(R.id.statistics_field_event_name)).setText(idEventTitle);
        ((TextView)statisticsField.findViewById(R.id.statistics_field_team1_result)).setText(team1result);
        ((TextView)statisticsField.findViewById(R.id.statistics_field_team2_result)).setText(team2result);
    }
}