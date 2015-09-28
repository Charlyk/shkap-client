package com.shkap.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shkap.R;

/**
 * Created by Eduard on 28.09.2015.
 */
public class ThingPageImageAdapter extends RecyclerView.Adapter<ThingPageImageAdapter.ViewHolder> {

    public ThingPageImageAdapter() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thingdetail_image_viewer,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mMainImage.setImageResource(R.drawable.test_image);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mMainImage;
        public ViewHolder(View itemView) {
            super(itemView);
            mMainImage = (ImageView) itemView.findViewById(R.id.thingDetail_photo);
        }
    }
}
