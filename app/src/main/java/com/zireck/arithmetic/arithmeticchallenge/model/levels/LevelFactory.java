package com.zireck.arithmetic.arithmeticchallenge.model.levels;

import com.zireck.arithmetic.arithmeticchallenge.model.enums.Difficulty;
import com.zireck.arithmetic.arithmeticchallenge.model.enums.Operations;

import java.util.HashMap;
import java.util.Map;

public class LevelFactory {

    public static Level getLevel(Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                return getLevelEasy();
            case MEDIUM:
                return getLevelMedium();
            case HARD:
                return getLevelHard();
            default:
                return getLevelEasy();
        }
    }

    private static Level getLevelEasy() {
        Difficulty difficulty = Difficulty.EASY;
        int steps = 9;
        int startMin = 1;
        int startMax = 20;
        Map<Operations, Integer> moves = new HashMap<>();

        moves.put(Operations.MULTIPLY, 1);
        moves.put(Operations.HALVE_IT, 2);
        moves.put(Operations.ADD, 3);
        moves.put(Operations.SUBTRACT, 3);
        moves.put(Operations.SQUARE_IT, 1);
        moves.put(Operations.DIVIDE, 1);

        return new Level(difficulty, steps, startMin, startMax, moves);
    }

    private static Level getLevelMedium() {
        Difficulty difficulty = Difficulty.MEDIUM;
        int steps = 9;
        int startMin = 10;
        int startMax = 100;
        Map<Operations, Integer> moves = new HashMap<>();

        moves.put(Operations.MULTIPLY, 2);
        moves.put(Operations.HALVE_IT, 2);
        moves.put(Operations.DOUBLE_IT, 2);
        moves.put(Operations.SQUARE_IT, 1);
        moves.put(Operations.DIVIDE, 1);
        moves.put(Operations.FRACTION, 1);
        moves.put(Operations.SUBTRACT, 1);

        return new Level(difficulty, steps, startMin, startMax, moves);
    }

    private static Level getLevelHard() {
        Difficulty difficulty = Difficulty.HARD;
        int steps = 9;
        int startMin = 1;
        int startMax = 100;
        Map<Operations, Integer> moves = new HashMap<>();

        moves.put(Operations.MULTIPLY, 3);
        moves.put(Operations.HALVE_IT, 2);
        moves.put(Operations.SQUARE_IT, 2);
        moves.put(Operations.CUBE_IT, 1);
        moves.put(Operations.DIVIDE, 2);
        moves.put(Operations.FRACTION, 1);
        moves.put(Operations.TEN_PERCENT, 3);
        moves.put(Operations.ADD, 1);
        moves.put(Operations.SUBTRACT, 1);

        return new Level(difficulty, steps, startMin, startMax, moves);
    }

}
