package com.shkap.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shkap.model.Thing;
import com.shkap.model.User;

import java.io.IOException;

/**
 * Created by Eduard Albu on 12.08.2015.
 */
public class Result {

    private final String mValue;
    private final Error mError;

    public Result(String value, Error error) {
        this.mValue = value;
        this.mError = error;
    }

    public static Result ok(String value) {
        return new Result(value, null);
    }

    public static Result error(Error error) {
        return new Result(null, error);
    }

    public String getValue() {
        return mValue;
    }

    public Error getError() {
        return mError;
    }

    public boolean isSuccessful() {
        return mValue != null;
    }

    public boolean hasFailed() {
        return mError != null;
    }
}
