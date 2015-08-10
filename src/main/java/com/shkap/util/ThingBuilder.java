package com.shkap.util;

/**
 * Created by Eduard Albu on 10.08.2015.
 */
public abstract class ThingBuilder {

    private String mTitle;
    private String mDescription;
    private int mPrice;

    public void setTitle(String title) {
        if (title == null) throw new NullPointerException("Need a title");
        mTitle = title;
    }

    public void setDescription(String description) {
        if (description == null) throw new NullPointerException("Need a description");
        mDescription = description;
    }

    public void setPrice(int price) {
        mPrice = price;
    }

    public void build() {
        ////TODO: Добавить метод post() чтобы при создании новой вещи она сразу отправлялась на сервер
    }
}
