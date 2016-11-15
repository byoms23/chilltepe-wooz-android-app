package me.wooz.mobile.android.services;

import java.util.List;

import me.wooz.mobile.android.dto.ensurance.InsuranceCompany;
import me.wooz.mobile.android.dto.ensurance.InsuranceType;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by byron on 8/11/16.
 */

public interface InsuranceService {

	@GET("/insurance_companies")
	Call<List<InsuranceCompany>> getInsuranceCompanies();

	@GET("/insurance_types")
	Call<List<InsuranceType>> getInsuranceTypes();
}
