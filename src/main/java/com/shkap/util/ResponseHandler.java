package com.shkap.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shkap.model.MySelf;
import com.shkap.model.Thing;
import com.shkap.model.User;
import com.shkap.ui.LoginActivity;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Eduard Albu on 10.08.2015.
 */
public class ResponseHandler {

    private final ObjectMapper mapper = new ObjectMapper();
    ////TODO: Как можно по другому это реализовать?
    public void handle(Response response, Object object) {
        try {
            if (object instanceof User) {
                User user = mapper.readValue(response.body().string(), User.class);
            } else if (object instanceof Thing) {
                Thing thing = mapper.readValue(response.body().string(), Thing.class);
            } else if (object instanceof MySelf) {
                MySelf mySelf = mapper.readValue(response.body().string(), MySelf.class);
            } else if (object instanceof LoginActivity) {
                LoginActivity.saveToken(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
