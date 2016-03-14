package com.zireck.arithmetic.arithmeticchallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zireck.arithmetic.arithmeticchallenge.model.Challenge;
import com.zireck.arithmetic.arithmeticchallenge.model.enums.Difficulty;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Challenge challenge = new Challenge(Difficulty.EASY);
        challenge.generateChallenge();
    }
}
