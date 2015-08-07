package com.shkap.social;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.shkap.data.ApiInfo;
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
                String accessToken = parseAccessToken(loginResult.getAccessToken().toString());
                try {
                    ShkapSRV.register(accessToken,
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
    public static String parseAccessToken(String accessToken) {
        String[] start = accessToken.split(":");
        String[] finish = start[1].split(" ");
        return finish[0];
    }
}
