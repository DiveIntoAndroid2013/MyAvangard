package ru.omsu.diveintoandroid.myavangard;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MatchesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);
        findViewById(R.id.match1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MatchesActivity.this, MatchInfoActivity.class));
            }
        });
        findViewById(R.id.match2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MatchesActivity.this, MatchInfoActivity.class));
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
    
}
