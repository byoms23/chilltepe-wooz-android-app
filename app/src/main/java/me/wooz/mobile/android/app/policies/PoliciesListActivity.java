package me.wooz.mobile.android.app.policies;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import me.wooz.mobile.android.app.BaseActivity;
import me.wooz.mobile.android.app.R;
import me.wooz.mobile.android.app.sinisters.SinisterTypeSelectionActivity;
import me.wooz.mobile.android.dto.policies.AddPolicyResponse;
import me.wooz.mobile.android.dto.policies.DaoSession;
import me.wooz.mobile.android.dto.policies.Policy;
import retrofit2.Call;
import retrofit2.Response;

public class PoliciesListActivity extends BaseActivity {

	private static final String TAG = PoliciesListActivity.class.getCanonicalName();

	protected static final String TAG_ADD_POLICY = "ADD_POLICY";
	protected static final int CODE_ADD_POLICY = 1;
	private MenuItem menuGoToSinisterTypeSelection;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_policies_list);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

//                AddPolicyDialogFragment.newInstance().show(getSupportFragmentManager(),
//                        TAG_ADD_POLICY);

				Intent intent = new Intent(view.getContext(), AddPolicyActivity.class);
				startActivityForResult(intent, CODE_ADD_POLICY);
			}
		});

		if(savedInstanceState == null) {
			new LoadPoliciesTask().execute();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_policies_list, menu);
		menuGoToSinisterTypeSelection = menu.findItem(R.id.action_go_to_sinister_type_selection);
		menuGoToSinisterTypeSelection.setVisible(false);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_go_to_sinister_type_selection:
				Intent intent = new Intent(this, SinisterTypeSelectionActivity.class);
				startActivityForResult(intent, CODE_ADD_POLICY);
//                TODO
//                finish();
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch(requestCode) {
			case CODE_ADD_POLICY: {
				switch(resultCode) {
					case RESULT_OK: {
						onPolicyAdded(null);
						break;
					}
					default: {
						super.onActivityResult(requestCode, resultCode, data);
					}
				}
			}
			default: {
				super.onActivityResult(requestCode, resultCode, data);
			}
		}
	}

	public void onPolicyAdded(Uri uri) {
		if(menuGoToSinisterTypeSelection != null) {
			menuGoToSinisterTypeSelection.setVisible(true);
//            menuGoToSinisterTypeSelection.getMenuInfo()..getActionView().performLongClick();
		}

		new LoadPoliciesTask().execute();
	}

	/**
	 * Represents an asynchronous task used to add a policy.
	 */
	public class LoadPoliciesTask extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Void... params) {
			try {
				// Load companies list
				Call<List<Policy>> call = getServicesManager()
						.getPoliciesService().getPolicies();
				Response<List<Policy>> response = call.execute();
				if(response.isSuccessful()) {
					// TODO Add policy to dao storage
					List<Policy> policies = response.body();
					DaoSession daoSession = getDaoSession();
					daoSession.getPolicyDao().deleteAll();
					daoSession.getPolicyDao().insertInTx(policies);
					getStorageManager().setHasPolicies(policies.size() > 0);
					return true;
				}
			} catch (IOException e) {
				Log.e(TAG, "Error calling service.", e);
			} catch (Exception e) {
				Log.e(TAG, "Unexpected. Error calling service.", e);
			}

			return false;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
//			mLoadTask = null;
//			showProgress(false);

			if (success) {
				setResult(RESULT_OK);
				finish();
			} else {
//				Sn0ackbar.make("", Snackbar.LENGTH_INDEFINITE).show();
				Toast.makeText(PoliciesListActivity.this, "Error searching for info.",
						Toast.LENGTH_LONG).show();
			}
		}

		@Override
		protected void onCancelled() {
//			mLoadTask = null;
//			showProgress(false);
		}
	}

}
