package com.example.optionmenu;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.optionmenu.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    // Khai bao binding
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Khoi tao binding cho layout
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Thiet lap Toolbar cho Activity
        setSupportActionBar(binding.toolbar);

        // Dat tieu de cho Toolbar
        Objects.requireNonNull(getSupportActionBar()).setTitle("Option Menu");

        // Mau nen cho Toolbar
        binding.toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
    }

    // Tao Option Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return true;
    }

    // Xu ly su kien khi chon item trong menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_share) {
            Toast.makeText(this, "Ban da chon item Share", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_search) {
            Toast.makeText(this, "Ban da chon item Search", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_email) {
            Toast.makeText(this, "Ban da chon item Email", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_phone) {
            Toast.makeText(this, "Ban da chon item Phone", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}