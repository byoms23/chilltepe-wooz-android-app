package me.wooz.mobile.android.app.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import me.wooz.mobile.android.app.R;

public class LoginSelectorActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "Login Selector";
    private static final int RC_SIGN_IN_FACEBOOK = 10;
    private static final int RC_SIGN_IN_GOOGLE = 20;

    private GoogleApiClient mGoogleApiClient;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initial configuration
        preConfigureFacebookLogin();

        // Set up views
        setContentView(R.layout.activity_login_selector);

        // Configure logins
        configureFacebookLogin();
        configureGoogleSignIn();
        configureEmailLogin();
    }

    private void preConfigureFacebookLogin() {
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    private void configureFacebookLogin() {
        callbackManager = CallbackManager.Factory.create();

        final LoginButton loginButton = (LoginButton) findViewById(R.id.btn_fb_login_button);
        loginButton.setReadPermissions("email");

        // Callback registration
        loginButton.registerCallback(callbackManager, facebookCallback);
    }

    private void configureGoogleSignIn() {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        // Set up sign in button
        View signInButton = findViewById(R.id.btn_google_sing_in_button);
        signInButton.setOnClickListener(onGoogleSignInListener);
    }

    private void configureEmailLogin() {
        findViewById(R.id.btn_login_with_email).setOnClickListener(onEmailLoginListener);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // TODO
    }

    protected View.OnClickListener onGoogleSignInListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
            startActivityForResult(signInIntent, RC_SIGN_IN_GOOGLE);
        }
    };

    protected View.OnClickListener onEmailLoginListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), EmailLoginActivity.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case RC_SIGN_IN_GOOGLE: {
                // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                handleGoogleSignInResult(result);
                break;
            }
            case RC_SIGN_IN_FACEBOOK:
            default: {
                callbackManager.onActivityResult(requestCode, resultCode, data);
                break;
            }
        }
    }

    private FacebookCallback<LoginResult> facebookCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            // TODO App code
            try {
                Toast.makeText(LoginSelectorActivity.this, new ObjectMapper().writeValueAsString(loginResult), Toast.LENGTH_SHORT).show();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onCancel() {
        }

        @Override
        public void onError(FacebookException exception) {
            // TODO
            Log.e(TAG, "Facebook error", exception);
            Toast.makeText(LoginSelectorActivity.this, "Facebook error", Toast.LENGTH_SHORT).show();
        }
    };

    private void handleGoogleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleGoogleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
//            mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
//            updateUI(true);
            Toast.makeText(this, acct.getDisplayName(), Toast.LENGTH_SHORT).show();
        } else {
            // Signed out, show unauthenticated UI.
//            updateUI(false);
            Toast.makeText(this, "Google Sign In failed", Toast.LENGTH_SHORT).show();
        }
    }
}
