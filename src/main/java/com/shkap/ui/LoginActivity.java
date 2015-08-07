package com.shkap.ui;

import android.app.Activity;
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
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
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
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends Activity {

    protected Intent mMainIntent;
    protected CallbackManager callbackManager;
    private static SharedPreferences mPreferences;
    private static final String PREFERENCES = "preferences";
    private static final String SHKAP_TOKEN = "shkap_token";

    @Bind(R.id.ggp_loginBtn) Button mGoogleButton;
    @Bind(R.id.fb_loginBtn) Button mFacebookButton;
    @Bind(R.id.vk_loginBtn) Button mVKButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);

        mPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        mMainIntent = new Intent(this, MainActivity.class);
        if (getToken() != null) {
            Log.i("TAG", getToken());
            startActivity(mMainIntent);
            finish();
        }
        callbackManager = FBManager.getCallbackManager();
        FBManager fbManager = new FBManager(LoginActivity.this);
        LoginManager.getInstance().registerCallback(FBManager.getCallbackManager(),
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        makeLog("TAG", loginResult.getAccessToken().toString());
                        LoginActivity.saveToken(ShkapSRV.register(loginResult.getAccessToken().toString(),
                                ApiInfo.regToFacebook()));
                    }

                    @Override
                    public void onCancel() {
                        makeToasts("You canceled authorisation");
                    }

                    @Override
                    public void onError(FacebookException e) {
                        makeToasts("Ooops... there is an error");
                    }
                });
    }

    @OnClick(R.id.ggp_loginBtn)
    public void googleClick() {
        makeToasts("Google");
    }

    @OnClick(R.id.vk_loginBtn)
    public void vkClick() {
        VKManager vkManager = new VKManager(LoginActivity.this, LoginActivity.this);
        VKManager.logInWithVK();
        makeToasts("Welcome");
    }

    @OnClick(R.id.fb_loginBtn)
    public void fbClick() {
        LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this,
                Arrays.asList("public_profile", "user_friends"));
    }

    public static void saveToken(String shkap_token) {
        makeLog("TAG", shkap_token);
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

    public static void makeLog(String tag, String message) {
        Log.i(tag, message);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        startActivity(mMainIntent);
        finish();
    }
}
