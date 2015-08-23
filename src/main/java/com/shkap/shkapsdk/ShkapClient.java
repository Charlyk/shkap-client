package com.shkap.shkapsdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shkap.ui.LoginActivity;
import com.shkap.util.Result;

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
            execute(post, o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String vkRegister(String vkToken) {
        try {
            HttpPost post = new HttpPost(ApiInfo.regWithVK().toString());
            byte[] bytes = mMapper.writeValueAsBytes(vkToken);
            post.setEntity(new ByteArrayEntity(bytes));
            LoginActivity.saveToken(mClient.execute(post, handler));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shkapToken;
    }

    public void fbRegister(String facebookToken) {
        try {
            HttpPost post = new HttpPost(ApiInfo.regWithFacebook().toString());
            byte[] bytes = mMapper.writeValueAsBytes(facebookToken);
            post.setEntity(new ByteArrayEntity(bytes));
            LoginActivity.saveToken(mClient.execute(post, handler));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void execute(HttpUriRequest request, Object o) throws IOException {
        String resultBody = mClient.execute(request, handler);
        handler.handleResponse(mClient.execute(request));
        Result<Object> result = new Result<>();
        result.handle(o, resultBody);
    }
}
