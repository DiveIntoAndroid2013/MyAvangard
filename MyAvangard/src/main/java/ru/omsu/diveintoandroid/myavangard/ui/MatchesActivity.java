package ru.omsu.diveintoandroid.myavangard.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import ru.omsu.diveintoandroid.myavangard.R;

/**
 * MatchInfoActivity
 *
 * @author Alex Korovyansky <korovyansk@gmail.com>
 */
public class MatchesActivity extends Activity {

    private static final String TAG = MatchesActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate");
        setContentView(R.layout.activity_matches);
        findViewById(R.id.match1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(MatchesActivity.this, MatchInfoActivity.class);
                intent.putExtra(MatchInfoActivity.INTENT_KEY_MATCH_ID, 1);
                startActivity(intent);
            }
        });
        findViewById(R.id.match2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(MatchesActivity.this, MatchInfoActivity.class);
                intent.putExtra(MatchInfoActivity.INTENT_KEY_MATCH_ID, 2);
                startActivity(intent);
            }
        });
        findViewById(R.id.site).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String url = "http://hawk.ru";
                final Intent openBrowserIntent = new Intent(Intent.ACTION_VIEW);
                openBrowserIntent.setData(Uri.parse(url));
                startActivity(openBrowserIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
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
