package com.gna.moody.wumpus;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Wumpus extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wumpus);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_wumpus, menu);
        return true;
    }
}