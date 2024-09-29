package com.example.calculator.presenter;

import com.example.calculator.contract.CalculatorContract;

public class CalculatorPresenter implements CalculatorContract.Presenter {


    private CalculatorContract.Model model;
    private CalculatorContract.View view;
    private final String REQUIRE = "Require";
    private final String NUM1_FIELD = "num1";
    private final String NUM2_FIELD = "num2";

    public CalculatorPresenter(CalculatorContract.Model model, CalculatorContract.View view) {
        this.model = model;
        this.view = view;
    }

    public CalculatorContract.Model getModel() {
        return model;
    }

    public void setModel(CalculatorContract.Model model) {
        this.model = model;
    }

    public CalculatorContract.View getView() {
        return view;
    }

    public void setView(CalculatorContract.View view) {
        this.view = view;
    }


    @Override
    public void handleOnClickAddBtn(String num1Str, String num2Str) {
        if (!checkEmptyValue(num1Str, num2Str)) {
            view.showToastMessage("Please enter required field.");
            return;
        };

        try {
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);

            double result = roundResult(model.add(num1, num2));
            view.showResult(String.valueOf(result));
            view.showToastMessage("Calculating successfully.");

        }catch (Exception ex) {
            view.showToastMessage(ex.getMessage());
        }
    }

    @Override
    public void handleOnClickSubtractBtn(String num1Str, String num2Str) {
        if (!checkEmptyValue(num1Str, num2Str)) {
            view.showToastMessage("Please enter required field.");
            return;
        };

        try {
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);
            double result = roundResult(model.subtract(num1, num2));
            view.showResult(String.valueOf(result));
            view.showToastMessage("Calculating successfully.");

        }catch (Exception ex) {
            view.showToastMessage(ex.getMessage());
        }
    }

    @Override
    public void handleOnClickMultiplyBtn(String num1Str, String num2Str) {
        if (!checkEmptyValue(num1Str, num2Str)) {
            view.showToastMessage("Please enter required field.");
            return;
        };

        try {
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);

            double result = roundResult(model.multiply(num1, num2));
            view.showResult(String.valueOf(result));
            view.showToastMessage("Calculating successfully.");

        }catch (Exception ex) {
            view.showToastMessage(ex.getMessage());
        }
    }

    @Override
    public void handleOnClickDivideBtn(String num1Str, String num2Str) {
        if (!checkEmptyValue(num1Str, num2Str)) {
            view.showToastMessage("Please enter required field.");
            return;
        };

        try {
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);

            double result = roundResult(model.divide(num1, num2));
            view.showResult(String.valueOf(result));
            view.showToastMessage("Calculating successfully.");

        }catch (Exception ex) {
            view.showToastMessage(ex.getMessage());
        }
    }

    private boolean checkEmptyValue(String num1Str, String num2Str) {
        boolean flag = true;

        if (num1Str.isEmpty()) {
            view.showErrorField(NUM1_FIELD, REQUIRE);
            flag = false;
        }

        if (num2Str.isEmpty()) {
            view.showErrorField(NUM2_FIELD, REQUIRE);
            flag = false;
        }

        return flag;
    }

    private double roundResult(double result){
        return Math.round(result * 100) / 100d;
    }
}

