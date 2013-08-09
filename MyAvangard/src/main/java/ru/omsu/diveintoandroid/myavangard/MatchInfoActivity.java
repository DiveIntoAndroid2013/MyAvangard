package ru.omsu.diveintoandroid.myavangard;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * MatchInfoActivity
 *
 * @author Alex Korovyansky <korovyansk@gmail.com>
 */
public class MatchInfoActivity extends Activity {

    public static final String INTENT_KEY_MATCH_ID = "matchId";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_info);
        final int matchId = getIntent().getIntExtra(INTENT_KEY_MATCH_ID, -1);
        final TextView matchInfoText = (TextView) findViewById(R.id.match_info_text);
        matchInfoText.setText("Info about Match " + matchId + "!");
    }
}