package me.wooz.mobile.android.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.wooz.mobile.android.app.login.LoginSelectorActivity;
import me.wooz.mobile.android.app.utils.StorageManager;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = null;
        StorageManager storageManager = new StorageManager(this);
        if(!storageManager.isLoggedIn()) {
            intent = new Intent(this, LoginSelectorActivity.class);
        }

        if(intent != null) {
            startActivity(intent);
        }
        finish();
    }
}
