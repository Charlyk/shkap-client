package com.shkap.social;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.shkap.data.ApiInfo;
import com.shkap.ui.LoginActivity;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;

/**
 * Created by Eduard Albu on 05.08.2015.
 */
public class VKManager extends LoginActivity {

    private static Activity currentActivity;

    public VKManager(Context context, Activity currentActivity) {
        VKManager.currentActivity = currentActivity;
        VKSdk.initialize(context);
    }

    // Логинит пользователя в ВКонтакте
    public static void logInWithVK() {
        VKSdk.login(currentActivity, VKScope.PHOTOS, VKScope.FRIENDS);
        String accessToken = VKAccessToken.currentToken().accessToken;
        //LoginActivity.makeLog("TAG", VKAccessToken.currentToken().accessToken);
        //ShkapSRV.register(accessToken, ApiInfo.regToVK());
    }


}
