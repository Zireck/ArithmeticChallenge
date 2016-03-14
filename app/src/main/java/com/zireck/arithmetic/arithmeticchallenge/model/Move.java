package com.zireck.arithmetic.arithmeticchallenge.model;

import com.zireck.arithmetic.arithmeticchallenge.model.enums.Operations;

public class Move {

    private Operations operation;
    private double factor;
    private String text;
    private int result;

    public Move(Operations operation, double factor, String text, int result) {
        this.operation = operation;
        this.factor = factor;
        this.text = text;
        this.result = result;
    }

    public Operations getOperation() {
        return operation;
    }

    public double getFactor() {
        return factor;
    }

    public String getText() {
        return text;
    }

    public int getResult() {
        return result;
    }
}
