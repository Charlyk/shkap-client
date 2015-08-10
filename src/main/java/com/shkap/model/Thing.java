package com.shkap.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Eduard on 09.08.2015.
 */
public class Thing {

    //// TODO: 10.08.2015 Отредактировать после переписания ShkapClient
    private String mId;
    private String mTitle;
    private String mDescription;
    private String mPrice;
    private List<String> mAttachments;

    @JsonCreator
    public Thing(@JsonProperty("id") String id,
                 @JsonProperty("title") String title,
                 @JsonProperty("description") String description,
                 @JsonProperty("price") String price,
                 @JsonProperty("attachments") List<String> attachments)
    {
        this.mId = id;
        this.mTitle = title;
        this.mDescription = description;
        this.mPrice = price;
        this.mAttachments = attachments;
    }

    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getPrice() {
        return mPrice;
    }

    public List<String> getAttachments() {
        return mAttachments;
    }
}
