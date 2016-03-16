package com.zireck.arithmetic.arithmeticchallenge.presenter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.zireck.arithmetic.arithmeticchallenge.model.Challenge;
import com.zireck.arithmetic.arithmeticchallenge.model.Move;
import com.zireck.arithmetic.arithmeticchallenge.model.enums.BottomSheetInput;
import com.zireck.arithmetic.arithmeticchallenge.model.enums.Difficulty;
import com.zireck.arithmetic.arithmeticchallenge.model.enums.GameState;
import com.zireck.arithmetic.arithmeticchallenge.view.GameView;
import com.zireck.arithmetic.arithmeticchallenge.view.View;
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

    public GamePresenter(Difficulty difficulty) {
        mDifficulty = difficulty;
        mGameState = GameState.STOPPED;
        mResult = new String();
    }

    @Override
    public <T extends View> void setView(@NonNull T view) {
        mView = ((GameView) view);
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
        if (mGameState == GameState.STOPPED) {
            mGameState = GameState.PLAYING;
            startGame();
        } else {
            mGameState = GameState.STOPPED;
            stopGame();
        }
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
    }

    private void startGame() {
        // TODO: start time

        mResult = new String();

        generateNewChallenge();
        mView.startGame();
    }

    private void stopGame() {
        // TODO: stop time

        mView.stopGame();
    }

    private void generateNewChallenge() {
        mChallenge = new Challenge(mDifficulty);

        mFragments = new ArrayList<>();
        mChallenge = new Challenge(Difficulty.EASY);
        for (Move move : mChallenge.getMoves()) {
            mFragments.add(MoveFragment.newInstance(move));
        }
        mFragments.add(ResultFragment.newInstance());

        mView.initViewPager(mFragments);
    }
}
