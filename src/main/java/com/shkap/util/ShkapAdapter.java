package com.shkap.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;

/**
 * Created by Eduard on 08.09.2015.
 */
public class ShkapAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    JSONArray dataSet;
    public ShkapAdapter(JSONArray dataSet) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mMainImage;
        public ImageView mSecondImage;
        public ImageView mThirdImage;
        public TextView mLikes;
        public TextView mPrice;
        public TextView mTitle;
        public TextView mDescription;

        public ViewHolder(View itemView, ImageView mainImage, ImageView secondImage,
                          ImageView thirdImage, TextView likes, TextView price,
                          TextView title, TextView description)
        {
            super(itemView);
            mMainImage = mainImage;
            mSecondImage = secondImage;
            mThirdImage = thirdImage;
            mLikes = likes;
            mPrice = price;
            mTitle = title;
            mDescription = description;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
