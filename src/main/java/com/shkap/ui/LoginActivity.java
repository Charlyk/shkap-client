package com.shkap.ui;

import android.app.Activity;
import android.content.Intent;
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
import com.facebook.login.LoginResult;
import com.shkap.R;
import com.shkap.social.VKManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;

public class LoginActivity extends Activity implements View.OnClickListener {

    private Intent mMainIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        FacebookSdk.sdkInitialize(this);
        VKManager vkManager = new VKManager(this, LoginActivity.this);
        mMainIntent = new Intent(this, MainActivity.class);
        if (vkManager.isLoggedIn()) {
            startActivity(mMainIntent);
            finish();
        }
        CallbackManager callbackManager = new CallbackManager() {
            @Override
            public boolean onActivityResult(int i, int i1, Intent intent) {
                Log.i("TAG", FacebookSdk.getClientToken());
                return true;
            }
        };
        Button mGoogleButton = (Button) findViewById(R.id.ggp_loginBtn);
        mGoogleButton.setOnClickListener(this);

        com.facebook.login.widget.LoginButton mFacebookButton = (com.facebook.login.widget.LoginButton)
                findViewById(R.id.fb_loginBtn);
        mFacebookButton.setReadPermissions("user_friends");
        mFacebookButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        });



        Button mVKButton = (Button) findViewById(R.id.vk_loginBtn);
        mVKButton.setOnClickListener(this);
    }

    public void makeToasts(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        VKManager.setAccesToken();
        Log.i("TAG", VKManager.getAccesToken());
        startActivity(mMainIntent);
        finish();
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ggp_loginBtn:
                makeToasts("Google");
                break;
            case R.id.vk_loginBtn:
                VKManager.logInWithVK();
                makeToasts("Welcome");
                break;
        }
    }
}
