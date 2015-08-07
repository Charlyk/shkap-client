package com.shkap.social;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.shkap.data.ApiInfo;
import com.shkap.ui.LoginActivity;

/**
 * Created by Eduard on 06.08.2015.
 */
public class FBManager extends LoginActivity {

    private static Context mContext;
    public FBManager(Context context) {
        mContext = context;
    }

    public static CallbackManager getCallbackManager() {
        return CallbackManager.Factory.create();
    }

    public static FacebookCallback<LoginResult> getLoginResult() {
        return new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(mContext, "Success", Toast.LENGTH_SHORT).show();
                LoginActivity.saveToken(ShkapSRV.register(loginResult.getAccessToken().toString(),
                        ApiInfo.regToFacebook()));
            }

            @Override
            public void onCancel() {
                Toast.makeText(mContext, "You canceled authorisation", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(mContext, "Ooops... there is an error", Toast.LENGTH_SHORT).show();
            }
        };
    }
}
