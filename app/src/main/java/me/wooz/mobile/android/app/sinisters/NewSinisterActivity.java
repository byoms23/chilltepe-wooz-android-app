package me.wooz.mobile.android.app.sinisters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import me.wooz.mobile.android.app.R;

public class NewSinisterActivity extends AppCompatActivity {

    private static final int CODE_SEND_LOCATION = 1;

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

        findViewById(R.id.btn_send_location).setOnClickListener(onSendLocationClick);
    }

    protected View.OnClickListener onSendLocationClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), SendLocationActivity.class);
            startActivityForResult(intent, CODE_SEND_LOCATION);
        }
    };
}
