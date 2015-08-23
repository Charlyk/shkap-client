package com.shkap.shkapsdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shkap.model.Thing;
import com.shkap.model.User;
import com.shkap.ui.LoginActivity;
import com.shkap.util.Result;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by Eduard Albu on 06.08.2015.
 */
public class ShkapClient {

    private final ObjectMapper mMapper;
    private final HttpClient mClient;
    private String shkapToken = null;
    private ResponseHandler<String> handler;

    public ShkapClient() {
        mMapper = new ObjectMapper();
        mClient = new DefaultHttpClient();
        handler = new BasicResponseHandler();
    }

    public void post(String url, Object o) {
        String shkapToken = LoginActivity.getToken();
        HttpPost post = new HttpPost(url);
        try {
            post.setHeader("Authorisation", "Bearer " + shkapToken);
            execute(post, handler, o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String vkRegister(String token) {
        try {
            HttpPost post = new HttpPost(String.valueOf(ApiInfo.regWithVK()));
            byte[] bytes = mMapper.writeValueAsBytes(token);
            post.setEntity(new ByteArrayEntity(bytes));
            shkapToken = mClient.execute(post, handler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shkapToken;
    }

    public String fbRegister(String facebookToken) {
        try {
            HttpPost post = new HttpPost(String.valueOf(ApiInfo.regWithFacebook()));
            byte[] bytes = mMapper.writeValueAsBytes(facebookToken);
            post.setEntity(new ByteArrayEntity(bytes));
            shkapToken = mClient.execute(post, handler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shkapToken;
    }

    private void execute(HttpUriRequest request, ResponseHandler handler, Object o) throws IOException {
        String resultBody = mClient.execute(request, handler).toString();
        Result<Object> result = new Result<>();
        result.handle(o, resultBody);
    }
}
