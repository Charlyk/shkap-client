package com.shkap.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.shkap.R;
import com.shkap.shkapsdk.ApiInfo;
import com.shkap.shkapsdk.ShkapClient;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends Activity {

    protected Intent mMainIntent;
    protected CallbackManager callbackManager;
    private static SharedPreferences mPreferences;
    private static final String PREFERENCES = "preferences";
    private static final String SHKAP_TOKEN = "shkap_token";
    private static final String TAG = "TAG";
    private ShkapClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        VKSdk.initialize(this);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);

        mPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        mMainIntent = new Intent(this, MainActivity.class);
        mClient = new ShkapClient();
        Log.i(TAG, "onCreate()");
        //if (getToken() != null) {
            //Log.i(TAG, getToken());
            startActivity(mMainIntent);
            finish();
        //}

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        try {
                            saveToken(mClient.fbRegister(loginResult
                                    .getAccessToken()
                                    .getToken()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException e) {

                    }
                });
    }

    @OnClick(R.id.ggp_loginBtn)
    public void googleClick() {
        Toast.makeText(this, "Google is coming", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.vk_loginBtn)
    public void vkClick() {
        VKSdk.login(LoginActivity.this, VKScope.PHOTOS, VKScope.FRIENDS);
    }

    @OnClick(R.id.fb_loginBtn)
    public void fbClick() {
        LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this,
                Arrays.asList("public_profile", "user_friends"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (VKSdk.isLoggedIn()) {
            try {
                saveToken(mClient.vkRegister(VKAccessToken
                        .currentToken()
                        .accessToken));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
        startActivity(mMainIntent);
        finish();
    }

    public static void saveToken(String shkap_token) {
        Log.i("TAG", shkap_token);
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(SHKAP_TOKEN, shkap_token);
        editor.apply();
    }

    public static String getToken() {
        return mPreferences.getString(SHKAP_TOKEN, null);
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;

        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    public void alertUserAboutTheError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "error_dialog");
    }
}
