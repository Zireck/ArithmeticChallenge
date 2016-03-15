package com.zireck.arithmetic.arithmeticchallenge.model;

import com.zireck.arithmetic.arithmeticchallenge.model.enums.Difficulty;
import com.zireck.arithmetic.arithmeticchallenge.model.enums.Operations;
import com.zireck.arithmetic.arithmeticchallenge.model.levels.Level;
import com.zireck.arithmetic.arithmeticchallenge.model.levels.LevelFactory;
import com.zireck.arithmetic.arithmeticchallenge.util.MathUtils;

import java.util.ArrayList;
import java.util.List;

public class Challenge {

    private Difficulty difficulty;
    private Level level;
    private List<Move> moves;
    private int initialValue;
    private int result;

    public Challenge(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.level = LevelFactory.getLevel(difficulty);

        while (!makeMoves() || moves.size() < level.getSteps());
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public int getInitialValue() {
        return initialValue;
    }

    public int getResult() {
        return result;
    }

    private boolean makeMoves() {
        initializeMoves();

        initialValue = MathUtils.generateRandomNumber(level.getStartMin(), level.getStartMax());
        result = initialValue;

        int steps = level.getSteps();
        Operations lastMove = null;

        level = LevelFactory.getLevel(difficulty);

        while (steps > 0) {
            level.calculatePossibleOperations(result, steps, lastMove);

            if (level.noMorePossibleOperations()) {
                //throw new IllegalStateException("No more possible operations.");
                return false;
            }

            Operations operation = level.weightedChoice();
            List<Move> newMoves = operation.getMoves(result);
            moves.addAll(newMoves);

            steps -= newMoves.size();
            result = moves.get(moves.size() - 1).getResult();
            lastMove = moves.get(moves.size() - 1).getOperation();
        }

        return steps <= 0;
    }

    private void initializeMoves() {
        if (moves == null) {
            moves = new ArrayList<>();
        }

        moves.clear();
    }

}
