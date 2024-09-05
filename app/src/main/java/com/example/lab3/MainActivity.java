package com.example.lab3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tvInfo;
    EditText etInput;
    Button btnInput, btnEasy, btnNormal, btnHard;
    int targetNumber, max = 50;
    boolean isFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvInfo = findViewById(R.id.tvInfo);
        etInput = findViewById(R.id.etInput);
        btnInput = findViewById(R.id.btnInput);
        btnEasy = findViewById(R.id.btnEasy);
        btnNormal = findViewById(R.id.btnNormal);
        btnHard = findViewById(R.id.btnHard);

        targetNumber = generateRandom(0,50);
        isFinished = false;

        btnEasy.setOnClickListener(changeDifficulty);
        btnNormal.setOnClickListener(changeDifficulty);
        btnHard.setOnClickListener(changeDifficulty);
    }

    public void onClick(View v) {
        if (isFinished) {
            targetNumber = generateRandom(0, max);
            btnInput.setText(R.string.input_value);
        }

        String inputText = etInput.getText().toString();
        if (inputText.matches("")) Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_LONG).show();
        else {
            int guessedNumber = Integer.parseInt(inputText);
            if (guessedNumber < targetNumber) tvInfo.setText(R.string.behind);
            else if (guessedNumber > targetNumber) tvInfo.setText(R.string.ahead);
            else {
                tvInfo.setText(R.string.hit);
                isFinished = true;
                btnInput.setText(R.string.play_more);
            }
        }
    }

    View.OnClickListener changeDifficulty = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnEasy:
                max = 25;
                tvInfo.setText(R.string.try_to_guess_easy);
                break;
            case R.id.btnNormal:
                max = 50;
                tvInfo.setText(R.string.try_to_guess);
                break;
            case R.id.btnHard:
                max = 100;
                tvInfo.setText(R.string.try_to_guess_hard);
                break;
        }

            targetNumber = generateRandom(0, max);
            isFinished = false;
            btnInput.setText(R.string.input_value);
        }
    };

    public int generateRandom(int min, int max){
        Random random = new Random();
        return random.ints(min, max).findFirst().getAsInt();
    }

    @Override
    protected void onStop() {
        super.onStop();
        targetNumber = generateRandom(0,50);
        isFinished = false;
        tvInfo.setText(R.string.try_to_guess);
        btnInput.setText(R.string.input_value);
    }
}