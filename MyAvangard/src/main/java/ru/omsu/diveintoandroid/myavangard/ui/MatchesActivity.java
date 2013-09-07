package ru.omsu.diveintoandroid.myavangard.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import ru.omsu.diveintoandroid.myavangard.R;
import ru.omsu.diveintoandroid.myavangard.adapters.MatchesAdapter;
import ru.omsu.diveintoandroid.myavangard.model.Match;
import ru.omsu.diveintoandroid.myavangard.services.MatchService;
import ru.omsu.diveintoandroid.myavangard.services.MockMatchService;

/**
 * MatchesActivity
 *
 * @author Alex Korovyansky <korovyansk@gmail.com>
 * @author Anton Rozhkov <aurozhkov@gmail.com>
*/
public class MatchesActivity extends Activity {

    private static final String TAG = MatchesActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate");
        setContentView(R.layout.activity_matches);

        prepareListView();
    }

    private void prepareListView() {
        final MatchService matchService = new MockMatchService();
        final ListView matchesListView = (ListView) findViewById(R.id.matches_listview);
        matchesListView.setAdapter(new MatchesAdapter(this, matchService.getMatches()));
        matchesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Match selectedMatch = (Match) adapterView.getItemAtPosition(i);
                goToMatchInfo(selectedMatch.matchId);
            }
        });
    }

    private void goToMatchInfo(long matchId) {
        final Intent intent = new Intent(MatchesActivity.this, MatchInfoActivity.class);
        intent.putExtra(MatchInfoActivity.INTENT_KEY_MATCH_ID, matchId);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_site) {
            openSite();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void openSite() {
        final String url = "http://hawk.ru";
        final Intent openBrowserIntent = new Intent(Intent.ACTION_VIEW);
        openBrowserIntent.setData(Uri.parse(url));
        startActivity(openBrowserIntent);
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