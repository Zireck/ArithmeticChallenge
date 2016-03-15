package com.zireck.arithmetic.arithmeticchallenge.view.activity;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.viewpagerindicator.CirclePageIndicator;
import com.zireck.arithmetic.arithmeticchallenge.R;
import com.zireck.arithmetic.arithmeticchallenge.model.Challenge;
import com.zireck.arithmetic.arithmeticchallenge.model.Move;
import com.zireck.arithmetic.arithmeticchallenge.model.enums.Difficulty;
import com.zireck.arithmetic.arithmeticchallenge.view.adapter.MovesPagerAdapter;
import com.zireck.arithmetic.arithmeticchallenge.view.fragment.MoveFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameActivity extends AppCompatActivity {

    @Bind(R.id.viewpager_moves)
    ViewPager mViewPagerMoves;
    @Bind(R.id.indicator)
    CirclePageIndicator mIndicator;
    @Bind(R.id.bottom_sheet)
    LinearLayout mBottomSheet;

    private Challenge mChallenge;
    private MovesPagerAdapter mMovesPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        final BottomSheetBehavior bsb = BottomSheetBehavior.from(mBottomSheet);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bsb.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    bsb.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    bsb.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
            }
        });

        // TODO: difficulty
        List<Fragment> moves = new ArrayList<>();
        mChallenge = new Challenge(Difficulty.EASY);
        for (Move move : mChallenge.getMoves()) {
            moves.add(MoveFragment.newInstance(move));
        }

        mMovesPagerAdapter = new MovesPagerAdapter(getSupportFragmentManager(), moves);

        mViewPagerMoves.setAdapter(mMovesPagerAdapter);
        mIndicator.setViewPager(mViewPagerMoves);
    }

}
