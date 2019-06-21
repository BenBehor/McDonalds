package com.mac.ben.delivermee;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

public class CategoryMainActivity extends AppCompatActivity {

    private ArrayList<String> mTitles = new ArrayList<>();
    private ArrayList<String> mSubtitles = new ArrayList<>();
    private ArrayList<Integer> mImages = new ArrayList<>();
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        final Button leftBarBtn = findViewById(R.id.allergies_btn);
        final Button rightBarBtn = findViewById(R.id.ilike_btn);
        final ImageView logoIv = findViewById(R.id.logo);
        rightBarBtn.setVisibility(View.VISIBLE);
        leftBarBtn.setVisibility(View.VISIBLE);

        mContext = CategoryMainActivity.this;

        //basic data is on the device, just to load the data faster & without internet connection.
        String[] titleString = new String[] {"Burger","Beverages","Salads","McCafe","HappyMeal","Desserts"};
        String[] subtiteString = new String[] {"We’re well known for our burgers. 100% fresh beef delicious meet! \n You’ll find them all here.","Refreshing beverages and homemade shakes such as: Coke, Pepsi, Sprite, Fanta, Chocolate Shake, Strawberry Shake...","Make your lunch epic. Choose crispy or grilled chicken ,bacon & veggies with our seasonal salad.","Discover a world of irresistible flavours with our exciting range of delicious beverages and indulgent treats. Full of those little somethings that help brighten your day.","Kids love a Happy Meal We've teamed up with The Secret Life of Pets 2 to bring you awesome fun in every box. Plus, you can now choose a Fruit Bag instead of Fries!","Taste our perfect Ice Cream Cone with Flake Deliciously soft ice cream, in a crispy cone."};
        Integer[] imagesDrawable = new Integer[] {R.drawable.burger,R.drawable.beverages,R.drawable.salads,R.drawable.mccafe,R.drawable.happymeal,R.drawable.desserts};
        mTitles.addAll(Arrays.asList(titleString));
        mSubtitles.addAll(Arrays.asList(subtiteString));
        mImages.addAll(Arrays.asList(imagesDrawable));

        initRecyclerView();



        leftBarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Animator(leftBarBtn, logoIv);
                Intent intent = new Intent(mContext, PopupActivity.class);
                intent.putExtra("allergyOrilike", "allergy");
                mContext.startActivity(intent);
            }
        });


        rightBarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animator(rightBarBtn, logoIv);
                Intent intent = new Intent(mContext, PopupActivity.class);
                intent.putExtra("allergyOrilike", "ilike");
                mContext.startActivity(intent);
            }
        });
    }

    public void Animator(Button button, ImageView logo){
        Animation btnAnimation = AnimationUtils.loadAnimation(CategoryMainActivity.this,R.anim.rotate);
        Animation logoAnimation = AnimationUtils.loadAnimation(CategoryMainActivity.this,R.anim.fadeout_in);
        logo.startAnimation(logoAnimation);
        button.startAnimation(btnAnimation);

    }
    
    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.item_rcv);
        CategoryRCVAdapter adapter = new CategoryRCVAdapter(mImages, mTitles, mSubtitles, this);
        adapter.notifyItemChanged(mTitles.size(),mTitles);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
