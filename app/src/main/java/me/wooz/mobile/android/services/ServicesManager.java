package me.wooz.mobile.android.services;

import com.fasterxml.jackson.annotation.JacksonInject;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by byron on 6/11/16.
 */

public final class ServicesManager {

    private static final String BASE_URL = "https://ensurance-events-notifier.herokuapp.com";

    protected UsersService usersService;

    public ServicesManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        this.usersService = retrofit.create(UsersService.class);
    }

    public UsersService getUsersService() {
        return usersService;
    }
}
