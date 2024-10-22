package com.example.mysqlapplication.presenter;

import com.example.mysqlapplication.model.DatabaseManager;
import com.example.mysqlapplication.view.SignUpView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SignUpPresenter {
    private final SignUpView view;
    private final DatabaseManager databaseManager;
    private final ExecutorService executorService;

    public SignUpPresenter(SignUpView view) {
        this.view = view;
        this.databaseManager = new DatabaseManager();
        this.executorService = Executors.newSingleThreadExecutor(); // Khởi tạo Executor
    }

    public void handleSignUp(String id_kh, String name, String password, String rePassword) {
        if (id_kh.isEmpty() || name.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
            view.showEmptyFieldsError();
        } else if (!password.equals(rePassword)) {
            view.showSignUpFailed();
        } else if (databaseManager.isUserExists(id_kh)) {
            view.showUserExistsError();
        } else {
            // Thực hiện đăng ký trên luồng phụ
            executorService.execute(() -> {
                boolean success = databaseManager.register(id_kh, name, password);
                // Cập nhật giao diện trên luồng chính
                if (success) {
                    view.showSignUpSuccess();
                } else {
                    view.showSignUpFailed();
                }
            });
        }
    }
}
