package com.example.calculator.model;

import com.example.calculator.contract.CalculatorContract;

public class CalculatorModel implements CalculatorContract.Model {
    @Override
    public double add(double num1, double num2) {
        return num1 + num2;
    }

    @Override
    public double subtract(double num1, double num2) {
        return num1 - num2;
    }

    @Override
    public double multiply(double num1, double num2) {
        return num1 * num2;
    }

    @Override
    public double divide(double num1, double num2) {
        if (num2 == 0) {
            throw new ArithmeticException("Num 2 cannot be 0 for division calculation.");
        }else {
            return num1 / num2;
        }
    }
}

