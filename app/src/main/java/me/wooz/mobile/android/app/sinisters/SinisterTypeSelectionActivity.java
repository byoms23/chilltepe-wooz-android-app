package me.wooz.mobile.android.app.sinisters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import me.wooz.mobile.android.app.R;
import me.wooz.mobile.android.widgets.RecyclerItemClickListener;

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_sinister_type_selection, menu);
//		MenuItem menuGoToSinisterTypeSelection = menu.findItem(R.id.action_go_to_sinister_type_selection);
//		menuGoToSinisterTypeSelection.setVisible(false);
		return true;
	}

}
