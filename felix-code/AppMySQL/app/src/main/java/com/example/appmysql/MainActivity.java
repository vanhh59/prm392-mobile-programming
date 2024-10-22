package com.example.appmysql;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmysql.Presenter.LoginPresenter;
import com.example.appmysql.View.LoginView;
import com.example.appmysql.View.SignUpActivity;
import com.example.appmysql.View.WellcomActivity;

public class MainActivity extends AppCompatActivity implements LoginView {

    private EditText editTextUsername, editTextPassword;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        try {
            // Kết nối các View từ layout
            editTextUsername = findViewById(R.id.logUsername);
            editTextPassword = findViewById(R.id.LogPassword);
            Button buttonLogin = findViewById(R.id.login);
            Button buttonRegister = findViewById(R.id.signin);

            // Khởi tạo Presenter
            presenter = new LoginPresenter(this);

            // Sự kiện nút Đăng nhập
            buttonLogin.setOnClickListener(v -> {
                try {
                    String email = editTextUsername.getText().toString().trim();
                    String password = editTextPassword.getText().toString().trim();

                    // Kiểm tra nếu các trường bị trống
                    if (email.isEmpty() || password.isEmpty()) {
                        showEmptyFieldsError();
                    } else {
                        presenter.handleLogin(email, password);
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Có lỗi xảy ra khi xử lý đăng nhập.", Toast.LENGTH_SHORT).show();
                }
            });

            // Sự kiện nút Đăng ký tài khoản
            buttonRegister.setOnClickListener(v -> {
                try {
                    startActivity(new Intent(MainActivity.this, SignUpActivity.class));
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Có lỗi xảy ra khi chuyển đến màn hình đăng ký.", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Có lỗi xảy ra khi khởi tạo giao diện.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showLoginSuccess() {
        try {
            startActivity(new Intent(MainActivity.this, WellcomActivity.class));
            finish(); // Kết thúc MainActivity nếu không quay lại
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Có lỗi xảy ra khi chuyển đến màn hình chào mừng.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showLoginFailed() {
        Toast.makeText(this, "Sai thông tin đăng nhập", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginError(String s) {

    }

    @Override
    public void showEmptyFieldsError() {
        Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
    }
}
