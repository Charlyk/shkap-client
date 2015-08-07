package com.shkap.social;

import android.net.Uri;
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
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Eduard Albu on 06.08.2015.
 */
public class ShkapSRV {

    private static final String AUTHORIZATION = "Authorization ";
    private static final String BEARER = " Bearer ";

    public static void register(String accessToken, URL destination) {
        RequestBody body = RequestBody.create(MediaType.parse(accessToken), accessToken);
        Log.i("TAG", accessToken);
        Log.i("TAG", body.toString());
        Log.i("TAG", destination.toString());
        Request request = new Request.Builder()
                .url(destination)
                .post(body)
                .build();
        Log.i("TAG", request.toString());
        connect(request);
    }

    public static void getUser(String shkap_token, URL destination) {
        Request request = new Request.Builder()
                .url(destination)
                .header(AUTHORIZATION, BEARER + shkap_token)
                .build();
    }

    private static void connect(Request request) {
        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("TAG", e.toString());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.i("LOGIN", response.body().string());
            }
        });
    }
}
