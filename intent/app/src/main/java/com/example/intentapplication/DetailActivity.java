package com.example.intentapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView detailImage = findViewById(R.id.detail_image);
        TextView detailName = findViewById(R.id.detail_name);

        Intent intent = getIntent();
        String name = intent.getStringExtra("productName");
        int image = intent.getIntExtra("productImage", 0);

        detailImage.setImageResource(image);
        detailName.setText(name);
    }


}
