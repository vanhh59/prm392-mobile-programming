package com.example.appmysql.View;

public interface LoginView {
    void showEmptyFieldsError();
    void showLoginSuccess();
    void showLoginFailed();
    void showLoginError(String s);
}
