package com.mac.ben.delivermee;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;

public class CategoryMainActivity extends AppCompatActivity {

    private ArrayList<String> mTitles = new ArrayList<>();
    private ArrayList<String> mSubtitles = new ArrayList<>();
    private ArrayList<Integer> mImages = new ArrayList<>();
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Button leftBarBtn = findViewById(R.id.allergies_btn);
        Button rightBarBtn = findViewById(R.id.ilike_btn);
        rightBarBtn.setVisibility(View.VISIBLE);
        leftBarBtn.setVisibility(View.VISIBLE);

        mContext = CategoryMainActivity.this;

        mTitles.add("Burger");
        mTitles.add("Beverages");
        mTitles.add("Salads");
        mTitles.add("McCafe");
        mTitles.add("HappyMeal");
        mTitles.add("Desserts");
        mSubtitles.add("Burger Meals");
        mSubtitles.add("Beverages to drink");
        mSubtitles.add("Subway - NEW Branch near you!");
        mSubtitles.add("Pasta Basta - free deliver monday's");
        mSubtitles.add("Ushi Ushi - 20% Discount available!");
        mSubtitles.add("DeliCream, Golda, Max Brenner...");
        mImages.add(R.drawable.burger);
        mImages.add(R.drawable.beverages);
        mImages.add(R.drawable.salads);
        mImages.add(R.drawable.mccafe);
        mImages.add(R.drawable.happymeal);
        mImages.add(R.drawable.desserts);

        initRecyclerView();



        leftBarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, PopupActivity.class);
                intent.putExtra("allergyOrilike", "allergy");
                mContext.startActivity(intent);
            }
        });


        rightBarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, PopupActivity.class);
                intent.putExtra("allergyOrilike", "ilike");
                mContext.startActivity(intent);
            }
        });
    }
    
    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.item_rcv);
        CategoryRCVAdapter adapter = new CategoryRCVAdapter(mImages, mTitles, mSubtitles, this);
        adapter.notifyItemChanged(mTitles.size(),mTitles);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
