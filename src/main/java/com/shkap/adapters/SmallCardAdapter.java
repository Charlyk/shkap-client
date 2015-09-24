package com.shkap.adapters;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shkap.R;
import com.shkap.model.Thing;

import java.util.List;

/**
 * Created by Eduard on 24.09.2015.
 */
public class SmallCardAdapter extends RecyclerView.Adapter<SmallCardAdapter.ViewHolder> {

    private List<Thing> mThings;
    public SmallCardAdapter(List<Thing> things) {
        mThings = things;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.smal_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mSellerPhoto.setImageResource(R.drawable.like);
        holder.mSellerName.setText("Eduard Albu");
        holder.mStuffImage.setImageResource(R.drawable.test_image);
        holder.mLikesCounter.setText("132");
        holder.mPrice.setText("32 000 P");
        holder.mDescription.setText("This is the description of the stuff that we want to sell");
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mSellerPhoto;
        TextView mSellerName;
        ImageView mStuffImage;
        TextView mLikesCounter;
        TextView mPrice;
        TextView mDescription;
        public ViewHolder(View itemView) {
            super(itemView);
            mSellerPhoto = (ImageView) itemView.findViewById(R.id.smallCard_sellerPhoto);
            mSellerName = (TextView) itemView.findViewById(R.id.smallCard_sellerName);
            mStuffImage = (ImageView) itemView.findViewById(R.id.smallCard_mainImage);
            mLikesCounter = (TextView) itemView.findViewById(R.id.smallCard_likesLabel);
            mPrice = (TextView) itemView.findViewById(R.id.smallCard_priceLabel);
            mDescription = (TextView) itemView.findViewById(R.id.smallCard_descLabel);
        }
    }
}
