package ru.omsu.diveintoandroid.myavangard.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import ru.omsu.diveintoandroid.myavangard.R;
import ru.omsu.diveintoandroid.myavangard.model.Match;
import ru.omsu.diveintoandroid.myavangard.model.MatchStatistic;
import ru.omsu.diveintoandroid.myavangard.services.MatchService;
import ru.omsu.diveintoandroid.myavangard.services.RealMatchService;

/**
 * MatchInfoActivity
 *
 * @author Alex Korovyansky <korovyansk@gmail.com>
 * @author Anton Rozhkov <aurozhkov@gmail.com>
 */
public class MatchInfoActivity extends Activity {

    public static final String INTENT_KEY_MATCH_ID = "matchId";
    private static final String TAG = MatchInfoActivity.class.getSimpleName();

    private long mMatchId;
    private GetStatisticTask mGetStatisticTask;

    private class GetStatisticTask extends AsyncTask<Long, Void, GetStatisticTask.Result> {

        class Result {
            Match match;
            MatchStatistic matchStatistic;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            findViewById(R.id.match_info_progress).setVisibility(View.VISIBLE);
        }

        @Override
        protected Result doInBackground(Long... longs) {
            try {
                final MatchService matchService = new RealMatchService();
                final Result result = new Result();
                result.match = matchService.getMatch(longs[0]);
                result.matchStatistic = matchService.getMatchStatistic(longs[0]);
                return result;
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Result result) {
            super.onPostExecute(result);
            findViewById(R.id.match_info_progress).setVisibility(View.GONE);
            if (result != null) {
                updateTitle(result.match);
                updateHeader(result.match);
                updateStatistics(result.matchStatistic);
            } else {
                Toast.makeText(MatchInfoActivity.this, R.string.error_message_async, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_info);
        initMatchData();
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
        mGetStatisticTask = new GetStatisticTask();
        mGetStatisticTask.execute(mMatchId);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "onPause");
        mGetStatisticTask.cancel(true);
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
        mMatchId = getIntent().getLongExtra(INTENT_KEY_MATCH_ID, -1);
    }

    private void updateTitle(Match match) {
        setTitle(match.team1Name + " vs " + match.team2Name);
    }

    private void updateHeader(Match match) {
        findViewById(R.id.match_info_header).setVisibility(View.VISIBLE);
        final TextView resultTextView = (TextView) findViewById(R.id.match_info_result);
        resultTextView.setText(match.result);

        updateLogo(R.id.match_info_logo1, match.team1Logo);
        updateLogo(R.id.match_info_logo2, match.team2Logo);
    }

    private void updateLogo(int idLogo, String logo) {
        final ImageView logoImageView = (ImageView) findViewById(idLogo);
        Picasso.with(this).load(logo).into(logoImageView);
    }


    private void updateStatistics(MatchStatistic matchStatistic) {
        if (matchStatistic != null) {
            findViewById(R.id.match_info_statistic_container).setVisibility(View.VISIBLE);

            updateStatisticField(R.id.match_info_shots, R.string.text_statistics_shots,
                    String.valueOf(matchStatistic.team1ShotsOnGoal),
                    String.valueOf(matchStatistic.team2ShotsOnGoal));

            updateStatisticField(R.id.match_info_saves, R.string.text_statistics_saves,
                    String.format("%.1f", matchStatistic.team1SavesPercent) + "%",
                    String.format("%.1f", matchStatistic.team2SavesPercent) + "%");

            updateStatisticField(R.id.match_info_face_off_won, R.string.text_statistics_face_off_won,
                    String.valueOf(matchStatistic.team1FaceOffsWon),
                    String.valueOf(matchStatistic.team2FaceOffsWon));

            updateStatisticField(R.id.match_info_power_play_goals, R.string.text_statistics_power_play_goals,
                    String.valueOf(matchStatistic.team1PowerPlayGoals),
                    String.valueOf(matchStatistic.team2PowerPlayGoals));
        }
    }

    private void updateStatisticField(int idField, int idEventTitle, String team1result, String team2result) {
        final RelativeLayout statisticsField = (RelativeLayout)findViewById(idField);
        ((TextView)statisticsField.findViewById(R.id.statistics_field_event_name)).setText(idEventTitle);
        ((TextView)statisticsField.findViewById(R.id.statistics_field_team1_result)).setText(team1result);
        ((TextView)statisticsField.findViewById(R.id.statistics_field_team2_result)).setText(team2result);
    }

}