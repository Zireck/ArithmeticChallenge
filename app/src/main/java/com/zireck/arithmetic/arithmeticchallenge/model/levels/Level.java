package com.zireck.arithmetic.arithmeticchallenge.model.levels;

import com.zireck.arithmetic.arithmeticchallenge.model.enums.Difficulty;
import com.zireck.arithmetic.arithmeticchallenge.model.enums.Operations;

import java.util.Map;

public class Level {

    private Difficulty difficulty;
    private int steps = 9;
    private int startMin;
    private int startMax;
    private Map<Operations, Integer> moves;

    Level(Difficulty difficulty, int steps, int startMin, int startMax, Map<Operations, Integer> moves) {
        this.difficulty = difficulty;
        this.steps = steps;
        this.startMin = startMin;
        this.startMax = startMax;
        this.moves = moves;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getSteps() {
        return steps;
    }

    public int getStartMin() {
        return startMin;
    }

    public int getStartMax() {
        return startMax;
    }

    public Map<Operations, Integer> getMoves() {
        return moves;
    }
}
