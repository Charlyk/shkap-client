package com.shkap.util;

import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Eduard on 11.09.2015.
 */
public class ShkapErrorHandler {

    private int startCode;
    private int endCode;
    private Error error;

    public ShkapErrorHandler() {
        startCode = 200;
        endCode = 307;
    }
    public boolean canHandle(int statusCode) {
        if (statusCode > endCode || statusCode < startCode)
            return true;
        else return false;
    }

    public Error handle(Response response) {
        error = new Error(new IllegalAccessError(response.message()));
        return error;
    }
}
