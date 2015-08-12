package com.shkap.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Eduard on 09.08.2015.
 */
public class Thing {

    private String mTitle;
    private String mDescription;
    private int mPrice;
    private List<String> mAttachments;

    @JsonCreator
    public Thing(@JsonProperty("title") String title,
                 @JsonProperty("description") String description,
                 @JsonProperty("price") int price,
                 @JsonProperty("attachments") List<String> attachments)
    {
        this.mTitle = title;
        this.mDescription = description;
        this.mPrice = price;
        this.mAttachments = attachments;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getPrice() {
        return mPrice;
    }

    public List<String> getAttachments() {
        return mAttachments;
    }
}
