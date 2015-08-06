package com.shkap.social;

import android.app.Activity;
import android.content.Context;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;

/**
 * Created by Eduard Albu on 05.08.2015.
 */
public class VKManager {

    private Context context;
    private static Activity currentActivity;
    private static String mAccesToken;



    public VKManager(Context context, Activity currentActivity) {
        this.context = context;
        VKManager.currentActivity = currentActivity;
        VKSdk.initialize(context);
    }

    // Проверяет если пользователь залогинился или нет
    public boolean isLoggedIn() {
        return VKSdk.wakeUpSession(context);
    }

    // Логинит пользователя в ВКонтакте
    public static void logInWithVK() {
        VKSdk.login(currentActivity, VKScope.PHOTOS, VKScope.FRIENDS);
    }

    public static void setAccesToken() {
        mAccesToken = VKAccessToken.currentToken().accessToken;
    }

    public static String getAccesToken() {
        return mAccesToken;
    }
}
