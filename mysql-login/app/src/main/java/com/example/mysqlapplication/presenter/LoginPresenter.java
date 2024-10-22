package com.example.mysqlapplication.presenter;

import android.util.Log;
import android.os.Handler;
import android.os.Looper;
import com.example.mysqlapplication.model.DatabaseManager;
import com.example.mysqlapplication.view.LoginView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginPresenter {
    private final LoginView view;
    private final DatabaseManager databaseManager;
    private final ExecutorService executorService;

    public LoginPresenter(LoginView view) {
        this.view = view;
        this.databaseManager = new DatabaseManager();
        this.executorService = Executors.newSingleThreadExecutor(); // Khởi tạo Executor
    }

    public void handleLogin(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            view.showEmptyFieldsError();
        } else {
            Log.d("LoginPresenter", "Attempting login with email: " + email); // Log email for debugging
            executorService.execute(() -> {
                try {
                    boolean success = databaseManager.login(email, password);
                    // Sử dụng Handler để chuyển tiếp về luồng chính
                    new Handler(Looper.getMainLooper()).post(() -> {
                        if (success) {
                            view.showLoginSuccess();
                        } else {
                            view.showLoginFailed();
                        }
                    });
                } catch (Exception e) {
                    // Ghi log lỗi và thông báo cho người dùng
                    Log.e("LoginPresenter", "Error during login", e);
                    new Handler(Looper.getMainLooper()).post(() -> view.showLoginError("Có lỗi xảy ra khi đăng nhập. Vui lòng thử lại."));
                }
            });
        }
    }
}
