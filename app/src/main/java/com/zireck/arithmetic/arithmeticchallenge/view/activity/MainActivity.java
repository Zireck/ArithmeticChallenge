package com.zireck.arithmetic.arithmeticchallenge.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zireck.arithmetic.arithmeticchallenge.R;
import com.zireck.arithmetic.arithmeticchallenge.model.Challenge;
import com.zireck.arithmetic.arithmeticchallenge.model.Move;
import com.zireck.arithmetic.arithmeticchallenge.model.enums.Difficulty;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.startGame)
    Button mStartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Challenge challenge = new Challenge(Difficulty.EASY);

        System.out.println("initial value: " + challenge.getInitialValue());

        List<Move> moves = challenge.getMoves();
        for (Move move : moves) {
            System.out.println(move.getText());
        }

        System.out.println("result: " + challenge.getResult());

        mStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GameActivity.class));
            }
        });
    }
}
