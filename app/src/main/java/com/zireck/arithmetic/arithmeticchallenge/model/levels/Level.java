package com.zireck.arithmetic.arithmeticchallenge.model.levels;

import com.zireck.arithmetic.arithmeticchallenge.model.enums.Difficulty;
import com.zireck.arithmetic.arithmeticchallenge.model.enums.Operations;

import java.util.LinkedHashMap;
import java.util.Map;

public class Level {

    private Difficulty difficulty;
    private int steps = 9;
    private int startMin;
    private int startMax;
    private final LinkedHashMap<Operations, Integer> operations;
    private LinkedHashMap<Operations, Integer> remainingOperations;
    private LinkedHashMap<Operations, Integer> possibleOperations;

    Level(Difficulty difficulty, int steps, int startMin, int startMax, LinkedHashMap<Operations, Integer> operations) {
        this.difficulty = difficulty;
        this.steps = steps;
        this.startMin = startMin;
        this.startMax = startMax;
        this.operations = operations;
        this.remainingOperations = operations;
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

    public Map<Operations, Integer> getOperations() {
        return operations;
    }

    public void calculatePossibleOperations(int result, int steps, Operations lastMove) {
        initializePossibleMoves();

        for (Map.Entry<Operations, Integer> entry : remainingOperations.entrySet()) {
            if (entry.getKey() != lastMove && entry.getKey().isPossible(result, steps, lastMove)) {
                possibleOperations.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public boolean noMorePossibleOperations() {
        return possibleOperations == null || possibleOperations.size() <= 0;
    }

    public Operations weightedChoice() {
        int t = 0;
        Operations keep = null;

        for (Map.Entry<Operations, Integer> entry : possibleOperations.entrySet()) {
            int w = entry.getValue();
            int p = w / (t+w);

            if (keep == null || Math.random() < p) {
                keep = entry.getKey();
            }

            t += w;
        }

        discardOperationFromRemaining(keep);

        return keep;
    }

    private void initializePossibleMoves() {
        if (possibleOperations == null) {
            possibleOperations = new LinkedHashMap<>();
        }

        possibleOperations.clear();
    }

    private void discardOperationFromRemaining(Operations operation) {
        if (remainingOperations == null || remainingOperations.size() <= 0) {
            return;
        }

        if (remainingOperations.containsKey(operation)) {
            int amountLeft = remainingOperations.get(operation);
            amountLeft -= 1;
            if (amountLeft <= 0) {
                remainingOperations.remove(operation);
            } else {
                remainingOperations.put(operation, amountLeft);
            }
        }
    }

}
