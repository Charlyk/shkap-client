package com.shkap.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.shkap.R;
import com.shkap.data.ApiInfo;
import com.shkap.model.ViewInitializer;
import com.shkap.social.FBManager;
import com.shkap.social.ShkapSRV;
import com.shkap.social.VKManager;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.util.VKUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;

public class LoginActivity extends ViewInitializer implements View.OnClickListener {

    protected Intent mMainIntent;
    protected CallbackManager callbackManager;
    private static SharedPreferences mPreferences;
    private static final String PREFERENCES = "preferences";
    private static final String SHKAP_TOKEN = "shkap_token";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.login_activity);
        mPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        mMainIntent = new Intent(this, MainActivity.class);
        if (getToken() != null) {
            Log.i("TAG", getToken());
            startActivity(mMainIntent);
            finish();
        }
        initViews();
        callbackManager = FBManager.getCallbackManager();
        FBManager fbManager = new FBManager(LoginActivity.this);
    }

    @Override
    protected void initToolbar() {}

    @Override
    protected void initViews() {
        Button mGoogleButton = (Button) findViewById(R.id.ggp_loginBtn);
        mGoogleButton.setOnClickListener(this);

        com.facebook.login.widget.LoginButton facebookButton = (com.facebook.login.widget.LoginButton)
                findViewById(R.id.fb_loginBtn);
        facebookButton.setReadPermissions("user_friends");
        facebookButton.registerCallback(FBManager.getCallbackManager(),
                FBManager.getLoginResult());

        Button VKButton = (Button) findViewById(R.id.vk_loginBtn);
        VKButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ggp_loginBtn:
                makeToasts("Google");
                break;
            case R.id.vk_loginBtn:
                VKManager vkManager = new VKManager(LoginActivity.this, LoginActivity.this);
                VKManager.logInWithVK();
                makeToasts("Welcome");
                break;
        }
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

    public void makeToasts(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        startActivity(mMainIntent);
        finish();
        super.onActivityResult(requestCode, resultCode, data);
    }
}
