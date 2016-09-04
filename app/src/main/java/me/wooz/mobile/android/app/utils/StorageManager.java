package me.wooz.mobile.android.app.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by byron on 4/09/16.
 */
public class StorageManager {

    private static final String APPLICATION_PREFERENCES = "app_prefs";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";
    private static final String KEY_HAS_POLICIES = "has_policies";
    private static final String KEY_SHOULD_GO_TO_PREFERRED_POLICY = "go_to_preferred_policy";

    protected SharedPreferences preferences;

    public StorageManager(Context context) {
        preferences = context.getSharedPreferences(APPLICATION_PREFERENCES, Context.MODE_PRIVATE);
    }

    public boolean isLoggedIn() {
        return preferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.apply();
    }

    public boolean hasPolicies() {
        return preferences.getBoolean(KEY_HAS_POLICIES, false);
    }

    public void setHasPolicies(boolean hasPolicies) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_HAS_POLICIES, hasPolicies);
        editor.apply();
    }

    public boolean shouldGoToPreferredPolicy() {
        return preferences.getBoolean(KEY_SHOULD_GO_TO_PREFERRED_POLICY, true);
    }

    public void setShouldGoToPreferredPolicy(boolean shouldGoToPreferredPolicy) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_SHOULD_GO_TO_PREFERRED_POLICY, shouldGoToPreferredPolicy);
        editor.apply();
    }
}
