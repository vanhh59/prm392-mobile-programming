package com.example.apidemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apidemo.api.ApiResponse;
import com.example.apidemo.api.ApiService;
import com.example.apidemo.api.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        // Khởi tạo các thành phần giao diện
        EditText inputUsername = findViewById(R.id.input_username);
        EditText inputPassword = findViewById(R.id.input_password);
        Button buttonLogin = findViewById(R.id.button_login);
        Button buttonSignup = findViewById(R.id.button_signup);

        // Thiết lập sự kiện nhấn nút
        buttonLogin.setOnClickListener(v -> login(inputUsername, inputPassword));
        buttonSignup.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, SignupActivity.class)));
    }

    private void login(EditText inputUsername, EditText inputPassword) {
        // Lấy giá trị từ các trường nhập liệu
        final String username = inputUsername.getText().toString().trim();
        final String password = inputPassword.getText().toString().trim();

        // Kiểm tra thông tin người dùng
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Log các tham số gửi đi
        Log.d(TAG, "Sending parameters: action=login, acc_kh=" + username + ", pass_kh=" + password);

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        // Gọi API
        apiService.login("login", username, password).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();
                    String status = apiResponse.getStatus();
                    String message = apiResponse.getMessage();

                    Log.d(TAG, "Response: " + response.body()); // Log phản hồi từ server

                    if (status.equals("success")) {
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, WelcomeActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e(TAG, "Response error: " + response.message());
                    Toast.makeText(LoginActivity.this, "Response error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "Network error: " + t.getMessage());
                Toast.makeText(LoginActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
