package me.wooz.mobile.android.app.sinisters;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import me.wooz.mobile.android.app.R;
import me.wooz.mobile.android.utils.RecyclerItemClickListener;

import static me.wooz.mobile.android.app.R.id.recyclerView;

public class SinisterTypeSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinister_type_selection);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rvSinisterType = (RecyclerView) findViewById(R.id.rv_sinister_type);
        rvSinisterType.setHasFixedSize(true);
        rvSinisterType.setItemAnimator(new DefaultItemAnimator());
        rvSinisterType.setAdapter(new SinistersTypesAdapter());
        rvSinisterType.addOnItemTouchListener(
            new RecyclerItemClickListener(this, rvSinisterType, new RecyclerItemClickListener.OnItemClickListener() {
                @Override public void onItemClick(View view, int position) {
                    Intent intent = new Intent(view.getContext(), NewSinisterActivity.class);
                    startActivity(intent);
                }

                @Override public void onLongItemClick(View view, int position) {
                }
            })
        );

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvSinisterType.setLayoutManager(layoutManager);

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
//                android.R.color.transparent);
//        rvSinisterType.addItemDecoration(dividerItemDecoration);

    }
}
