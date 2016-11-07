package me.wooz.mobile.android.services;

import me.wooz.mobile.android.dto.users.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by byron on 6/11/16.
 */

public interface UsersService {

    @FormUrlEncoded
    @POST("/users")
    Call<RegisterResponse> register(@Field("name") String name, @Field("email") String email,
            @Field("password") String password, @Field("address") String address,
            @Field("phone_number") String phoneNumber, @Field("birth_date") String birthDate);
}
