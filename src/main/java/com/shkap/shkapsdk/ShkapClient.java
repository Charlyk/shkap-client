package com.shkap.shkapsdk;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shkap.ui.LoginActivity;
import com.shkap.util.ShkapHandler;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Eduard Albu on 06.08.2015.
 */
public class ShkapClient<T> {

    private final OkHttpClient mClient;
    private final MediaType JSON;
    private final ObjectMapper mMapper;
    private final String SHKAP_TOKEN;
    private final ShkapHandler mShkapHandler;
    private T o;
    private Response mResponse;

    public ShkapClient() {
        mMapper = new ObjectMapper();
        mClient = new OkHttpClient();
        JSON = MediaType.parse("application/json; charset=utf-8");
        SHKAP_TOKEN = LoginActivity.getToken();
        mShkapHandler = new ShkapHandler();
        mResponse = null;
    }

    public void post(String url, T o) throws IOException {
        RequestBody body = RequestBody.create(JSON, makeJSON(o));
        Request request = new Request.Builder()
                .header("Authorisation", "Bearer " + SHKAP_TOKEN)
                .url(url)
                .post(body)
                .build();
        mShkapHandler.handleResponse(execute(request));
    }

    public String vkRegister(String vkToken) throws IOException {
        RequestBody body = RequestBody.create(JSON, vkToken);
        Request request = new Request.Builder()
                .url(ApiInfo.regWithVK())
                .post(body)
                .build();
        return execute(request).body().string();
    }

    public String fbRegister(String facebookToken) throws IOException {
        RequestBody body = RequestBody.create(JSON, facebookToken);
        Request request = new Request.Builder()
                .url(ApiInfo.regWithFacebook())
                .post(body)
                .build();
        return execute(request).body().string();
    }

    private Response execute(Request request) throws IOException {
        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("TAG", e.toString());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                mResponse = response;
            }
        });
        return mResponse;
    }

    private String makeJSON(T o) {
        String object = null;
        try {
            object = mMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return object;
    }
}
