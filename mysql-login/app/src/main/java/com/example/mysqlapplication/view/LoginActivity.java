package com.example.mysqlapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mysqlapplication.presenter.LoginPresenter;
import com.example.mysqlapplication.databinding.LoginLayoutBinding;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private LoginLayoutBinding binding;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            binding = LoginLayoutBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            presenter = new LoginPresenter(this);

            // Cập nhật ID cho nút đăng nhập
            binding.login.setOnClickListener(v -> {
                String email = binding.logUsername.getText().toString().trim();
                String password = binding.logPassword.getText().toString().trim();
                try {
                    presenter.handleLogin(email, password);
                } catch (Exception e) {
                    Log.e("LoginActivity", "Error during login", e);
                    Toast.makeText(this, "Có lỗi xảy ra. Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                }
            });

            // Cập nhật ID cho nút chuyển sang màn hình đăng ký
            binding.signin.setOnClickListener(v -> {
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                // log
                Log.d("LoginActivity", "Switch to SignUpActivity");
            });
        } catch (Exception e) {
            Log.e("LoginActivity", "Error during initialization", e);
            Toast.makeText(this, "Lỗi khởi tạo giao diện. Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showEmptyFieldsError() {
        Toast.makeText(this, "Email và mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginSuccess() {
        Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, WellcomActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showLoginFailed() {
        Toast.makeText(this, "Đăng nhập thất bại. Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginError(String s) {

    }
}
