package com.zireck.arithmetic.arithmeticchallenge.presenter;

import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.zireck.arithmetic.arithmeticchallenge.model.Challenge;
import com.zireck.arithmetic.arithmeticchallenge.model.Move;
import com.zireck.arithmetic.arithmeticchallenge.model.enums.BottomSheetInput;
import com.zireck.arithmetic.arithmeticchallenge.model.enums.Difficulty;
import com.zireck.arithmetic.arithmeticchallenge.model.enums.GameState;
import com.zireck.arithmetic.arithmeticchallenge.view.GameView;
import com.zireck.arithmetic.arithmeticchallenge.view.View;
import com.zireck.arithmetic.arithmeticchallenge.view.fragment.InitialValueFragment;
import com.zireck.arithmetic.arithmeticchallenge.view.fragment.MoveFragment;
import com.zireck.arithmetic.arithmeticchallenge.view.fragment.ResultFragment;

import java.util.ArrayList;
import java.util.List;

public class GamePresenter implements Presenter {

    private GameView mView;
    private GameState mGameState;
    private Difficulty mDifficulty;
    private Challenge mChallenge;
    List<Fragment> mFragments;

    private String mResult;

    private CountDownTimer mCountDownTimer;

    public GamePresenter(Difficulty difficulty) {
        mDifficulty = difficulty;
        mGameState = GameState.READY;
        mResult = new String();
    }

    @Override
    public <T extends View> void setView(@NonNull T view) {
        mView = ((GameView) view);
        mView.setColor(mDifficulty);
        generateNewChallenge();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    public boolean isGameOver() {
        return mGameState != GameState.PLAYING;
    }

    public void setDifficulty(Difficulty difficulty) {
        stopGame();

        mDifficulty = difficulty;
        generateNewChallenge();
        mView.setColor(difficulty);

        mGameState = GameState.READY;
        mView.readyGame();
    }

    public void pageSelected(int position) {
        if (position == mFragments.size() - 1) {
            mResult = new String();
            mView.setResult(mResult);
            mView.startInput();
        } else {
            mView.stopInput();
        }
    }

    public void onClickFab() {
        if (mGameState == GameState.READY) {
            startGame();
        } else if (mGameState == GameState.PLAYING) {
            stopGame();
        } else if (mGameState == GameState.STOPPED) {
            readyGame();
        }

/*
        if (mGameState == GameState.STOPPED) {
            startGame();
        } else {
            stopGame();
        }*/
    }

    public void bottomSheetInput(BottomSheetInput bottomSheetInput) {
        if (bottomSheetInput == BottomSheetInput.COLLAPSE) {
            mView.stopInput();
            mView.setPage(mFragments.size() - 2);
        } else if (bottomSheetInput == BottomSheetInput.BACKSPACE) {
            if (mResult != null && mResult.length() > 0) {
                mResult = mResult.substring(0, mResult.length()-1);
            }
            mView.setResult(mResult);
        }
    }

    public void bottomSheetInput(String input) {
        if (mResult.length() >= 5) {
            mView.notify("Error: Number too big.");
            return;
        }

        mResult += input;
        mView.setResult(mResult);

        checkIfValidResult();
    }

    private void checkIfValidResult() {
        if (mResult != null && !TextUtils.isEmpty(mResult)) {
            int result = Integer.parseInt(mResult);
            if (result == mChallenge.getResult()) {
                mView.notify("You win!");
                stopGame();
            }
        }
    }

    private void startGame() {
        mGameState = GameState.PLAYING;

        mResult = new String();

        generateNewChallenge();
        mView.startGame();

        System.out.println("k9d3 result = " + mChallenge.getResult());

        mCountDownTimer = new CountDownTimer(30000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                String timeLeft = String.valueOf(millisUntilFinished / 100);
                if (timeLeft.length() < 2) {
                    timeLeft = "0." + timeLeft;
                } else if (timeLeft.length() < 3) {
                    timeLeft = timeLeft.substring(0, 1) + "." + timeLeft.substring(1, timeLeft.length());
                } else {
                    timeLeft = timeLeft.substring(0, 2) + "." + timeLeft.substring(2, timeLeft.length());
                }

                mView.updateTimeLeft(timeLeft + "s");

                int seconds = (int) (millisUntilFinished / 100);
                float percentage = seconds * 100 / 300;
                mView.updateProgress(percentage);
            }

            @Override
            public void onFinish() {
                mView.updateTimeLeft("0.0s");
                mView.updateProgress(0);
                mView.notify("Game over!");
                stopGame();
            }
        }.start();
    }

    private void stopGame() {
        mGameState = GameState.STOPPED;

        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }

        mView.stopGame();
    }

    private void readyGame() {
        mGameState = GameState.READY;

        mView.readyGame();
    }

    private void generateNewChallenge() {
        mChallenge = new Challenge(mDifficulty);

        mFragments = new ArrayList<>();
        mChallenge = new Challenge(Difficulty.EASY);
        mFragments.add(InitialValueFragment.newInstance(mChallenge.getInitialValue(), mDifficulty.getColor()));
        for (Move move : mChallenge.getMoves()) {
            mFragments.add(MoveFragment.newInstance(move));
        }
        mFragments.add(ResultFragment.newInstance(mDifficulty.getColor()));

        mView.initViewPager(mFragments);
    }
}
