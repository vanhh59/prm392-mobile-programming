package com.example.calculator.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.calculator.contract.CalculatorContract;
import com.example.calculator.databinding.CalculatorMainBinding;
import com.example.calculator.model.CalculatorModel;
import com.example.calculator.presenter.CalculatorPresenter;

public class CalculatorMainActivity extends AppCompatActivity implements CalculatorContract.View {

    private CalculatorMainBinding binding;
    private CalculatorPresenter presenter;
    private final String NUM1_FIELD = "num1";
    private final String NUM2_FIELD = "num2";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = CalculatorMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new CalculatorPresenter(new CalculatorModel(), this);

        ViewCompat.setOnApplyWindowInsetsListener(binding.calculator, ((v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        }));


        binding.addBtn.setOnClickListener(v -> onClickAddBtn());
        binding.subtractBtn.setOnClickListener(v -> onClickSubtractBtn());
        binding.multiplyBtn.setOnClickListener(v -> onClickMultiplyBtn());
        binding.divideBtn.setOnClickListener(v -> onClickDivideBtn());

    }

    private void onClickAddBtn() {
        String num1 = binding.num1.getText().toString();
        String num2 = binding.num2.getText().toString();
        presenter.handleOnClickAddBtn(num1, num2);
    }

    private void onClickSubtractBtn() {
        String num1 = binding.num1.getText().toString();
        String num2 = binding.num2.getText().toString();
        presenter.handleOnClickSubtractBtn(num1, num2);
    }

    private void onClickMultiplyBtn() {
        String num1 = binding.num1.getText().toString();
        String num2 = binding.num2.getText().toString();
        presenter.handleOnClickMultiplyBtn(num1, num2);
    }

    private void onClickDivideBtn() {
        String num1 = binding.num1.getText().toString();
        String num2 = binding.num2.getText().toString();
        presenter.handleOnClickDivideBtn(num1, num2);
    }

    @Override
    public void showResult(String result) {
        binding.resultOutput.setText(result);
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(CalculatorMainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorField(String field, String type) {
        switch (field) {
            case NUM1_FIELD:
                binding.num1.setError(type);
                break;
            case NUM2_FIELD:
                binding.num2.setError(type);
                break;
        }
    }

}
