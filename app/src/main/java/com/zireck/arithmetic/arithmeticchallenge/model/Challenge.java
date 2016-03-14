package com.zireck.arithmetic.arithmeticchallenge.model;

import com.zireck.arithmetic.arithmeticchallenge.model.enums.Difficulty;
import com.zireck.arithmetic.arithmeticchallenge.model.enums.Operations;
import com.zireck.arithmetic.arithmeticchallenge.model.levels.Level;
import com.zireck.arithmetic.arithmeticchallenge.model.levels.LevelFactory;
import com.zireck.arithmetic.arithmeticchallenge.util.MathUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Challenge {

    private Difficulty difficulty;
    private Level level;
    private List<Move> moves;
    private int result;

    public Challenge(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.level = LevelFactory.getLevel(difficulty);

        makeMoves();
    }

    public void generateChallenge() {
        System.out.println("hello world");
        Operations suma = Operations.ADD;
        System.out.println(suma.toString());
    }

    private void makeMoves() {
        moves = new ArrayList<>();

        result = MathUtils.generateRandomNumber(level.getStartMin(), level.getStartMax());
        int steps = level.getSteps();

        while (steps > 0) {

            Operations operation = weighted_choice(level.getMoves());
            List<Move> newMoves = operation.getMoves(result);
            moves.addAll(newMoves);

            steps -= newMoves.size();
            result = moves.get(moves.size() - 1).getResult();

        }

    }

    // TODO: these two methods go in Level.class
    private List<Operations> getPossibleOperations(int result, int steps, Operations lastMove) {

        return new ArrayList<>();
    }

    private Operations weighted_choice(Map<Operations, Integer> possibles) {
        int t = 0;
        Operations keep = null;

        Iterator iterator = possibles.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Operations, Integer> pair = (Map.Entry<Operations, Integer>) iterator.next();
            Operations clave = pair.getKey();
            int w = pair.getValue();
            int p = w / (t+w);

            if (keep == null || Math.random() < p) {
                keep = clave;
            }

            t += w;
        }

        return keep;
    }

}
