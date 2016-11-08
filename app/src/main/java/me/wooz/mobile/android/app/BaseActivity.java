package me.wooz.mobile.android.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import me.wooz.mobile.android.services.ServicesManager;
import me.wooz.mobile.android.utils.StorageManager;

/**
 * Created by byron on 7/11/16.
 */

public class BaseActivity extends AppCompatActivity {

	private StorageManager storageManager;
	private ServicesManager servicesManager;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Initialize managers
		this.storageManager = new StorageManager(this);
		this.servicesManager = new ServicesManager(this);
	}

	public StorageManager getStorageManager() {
		return storageManager;
	}

	public ServicesManager getServicesManager() {
		return servicesManager;
	}
}
