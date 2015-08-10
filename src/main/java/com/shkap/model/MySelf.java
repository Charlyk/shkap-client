package com.shkap.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shkap.ui.LoginActivity;

/**
 * Created by Eduard Albu on 10.08.2015.
 */
public class MySelf {
    private final String mId;
    private final String mFirstName;
    private final String mLastName;
    private final String mPhoto;


    @JsonCreator
    public MySelf(@JsonProperty("id") String id,
                  @JsonProperty("first_name") String firstName,
                  @JsonProperty("last_name") String lastName,
                  @JsonProperty("photo") String photo)
    {
        mId = id;
        mFirstName = firstName;
        mLastName = lastName;
        mPhoto = photo;
    }

    public String getId() {
        return mId;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getPhoto() {
        return mPhoto;
    }
}
