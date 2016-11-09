package me.wooz.mobile.android.app.policies;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.wooz.mobile.android.app.BaseActivity;
import me.wooz.mobile.android.app.R;
import me.wooz.mobile.android.dto.ensurance.InsuranceCompany;
import me.wooz.mobile.android.dto.ensurance.InsuranceType;
import retrofit2.Call;
import retrofit2.Response;

public class AddPolicyActivity extends BaseActivity {

	private static final String TAG = AddPolicyActivity.class.getCanonicalName();

	private LoadEnsuranceInfoTask mLoadTask = null;

	private ArrayAdapter<InsuranceCompany> insuranceCompaniesAdapter;
	private ArrayAdapter<InsuranceType> insuranceTypesAdapter;
//	private View mProgressView;
//	private View mLoginFormView;

//	private String[] DEFAULT_LIST = {"Android Material Design", "Material Design Spinner", "Spinner Using Material Library", "Material Spinner Example"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_policy);

		this.insuranceCompaniesAdapter = new InsuranceCompanyAdapter(this,
				 new ArrayList<InsuranceCompany>());
		MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner)
				findViewById(R.id.sp_ensurance_company);
		materialDesignSpinner.setAdapter(insuranceCompaniesAdapter);

		this.insuranceTypesAdapter = new InsuranceTypeAdapter(this,
				new ArrayList<InsuranceType>());
		MaterialBetterSpinner ensuranceTypeSpinner = (MaterialBetterSpinner)
				findViewById(R.id.sp_ensurance_type);
		ensuranceTypeSpinner.setAdapter(insuranceTypesAdapter);

		findViewById(R.id.add_policy_button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setResult(RESULT_OK);
				finish();
			}
		});

//		mLoginFormView = findViewById(R.id.login_form);
//		mProgressView = findViewById(R.id.login_progress);

		new LoadEnsuranceInfoTask().execute();
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

//			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
//			mLoginFormView.animate().setDuration(shortAnimTime).alpha(
//					show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
//				@Override
//				public void onAnimationEnd(Animator animation) {
//					mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
//				}
//			});
//
//			mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//			mProgressView.animate().setDuration(shortAnimTime).alpha(
//					show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
//				@Override
//				public void onAnimationEnd(Animator animation) {
//					mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//				}
//			});
//		} else {
//			// The ViewPropertyAnimator APIs are not available, so simply show
//			// and hide the relevant UI components.
//			mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}


	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class LoadEnsuranceInfoTask extends AsyncTask<Void, Void, Boolean> {

		List<InsuranceCompany> insuranceCompanies = Collections.emptyList();
		List<InsuranceType> insuranceTypes = Collections.emptyList();

		@Override
		protected Boolean doInBackground(Void... params) {
			try {
				// Load companies list
				Call<List<InsuranceCompany>> getInsuranceCompaniesCall = getServicesManager()
						.getInsuranceServices().getInsuranceCompanies();
				Response<List<InsuranceCompany>> getInsuranceCompaniesResponse =
						getInsuranceCompaniesCall.execute();
				if(getInsuranceCompaniesResponse.isSuccessful()) {

					// Load insurance types
					Call<List<InsuranceType>> getInsuranceTypesCall = getServicesManager()
							.getInsuranceServices().getInsuranceTypes();
					Response<List<InsuranceType>> getInsuranceTypesResponse =
							getInsuranceTypesCall.execute();
					if(getInsuranceTypesResponse.isSuccessful()) {
						insuranceCompanies = getInsuranceCompaniesResponse.body();
						insuranceTypes = getInsuranceTypesResponse.body();
						return true;
					}
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
			mLoadTask = null;
			showProgress(false);

			if (success) {
				insuranceCompaniesAdapter.addAll(insuranceCompanies);
				insuranceCompaniesAdapter.notifyDataSetChanged();
				insuranceTypesAdapter.addAll(insuranceTypes);
				insuranceTypesAdapter.notifyDataSetChanged();
			} else {
//				Snackbar.make("", Snackbar.LENGTH_INDEFINITE).show();
				Toast.makeText(AddPolicyActivity.this, "Error searching for info.",
						Toast.LENGTH_LONG).show();
			}
		}

		@Override
		protected void onCancelled() {
			mLoadTask = null;
			showProgress(false);
		}
	}
}
