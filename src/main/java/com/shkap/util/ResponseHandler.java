package com.shkap.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shkap.model.User;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Eduard Albu on 10.08.2015.
 */
public class ResponseHandler {

    ObjectMapper mapper = new ObjectMapper();
    public void handle(Response response) {
        try {
            User user = mapper.readValue(response.body().string(), User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
