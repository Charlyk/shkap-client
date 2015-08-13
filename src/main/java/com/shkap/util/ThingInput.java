package com.shkap.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Eduard Albu on 10.08.2015.
 */
public class ThingInput {
    private final String mTitle;
    private final String mDescription;
    private final int mPrice;
    private final List<String> mAttachments;

    @JsonCreator
    private ThingInput(@JsonProperty("title") String title,
                       @JsonProperty("description") String description,
                       @JsonProperty("price") int price,
                       @JsonProperty("attachments") List<String> attachments) {
        mTitle = title;
        mDescription = description;
        mPrice = price;
        mAttachments = attachments;
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

    public static class Builder {
        private String mTitle;
        private String mDescription;
        private int mPrice;
        private List<String> mAttachments;

        public Builder title(String title) {
            mTitle = title;
            return this;
        }

        public Builder description(String description) {
            mDescription = description;
            return this;
        }

        public Builder price(int price) {
            mPrice = price;
            return this;
        }

        public Builder attachments(List<String> attachments) {
            mAttachments = attachments;
            return this;
        }

        public ThingInput build() {
            return new ThingInput(this.mTitle, this.mDescription,
                    this.mPrice, this.mAttachments);
        }
    }
}
