package me.wooz.mobile.android.services;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

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
	private Retrofit retrofit;
	private UsersService usersService;
	private InsuranceService insuranceService;
	private PoliciesService policiesService;

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

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JodaModule());

		OkHttpClient client = httpClient.build();
		this.retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.client(client)
				.addConverterFactory(JacksonConverterFactory.create(mapper))
				.build();

		this.storageManager = new StorageManager(context);
	}

	public UsersService getUsersService() {
		if(usersService == null) {
			this.usersService = retrofit.create(UsersService.class);
		}
		return usersService;
	}

	public InsuranceService getInsuranceService() {
		if(this.insuranceService == null) {
			this.insuranceService = retrofit.create(InsuranceService.class);
		}
		return insuranceService;
	}

	public PoliciesService getPoliciesService() {
		if(this.policiesService == null) {
			this.policiesService = retrofit.create(PoliciesService.class);
		}
		return policiesService;
	}
}
