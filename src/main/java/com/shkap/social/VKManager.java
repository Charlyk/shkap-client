package com.shkap.social;

import android.app.Activity;
import android.content.Context;

import com.shkap.ui.LoginActivity;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;

import java.net.MalformedURLException;

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
    public static void logInWithVK() throws MalformedURLException {
        VKSdk.login(currentActivity, VKScope.PHOTOS, VKScope.FRIENDS);
    }


}
