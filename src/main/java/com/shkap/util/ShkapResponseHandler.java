package com.shkap.util;

import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Eduard on 09.09.2015.
 */
public class ShkapResponseHandler {

    private int startCode;
    private int endCode;
    private String value;

    public ShkapResponseHandler() {
        startCode = 200;
        endCode = 307;
    }
    public boolean canHandle(int statusCode) {
        if (statusCode < endCode || statusCode > startCode)
            return true;
        else return false;
    }

    public String handle(Response response) {
        try {
            value = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}
