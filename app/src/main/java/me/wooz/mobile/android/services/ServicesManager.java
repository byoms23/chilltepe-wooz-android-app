package me.wooz.mobile.android.services;

import android.content.Context;

import java.io.IOException;

import me.wooz.mobile.android.utils.StorageManager;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by byron on 6/11/16.
 */

public final class ServicesManager {

	private static final String BASE_URL = "https://ensurance-events-notifier.herokuapp.com";

	private StorageManager storageManager;
	private UsersService usersService;

	public ServicesManager(Context context) {
		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
		loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

		OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
		httpClient.addInterceptor(loggingInterceptor);
		httpClient.addInterceptor(new Interceptor() {
			@Override
			public Response intercept(Interceptor.Chain chain) throws IOException {
				Request original = chain.request();

				Request.Builder requestBuilder = original.newBuilder();
				if(storageManager != null && storageManager.getToken() != null) {
					String authorization = "token " + storageManager.getToken();
					requestBuilder.header("Authorization", authorization);
				}
				requestBuilder.method(original.method(), original.body());

				Request request = requestBuilder.build();
				return chain.proceed(request);
			}
		});

		OkHttpClient client = httpClient.build();
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.client(client)
				.addConverterFactory(JacksonConverterFactory.create())
				.build();

		this.storageManager = new StorageManager(context);
		this.usersService = retrofit.create(UsersService.class);
	}

	public UsersService getUsersService() {
		return usersService;
	}
}
