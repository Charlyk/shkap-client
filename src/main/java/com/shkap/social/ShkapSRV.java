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

    private static String shkap_token = null;
    private static String response;
    private static String userData;
    private static final String AUTHORIZATION = "Authorization ";
    private static final String BEARER = " Bearer ";


    public static String register(String accessToken, Uri destination) {
        try {
            RequestBody body = RequestBody.create(MediaType.parse(accessToken), accessToken);
            Log.i("TAG", destination.toString());
            LoginActivity.makeLog("TAG", body.toString());
            URL address = new URL(destination.toString());
            Request request = new Request.Builder()
                    .url(address)
                    .post(body)
                    .build();
            shkap_token = connect(request);
            LoginActivity.makeLog("TAG", shkap_token);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shkap_token;
    }

    public static String getUser(String shkap_token, Uri uri) {
        try {
            URL address = new URL(uri.toString());
            Request request = new Request.Builder()
                    .url(address)
                    .header(AUTHORIZATION, BEARER + shkap_token)
                    .build();
            userData = connect(request);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return userData;
    }

    private static String connect(Request request) {
        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("TAG", e.toString());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.i("TAG", response.body().string());
                shkap_token = response.body().string();
            }
        });
        return shkap_token;
    }
}
