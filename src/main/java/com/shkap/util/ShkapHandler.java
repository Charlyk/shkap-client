package com.shkap.util;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import okio.BufferedSource;

/**
 * Created by Eduard on 11.09.2015.
 */
public class ShkapHandler {

    private ShkapResponseHandler mHandler;
    private ShkapErrorHandler mErrorHandler;

    public ShkapHandler() {
        mHandler = new ShkapResponseHandler();
        mErrorHandler = new ShkapErrorHandler();
    }

    public void handleResponse(Response response) {
        int statusCode = response.code();
        if (mHandler.canHandle(statusCode))
            Result.ok(mHandler.handle(response));
        else if (mErrorHandler.canHandle(statusCode))
            Result.error(mErrorHandler.handle(response));
    }
}
