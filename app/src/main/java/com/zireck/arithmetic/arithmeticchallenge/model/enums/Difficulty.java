package com.zireck.arithmetic.arithmeticchallenge.model.enums;

import com.zireck.arithmetic.arithmeticchallenge.R;

public enum Difficulty {
    EASY (R.color.easy),
    MEDIUM (R.color.medium),
    HARD (R.color.hard);

    private int mColor;

    Difficulty(int color) {
        mColor = color;
    }

    public int getColor() {
        return mColor;
    }
}
