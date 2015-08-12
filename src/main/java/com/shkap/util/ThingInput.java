package com.shkap.util;

import java.util.List;

/**
 * Created by Eduard Albu on 10.08.2015.
 */
public class ThingInput {
    private String mTitle;
    private String mDescription;
    private int mPrice;
    private List<String> mAttachments;

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
            return new ThingInput(this);
        }
    }

    private ThingInput(Builder builder) {
        mTitle = builder.mTitle;
        mDescription = builder.mDescription;
        mPrice = builder.mPrice;
        mAttachments = builder.mAttachments;
    }
}
