package com.mac.ben.delivermee;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class ItemRCVAdapter extends RecyclerView.Adapter<ItemRCVAdapter.ViewHolder> {

    private ArrayList<String> mDishNames;
    private ArrayList<String> mDishDetails;
    private ArrayList<String> mImages;
    private Context mContext;
    private Animation btnAnimation;

    public ItemRCVAdapter(ArrayList<String> mTitleNames, ArrayList<String> mImages, ArrayList<String> mDishDetails, Context context) {
        this.mDishNames = mTitleNames;
        this.mDishDetails = mDishDetails;
        this.mContext = context;
        this.mImages = mImages;
        btnAnimation = AnimationUtils.loadAnimation(mContext,R.anim.zoomout_in);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.itemTitle.setText(mDishNames.get(i));
        viewHolder.itemSubtitle.setText(mDishDetails.get(i));

        Glide.with(viewHolder.itemView.getContext())
                .load(mImages.get(i))
                .placeholder(R.drawable.mcdonaldslogo)
                .error(R.drawable.mcdonaldslogo)
                .into(viewHolder.itemIv);

        viewHolder.item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewHolder.item_layout.startAnimation(btnAnimation);

                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                alert.setTitle("I'm Loving it!");
                alert.setMessage("Do you like " + viewHolder.itemTitle.getText());
                alert.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
                        String ilikeCurrently = preferences.getString("ilike", "");
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("ilike",ilikeCurrently + "\n" + viewHolder.itemTitle.getText().toString() );
                        editor.apply();
                    }
                });
                alert.setNegativeButton(R.string.no,null);
                alert.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return mDishNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
            ImageView itemIv;
            TextView itemTitle;
            TextView itemSubtitle;
            RelativeLayout item_layout;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                itemIv = itemView.findViewById(R.id.item_iv);
                itemTitle = itemView.findViewById(R.id.item_title);
                itemSubtitle = itemView.findViewById(R.id.item_subtitle);
                item_layout = itemView.findViewById(R.id.item_layout);
            }
        }
}
