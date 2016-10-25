package me.wooz.mobile.android.app.sinisters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import me.wooz.mobile.android.app.R;

public class NewSinisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sinister);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        RecyclerView rvEvents = (RecyclerView) findViewById(R.id.rv_events);
        rvEvents.setHasFixedSize(true);
        rvEvents.setAdapter(new SinistersAdapter());

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        rvEvents.setLayoutManager(layoutManager);
    }
}
