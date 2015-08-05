package com.shkap;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Eduard Albu on 05.08.2015.
 */
public class VKLoginHelper {

    private Context context;
    private Activity currentActivity;

    public VKLoginHelper(Context context, Activity currentActivity) {
        this.context = context;
        this.currentActivity = currentActivity;
        VKSdk.initialize(context);
    }

    // Проверяет если пользователь залогинился или нет
    public boolean isLoggedIn() {
        return VKSdk.wakeUpSession(context);
    }

    // Логинит пользователя в ВКонтакте
    public void logInWithVK() {
        VKSdk.login(currentActivity, VKScope.PHOTOS, VKScope.FRIENDS);
    }

    private void setUserData(String jsonData, UserData user) {
        //// TODO: 06.08.2015 Добавить в запрос вк почту
        try {
            JSONObject userData = new JSONObject(jsonData);
            JSONArray data = userData.getJSONArray("response");

            for (int i = 0; i < data.length(); i++) {
                JSONObject o = data.getJSONObject(i);
                user.setFirstName(o.getString("first_name"));
                user.setLastName(o.getString("last_name"));
                user.setPhoto(o.getString("photo_100"));
                //user.setEmail(o.getString("email"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void grabUserData(final UserData userData) {
        VKApi.users().get(VKParameters.from(VKApiConst.FIELDS, "photo_100,email")).executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                setUserData(response.responseString, userData);
                Log.i("TAG", response.responseString);
            }
        });
    }
}
