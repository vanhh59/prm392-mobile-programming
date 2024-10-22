package com.example.appmysql.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmysql.MainActivity;
import com.example.appmysql.Presenter.SignUpPresenter;
import com.example.appmysql.R;

public class SignUpActivity extends AppCompatActivity implements SignUpView {

    private EditText editTextId, editTextName, editTextPassword, editTextRePassword;
    private SignUpPresenter presenter;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.sign_layout); // Layout đăng ký

            // Kết nối các View từ layout
            editTextId = findViewById(R.id.ten_kh); // ID_kh
            editTextName = findViewById(R.id.ten_kh); // ten_kh
            editTextPassword = findViewById(R.id.pass_kh); // pass_kh
            editTextRePassword = findViewById(R.id.repass_kh); // repass_kh
            Button buttonSignUp = findViewById(R.id.button_signup); // ID cho nút đăng ký

            // Khởi tạo Presenter
            presenter = new SignUpPresenter(this);

            // Sự kiện nút Đăng ký
            buttonSignUp.setOnClickListener(this::onClick);
        } catch (Exception e) {
            Log.e("SignUpActivity", "Error during initialization", e);
            Toast.makeText(this, "Lỗi khởi tạo giao diện. Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showSignUpSuccess() {
        Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void showSignUpFailed() {
        Toast.makeText(this, "Đăng ký thất bại. Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserExistsError() {
        Toast.makeText(this, "Người dùng đã tồn tại. Vui lòng chọn tên khác!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmptyFieldsError() {
        Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
    }

    private void onClick(View v) {
        try {
            String id = editTextId.getText().toString().trim();
            String name = editTextName.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String rePassword = editTextRePassword.getText().toString().trim();

            // Gọi phương thức handleSignUp với cả 4 tham số
            presenter.handleSignUp(id, name, password, rePassword);
        } catch (Exception e) {
            Log.e("SignUpActivity", "Error during sign up", e);
            Toast.makeText(this, "Có lỗi xảy ra. Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
        }
    }
}
