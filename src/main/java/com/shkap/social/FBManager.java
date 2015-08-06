package com.shkap.social;

import android.content.Context;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.shkap.data.ApiInfo;

/**
 * Created by Eduard on 06.08.2015.
 */
public class FBManager {

    public FBManager(Context context) {
        FacebookSdk.sdkInitialize(context);
    }

    public static CallbackManager getCallbackManager() {
        return CallbackManager.Factory.create();
    }

    public static FacebookCallback<LoginResult> getLoginResul() {
        return new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                ShkapSRV.register(loginResult.getAccessToken().toString(), ApiInfo.regToFb());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        };
    }
}
