package com.zireck.arithmetic.arithmeticchallenge.view;

import android.support.v4.app.Fragment;

import java.util.List;

public interface GameView extends View {
    void initViewPager(List<Fragment> fragments);
    void startGame();
    void stopGame();
    void startInput();
    void stopInput();
    void setPage(int page);
    void setResult(String result);
    void notify(String message);
    void updateTimeLeft(String timeLeft);
    void updateProgress(float progress);
    void setColor(int color);
}
