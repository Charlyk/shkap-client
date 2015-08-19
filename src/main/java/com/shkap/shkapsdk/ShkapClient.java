package com.shkap.shkapsdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shkap.ui.LoginActivity;
import com.shkap.util.Result;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by Eduard Albu on 06.08.2015.
 */
public class ShkapClient {

    //// TODO: 10.08.2015 Переписать клиент чтобы он был максимально гибким
    private final ObjectMapper mMapper = new ObjectMapper();
    private final HttpClient mClient = new DefaultHttpClient();
    private Result mResult = new Result();

    public <T> T post(String url, HttpEntity entity, ResponseHandler<T> handler) {
        String shkapToken = LoginActivity.getToken();
        HttpPost post = new HttpPost(url);
        try {
            byte[] bytes = mMapper.writeValueAsBytes(entity);
            post.setEntity(new ByteArrayEntity(bytes));
            post.setHeader("Authorisation", "Bearer " + shkapToken);
            return execute(post, handler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> T execute(HttpUriRequest request, ResponseHandler<T> handler) throws IOException {
            return mClient.execute(request, handler);
    }
}
