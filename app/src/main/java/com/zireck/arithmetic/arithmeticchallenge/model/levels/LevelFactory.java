package com.zireck.arithmetic.arithmeticchallenge.model.levels;

import com.zireck.arithmetic.arithmeticchallenge.model.enums.Difficulty;
import com.zireck.arithmetic.arithmeticchallenge.model.enums.Operations;

import java.util.LinkedHashMap;

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
        LinkedHashMap<Operations, Integer> operations = new LinkedHashMap<>();

        operations.put(Operations.MULTIPLY, 1);
        operations.put(Operations.HALVE_IT, 2);
        operations.put(Operations.ADD, 3);
        operations.put(Operations.SUBTRACT, 3);
        operations.put(Operations.SQUARE_IT, 1);
        operations.put(Operations.DIVIDE, 1);

        return new Level(difficulty, steps, startMin, startMax, operations);
    }

    private static Level getLevelMedium() {
        Difficulty difficulty = Difficulty.MEDIUM;
        int steps = 9;
        int startMin = 10;
        int startMax = 100;
        LinkedHashMap<Operations, Integer> operations = new LinkedHashMap<>();

        operations.put(Operations.MULTIPLY, 2);
        operations.put(Operations.HALVE_IT, 2);
        operations.put(Operations.DOUBLE_IT, 2);
        operations.put(Operations.SQUARE_IT, 1);
        operations.put(Operations.DIVIDE, 1);
        operations.put(Operations.FRACTION, 1);
        operations.put(Operations.SUBTRACT, 1);

        return new Level(difficulty, steps, startMin, startMax, operations);
    }

    private static Level getLevelHard() {
        Difficulty difficulty = Difficulty.HARD;
        int steps = 9;
        int startMin = 1;
        int startMax = 100;
        LinkedHashMap<Operations, Integer> operations = new LinkedHashMap<>();

        operations.put(Operations.MULTIPLY, 3);
        operations.put(Operations.HALVE_IT, 2);
        operations.put(Operations.SQUARE_IT, 2);
        operations.put(Operations.CUBE_IT, 1);
        operations.put(Operations.DIVIDE, 2);
        operations.put(Operations.FRACTION, 1);
        operations.put(Operations.TEN_PERCENT, 3);
        operations.put(Operations.ADD, 1);
        operations.put(Operations.SUBTRACT, 1);

        return new Level(difficulty, steps, startMin, startMax, operations);
    }

}
