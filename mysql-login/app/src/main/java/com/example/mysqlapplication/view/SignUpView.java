package com.example.mysqlapplication.view;

public interface SignUpView {
    void showEmptyFieldsError();
    void showSignUpSuccess();
    void showSignUpFailed();
    void showUserExistsError();
}
