package com.shkap.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Eduard on 06.08.2015.
 */
public class User {

    private final String mUserId;
    private final String mFirstName;
    private final String mLastName;
    private final String mPhoto;

    @JsonCreator
    public User (@JsonProperty("id") String id,
                 @JsonProperty("first_name") String firstName,
                 @JsonProperty("last_name") String lastName,
                 @JsonProperty("photo") String photo)
    {
        this.mUserId = id;
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mPhoto = photo;
    }

    public String getUserId() {
        return mUserId;
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
