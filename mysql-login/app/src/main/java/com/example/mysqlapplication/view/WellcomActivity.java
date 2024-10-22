package com.example.mysqlapplication.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mysqlapplication.R;

public class WellcomActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);

        // Hiển thị thông điệp đăng nhập thành công
        TextView welcomeText = findViewById(R.id.welcome_text);
        welcomeText.setText("Chào mừng bạn đến với ứng dụng!");
    }
}
