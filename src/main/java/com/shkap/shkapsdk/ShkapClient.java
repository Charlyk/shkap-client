package com.shkap.shkapsdk;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.shkap.ui.LoginActivity;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Eduard Albu on 06.08.2015.
 */
public class ShkapClient {

    //// TODO: 10.08.2015 Переписать клиент чтобы он был максимально гибким
    //// TODO: 10.08.2015 Изучить обработку ошибок в OkHttp
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json");

    public void register(String accessToken, URL destination) {
        RequestBody body = RequestBody.create(MEDIA_TYPE, accessToken);
        Request request = new Request.Builder()
                .url(destination)
                .post(body)
                .build();
        connect(request);
    }

    public void post(String shkap_token, URL destination, String message) {
        RequestBody body = RequestBody.create(MEDIA_TYPE, message);
        Request request = new Request.Builder()
                .url(destination)
                .header(AUTHORIZATION, BEARER + shkap_token)
                .post(body)
                .build();
        connect(request);
    }

    private void connect(Request request) {
        LoginActivity activity = new LoginActivity();
        if (activity.isNetworkAvailable()) {
            OkHttpClient client = new OkHttpClient();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Log.e("TAG", e.toString());
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    final String token = response.body().string();
                    Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            LoginActivity.saveToken(token);
                        }
                    };
                    r.run();
                }
            });
        } else activity.alertUserAboutTheError();
    }
}
