package com.zireck.arithmetic.arithmeticchallenge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zireck.arithmetic.arithmeticchallenge.model.Challenge;
import com.zireck.arithmetic.arithmeticchallenge.model.Move;
import com.zireck.arithmetic.arithmeticchallenge.model.enums.Difficulty;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Challenge challenge = new Challenge(Difficulty.EASY);

        System.out.println("initial value: " + challenge.getInitialValue());

        List<Move> moves = challenge.getMoves();
        for (Move move : moves) {
            System.out.println(move.getText());
        }

        System.out.println("result: " + challenge.getResult());
    }
}
