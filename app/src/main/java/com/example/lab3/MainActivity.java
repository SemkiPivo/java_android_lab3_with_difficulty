package com.example.lab3;

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
    Button btnInput;
    int targetNumber;
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

        targetNumber = generateRandom(0,50);
        isFinished = false;
    }



    public void onClick(View v) {
        if (isFinished) {
            targetNumber = generateRandom(0, 50);
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

    public int generateRandom(int min, int max){
        Random random = new Random();
        return random.ints(min, max).findFirst().getAsInt();
    }
}