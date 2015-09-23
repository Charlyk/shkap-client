package com.shkap.util;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shkap.R;
import com.shkap.model.Thing;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduard on 08.09.2015.
 */
public class ShkapAdapter extends RecyclerView.Adapter<ShkapAdapter.ViewHolder> {

    List<Thing> mThings;
    public ShkapAdapter(List<Thing> things) {
        mThings = things;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mMainImage;
        ImageView mSecondImage;
        ImageView mThirdImage;
        TextView mLikes;
        TextView mPrice;
        TextView mTitle;
        TextView mDescription;
        CardView mCardView;

        ViewHolder(View itemView) {
            super(itemView);
            mMainImage = (ImageView) itemView.findViewById(R.id.big_card_image);
            mSecondImage = (ImageView) itemView.findViewById(R.id.big_card_secodImage);
            mThirdImage = (ImageView) itemView.findViewById(R.id.big_card_otherImages);
            mLikes = (TextView) itemView.findViewById(R.id.big_card_likeLabel);
            mPrice = (TextView) itemView.findViewById(R.id.big_card_priceLabel);
            mTitle = (TextView) itemView.findViewById(R.id.big_card_titleLabel);
            mDescription = (TextView) itemView.findViewById(R.id.big_card_descLabel);
            mCardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_img_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mPrice.setText("500 USD");
        holder.mDescription.setText("Nu shtiu cce sa scriu aici dar trebuie de scris mult");
        holder.mTitle.setText("Plapuma roshie");
        holder.mLikes.setText("100");
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
