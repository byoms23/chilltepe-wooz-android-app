package me.wooz.mobile.android.services;

import me.wooz.mobile.android.dto.policies.AddPolicyResponse;
import me.wooz.mobile.android.dto.users.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by byron on 9/11/16.
 */

public interface PoliciesService {

	@FormUrlEncoded
	@POST("/insurance_policies")
	Call<AddPolicyResponse> addPolicy(@Field("insurance_company_id") Integer insuranceCompanyId,
			@Field("insurance_type_id") Integer insuranceTypeId, @Field("user_id") Integer userId,
			@Field("number") String number);

}
