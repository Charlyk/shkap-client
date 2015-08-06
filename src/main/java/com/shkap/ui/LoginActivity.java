package com.shkap.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.shkap.R;
import com.shkap.model.ViewInitializer;
import com.shkap.social.FBManager;
import com.shkap.social.VKManager;

public class LoginActivity extends ViewInitializer implements View.OnClickListener {

    private Intent mMainIntent;
    private Button mGoogleButton;
    private Button mVKButton;
    private CallbackManager callbackManager;
    private com.facebook.login.widget.LoginButton mFacebookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initViews();
        callbackManager = FBManager.getCallbackManager();
        FBManager fbManager = new FBManager(LoginActivity.this);
        VKManager vkManager = new VKManager(this, LoginActivity.this);
        mMainIntent = new Intent(this, MainActivity.class);

        if (vkManager.isLoggedIn()) {
            startActivity(mMainIntent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        VKManager.setAccesToken();
        callbackManager.onActivityResult(requestCode, resultCode, data);
        Log.i("TAG", VKManager.getAccesToken());
        startActivity(mMainIntent);
        finish();
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void initToolbar() {}

    @Override
    protected void initViews() {
        mGoogleButton = (Button) findViewById(R.id.ggp_loginBtn);
        mGoogleButton.setOnClickListener(this);

        mFacebookButton = (com.facebook.login.widget.LoginButton)
                findViewById(R.id.fb_loginBtn);
        mFacebookButton.setReadPermissions("user_friends");
        mFacebookButton.registerCallback(FBManager.getCallbackManager(),
                FBManager.getLoginResul());

        mVKButton = (Button) findViewById(R.id.vk_loginBtn);
        mVKButton.setOnClickListener(this);
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

    public void makeToasts(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
