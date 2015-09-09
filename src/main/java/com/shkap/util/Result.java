package com.shkap.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shkap.model.Thing;
import com.shkap.model.User;

import java.io.IOException;

/**
 * Created by Eduard Albu on 12.08.2015.
 */
public class Result<T> {

    private final T mValue;
    private final Error mError;

    public Result (T value, Error error) {
        this.mValue = value;
        this.mError = error;
    }

    public static <T> Result<T> ok (T value) {
        return new Result<>(value, null);
    }

    public static <T> Result<T> error (Error error) {
        return new Result<>(null, error);
    }

    public T getValue() {
        return mValue;
    }

    public Error getError() {
        return mError;
    }
}
