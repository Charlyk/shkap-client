package com.shkap.social;

import android.util.Log;

import com.shkap.data.ApiInfo;
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
public class Authenticate {
    private final OkHttpClient client = new OkHttpClient();

    public void authUser(String accesToken) {
        try {
            RequestBody body = RequestBody.create(MediaType.parse(accesToken), accesToken);
            Log.i("TAG", ApiInfo.regToVK().toString());
            URL address = new URL(ApiInfo.regToVK().toString());
            Request request = new Request.Builder()
                    .url(address)
                    .post(body)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Log.e("TAG", e.toString());
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    Log.i("TAG", response.body().string());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
