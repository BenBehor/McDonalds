package com.mac.ben.delivermee;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class PopupActivity extends Activity {

    TextView popupTitle;
    TextView popupSubtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_layout);

        popupTitle = findViewById(R.id.popup_title_tv);
        popupSubtitle = findViewById(R.id.popup_subtitle_tv);

        Intent intent = getIntent();
        String allergyOrOrder = intent.getStringExtra("allergyOrilike");

        if(allergyOrOrder.equals("ilike")){

            popupTitle.setText(getString(R.string.thatswhatilike));
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            String name = preferences.getString("ilike", "");
            popupSubtitle.setText(name);
        }

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        getWindow().setLayout((int)(displayMetrics.widthPixels * .6), (int)(displayMetrics.heightPixels * .6));
    }
}
