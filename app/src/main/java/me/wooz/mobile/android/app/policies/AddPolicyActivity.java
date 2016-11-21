package me.wooz.mobile.android.app.policies;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.wooz.mobile.android.app.BaseActivity;
import me.wooz.mobile.android.app.R;
import me.wooz.mobile.android.dto.ensurance.InsuranceCompany;
import me.wooz.mobile.android.dto.ensurance.InsuranceType;
import me.wooz.mobile.android.dto.policies.AddPolicyResponse;
import me.wooz.mobile.android.widgets.WoozSpinner;
import retrofit2.Call;
import retrofit2.Response;

public class AddPolicyActivity extends BaseActivity {

	private static final String TAG = AddPolicyActivity.class.getCanonicalName();

	private LoadEnsuranceInfoTask mLoadTask = null;
	private AddPolicyTask mAddTask = null;

	private WoozSpinner insuranceCompanySpinner;
	private ArrayAdapter<InsuranceCompany> insuranceCompaniesAdapter;
	private WoozSpinner insuranceTypeSpinner;
	private ArrayAdapter<InsuranceType> insuranceTypesAdapter;
	private EditText etPolicyNumber;
//	private View mProgressView;
//	private View mLoginFormView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_policy);

		this.insuranceCompaniesAdapter = new InsuranceCompanyAdapter(this,
				 new ArrayList<InsuranceCompany>());
		this.insuranceCompanySpinner = (WoozSpinner)findViewById(R.id.sp_ensurance_company);
		this.insuranceCompanySpinner.setAdapter(insuranceCompaniesAdapter);
//		this.insuranceCompanySpinner.set(insuranceCompaniesAdapter);

		this.insuranceTypesAdapter = new InsuranceTypeAdapter(this, new ArrayList<InsuranceType>());
		this.insuranceTypeSpinner = (WoozSpinner) findViewById(R.id.sp_ensurance_type);
		this.insuranceTypeSpinner.setAdapter(insuranceTypesAdapter);

		this.etPolicyNumber = (EditText) findViewById(R.id.policy_number);

		findViewById(R.id.add_policy_button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				attemptAction();
			}
		});

//		mLoginFormView = findViewById(R.id.login_form);
//		mProgressView = findViewById(R.id.login_progress);

		new LoadEnsuranceInfoTask().execute();
	}

	private void attemptAction() {
		if (mAddTask != null) {
			return;
		}

		// Reset errors.
		insuranceCompanySpinner.setError(null);
		insuranceTypeSpinner.setError(null);
		etPolicyNumber.setError(null);

		// Store values at the time of the login attempt.
		Integer companyIndex = insuranceCompanySpinner.getSelectedPosition();
		Integer typeIndex = insuranceTypeSpinner.getSelectedPosition();
		String policyNumber = etPolicyNumber.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid inputs.
		if (TextUtils.isEmpty(policyNumber)) {
			etPolicyNumber.setError(getString(R.string.error_field_required));
			focusView = etPolicyNumber;
			cancel = true;
		}
		if(typeIndex < 0 || typeIndex >= insuranceTypesAdapter.getCount()) {
			insuranceTypeSpinner.setError(getString(R.string.error_field_required));
			focusView = insuranceTypeSpinner;
			cancel = true;
		}
		if(companyIndex < 0 || companyIndex >= insuranceCompaniesAdapter.getCount()) {
			insuranceCompanySpinner.setError(getString(R.string.error_field_required));
			focusView = insuranceCompanySpinner;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt action and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the action attempt.
			showProgress(true);
			mAddTask = new AddPolicyTask(insuranceCompaniesAdapter.getItem(companyIndex).getId(),
					insuranceTypesAdapter.getItem(typeIndex).getId(), policyNumber);
			mAddTask.execute((Void) null);
		}
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
	 * Represents an asynchronous task used to load info.
	 */
	public class LoadEnsuranceInfoTask extends AsyncTask<Void, Void, Boolean> {

		List<InsuranceCompany> insuranceCompanies = Collections.emptyList();
		List<InsuranceType> insuranceTypes = Collections.emptyList();

		@Override
		protected Boolean doInBackground(Void... params) {
			try {
				// Load companies list
				Call<List<InsuranceCompany>> getInsuranceCompaniesCall = getServicesManager()
						.getInsuranceService().getInsuranceCompanies();
				Response<List<InsuranceCompany>> getInsuranceCompaniesResponse =
						getInsuranceCompaniesCall.execute();
				if(getInsuranceCompaniesResponse.isSuccessful()) {

					// Load insurance types
					Call<List<InsuranceType>> getInsuranceTypesCall = getServicesManager()
							.getInsuranceService().getInsuranceTypes();
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

	/**
	 * Represents an asynchronous task used to add a policy.
	 */
	public class AddPolicyTask extends AsyncTask<Void, Void, Boolean> {

		private Long insuranceCompanyId;
		private Long insuranceTypeId;
		private String policyNumber;

		public AddPolicyTask(Long insuranceCompanyId, Long insuranceTypeId,
				String policyNumber) {
			this.insuranceCompanyId = insuranceCompanyId;
			this.insuranceTypeId = insuranceTypeId;
			this.policyNumber = policyNumber;
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			try {
				// Load companies list
				Call<AddPolicyResponse> call = getServicesManager()
						.getPoliciesService().addPolicy(insuranceCompanyId, insuranceTypeId,
						null, policyNumber);
				Response<AddPolicyResponse> response = call.execute();
				if(response.isSuccessful()) {
					// TODO Add policy to dao storage
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
			mLoadTask = null;
			showProgress(false);

			if (success) {
				setResult(RESULT_OK);
				finish();
			} else {
//				Sn0ackbar.make("", Snackbar.LENGTH_INDEFINITE).show();
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
