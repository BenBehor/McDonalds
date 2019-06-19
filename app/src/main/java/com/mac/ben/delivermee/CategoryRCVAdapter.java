package com.mac.ben.delivermee;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class CategoryRCVAdapter extends RecyclerView.Adapter<CategoryRCVAdapter.ViewHolder> {
    private ArrayList<Integer> mImages;
    private ArrayList<String> mTitles;
    private ArrayList<String> mSubTitles;
    private Context mContext;

    public CategoryRCVAdapter(ArrayList<Integer> mImages, ArrayList<String> mTitles, ArrayList<String> mSubTitles, Context mContext) {
        this.mImages = mImages;
        this.mTitles = mTitles;
        this.mSubTitles = mSubTitles;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        viewHolder.titleCategory.setText(mTitles.get(i));
        viewHolder.subTitleCategory.setText(mSubTitles.get(i));
        viewHolder.imageViewCategory.setImageResource(mImages.get(i));

        viewHolder.categoriesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, ItemActivity.class);
                intent.putExtra("title", mTitles.get(i));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageViewCategory;
        TextView titleCategory;
        TextView subTitleCategory;
        RelativeLayout categoriesLayout;

        public ViewHolder(View itemView){
            super(itemView);
            imageViewCategory = itemView.findViewById(R.id.item_iv);
            titleCategory = itemView.findViewById(R.id.item_title);
            subTitleCategory = itemView.findViewById(R.id.item_subtitle);
            categoriesLayout = itemView.findViewById(R.id.item_layout);
        }
    }

}
