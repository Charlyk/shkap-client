package com.shkap.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shkap.model.Thing;
import com.shkap.model.User;

import java.io.IOException;

/**
 * Created by Eduard Albu on 12.08.2015.
 */
public class Result<T> {

    private final ObjectMapper mMapper = new ObjectMapper();

    public void handle(T object, String json) {
        try {
            if (object instanceof User) mMapper.readValue(json, User.class);
            if (object instanceof Thing) mMapper.readValue(json, Thing.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
