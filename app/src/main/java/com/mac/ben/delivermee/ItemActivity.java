package com.mac.ben.delivermee;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {
    private ArrayList<String> mDishName = new ArrayList<>();
    private ArrayList<String> mDishDetails = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    protected ItemRCVAdapter adapter;
    protected DatabaseReference databaseReference;
    protected Button rightBarBtn;
    protected Button leftBarBtn;
    protected RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        rightBarBtn = findViewById(R.id.ilike_btn);
        leftBarBtn = findViewById(R.id.allergies_btn);
        rightBarBtn.setVisibility(View.INVISIBLE);
        leftBarBtn.setText(getString(R.string.back));

        getIncomingIntent();

        leftBarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }




    private void showBranches(final String categoryName) {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Categories");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adapter.notifyDataSetChanged();
                for(DataSnapshot ds : dataSnapshot.child(categoryName).getChildren()) {
                    mDishName.add((String) ds.getValue());
                }
                for(DataSnapshot ds : dataSnapshot.child(categoryName + "Logos").getChildren()){
                    mImages.add((String) ds.getValue());
                }
                for(DataSnapshot ds : dataSnapshot.child(categoryName + "Details").getChildren()) {
                    mDishDetails.add((String) ds.getValue());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void getIncomingIntent(){
        if(getIntent().hasExtra("title")){
            String categoryName = getIntent().getStringExtra("title");
            showBranches(categoryName);
            initRecyclerView();
        }
    }

    public void initRecyclerView(){
        recyclerView = findViewById(R.id.item_rcv);
        adapter = new ItemRCVAdapter(mDishName, mImages, mDishDetails, this);
        recyclerView.setAdapter(adapter);
        adapter.notifyItemChanged(mDishName.size(),mDishName);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.forceLayout();
    }
}

