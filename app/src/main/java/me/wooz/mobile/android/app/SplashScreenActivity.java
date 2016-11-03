package me.wooz.mobile.android.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.wooz.mobile.android.app.login.LoginSelectorActivity;
import me.wooz.mobile.android.app.policies.PoliciesListActivity;
import me.wooz.mobile.android.app.sinisters.SinisterTypeSelectionActivity;
import me.wooz.mobile.android.utils.StorageManager;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = null;
        StorageManager storageManager = new StorageManager(this);
//        if(storageManager.isLoggedIn()) {
//            if(storageManager.hasPolicies()) {
                intent = new Intent(this, SinisterTypeSelectionActivity.class);
//            } else {
//                intent = new Intent(this, PoliciesListActivity.class);
//                storageManager.setHasPolicies(true);
//            }
//        } else {
//            intent = new Intent(this, LoginSelectorActivity.class);
//            storageManager.setIsLoggedIn(true);
//        }

        if(intent != null) {
            startActivity(intent);
        }
        finish();
    }
}
