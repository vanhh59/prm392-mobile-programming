package com.example.calculator.contract;

public interface CalculatorContract {

    interface View {
        void showResult(String result);
        void showToastMessage(String message);
        void showErrorField(String field, String type);
    }

    interface Model {
        double add(double num1, double num2);
        double subtract(double num1, double num2);
        double multiply(double num1, double num2);
        double divide(double num1, double num2);
    }

    interface Presenter {
        void handleOnClickAddBtn(String num1Str, String num2Str);
        void handleOnClickSubtractBtn(String num1Str, String num2Str);
        void handleOnClickMultiplyBtn(String num1Str, String num2Str);
        void handleOnClickDivideBtn(String num1Str, String num2Str);
    }

}
