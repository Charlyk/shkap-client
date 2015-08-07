package com.shkap.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.login.LoginManager;
import com.shkap.BuildConfig;
import com.shkap.R;
import com.shkap.data.ApiInfo;
import com.shkap.social.FBManager;
import com.shkap.social.ShkapSRV;
import com.shkap.social.VKManager;
import com.vk.sdk.VKAccessToken;

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
    private boolean VK = false;
    private boolean FB = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.login_activity);
        if (BuildConfig.DEBUG) {
            FacebookSdk.setIsDebugEnabled(true);
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        }
        ButterKnife.bind(this);

        mPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        mMainIntent = new Intent(this, MainActivity.class);
        Log.i(TAG, "onCreate()");
        if (getToken() != null) {
            Log.i(TAG, getToken());
            startActivity(mMainIntent);
            finish();
        }
        callbackManager = FBManager.getCallbackManager();
        LoginManager.getInstance().registerCallback(callbackManager,
                FBManager.getLoginResult());
    }

    @OnClick(R.id.ggp_loginBtn)
    public void googleClick() {
        makeToasts("Google is coming");
    }

    @OnClick(R.id.vk_loginBtn)
    public void vkClick() {
        VK = true;
        VKManager vkManager = new VKManager(LoginActivity.this, LoginActivity.this);
        try {
            VKManager.logInWithVK();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        makeToasts("Welcome");
    }

    @OnClick(R.id.fb_loginBtn)
    public void fbClick() {
        FB = true;
        LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this,
                Arrays.asList("public_profile", "user_friends"));
    }

    public void makeToasts(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (VK) {
            try {
                ShkapSRV.register(VKAccessToken.currentToken().accessToken, ApiInfo.regToVK());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else if (FB) {
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
}
