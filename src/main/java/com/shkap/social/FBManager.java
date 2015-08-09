package com.shkap.social;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.shkap.shkapsdk.ApiInfo;
import com.shkap.shkapsdk.ShkapClient;
import com.shkap.ui.LoginActivity;

import java.net.MalformedURLException;

/**
 * Created by Eduard on 06.08.2015.
 */
public class FBManager extends LoginActivity {

    public static CallbackManager getCallbackManager() {
        return CallbackManager.Factory.create();
    }

    public static FacebookCallback<LoginResult> getLoginResult() {
        return new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                try {
                    ShkapClient.register(loginResult.getAccessToken().getToken(),
                            ApiInfo.regToFacebook());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
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
