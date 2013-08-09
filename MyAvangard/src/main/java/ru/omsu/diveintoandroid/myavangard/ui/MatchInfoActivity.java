package ru.omsu.diveintoandroid.myavangard.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import ru.omsu.diveintoandroid.myavangard.R;

/**
 * MatchInfoActivity
 *
 * @author Alex Korovyansky <korovyansk@gmail.com>
 */
public class MatchInfoActivity extends Activity {

    public static final String INTENT_KEY_MATCH_ID = "matchId";
    private static final String TAG = MatchInfoActivity.class.getSimpleName();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_info);
        final int matchId = getIntent().getIntExtra(INTENT_KEY_MATCH_ID, -1);
        final TextView matchInfoText = (TextView) findViewById(R.id.match_info_text);
        matchInfoText.setText("Info about Match " + matchId + "!");
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
}