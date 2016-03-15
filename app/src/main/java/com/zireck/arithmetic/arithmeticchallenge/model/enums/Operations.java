package com.zireck.arithmetic.arithmeticchallenge.model.enums;

import com.zireck.arithmetic.arithmeticchallenge.model.Move;
import com.zireck.arithmetic.arithmeticchallenge.model.OperationControl;
import com.zireck.arithmetic.arithmeticchallenge.util.MathUtils;

import java.util.ArrayList;
import java.util.List;

public enum Operations implements OperationControl {

    ADD {
        public boolean isPossible(int result, int steps, Operations lastMove) {
            return (lastMove != Operations.ADD && lastMove != Operations.SUBTRACT);
        }

        @Override
        public List<Move> getMoves(int result) {
            List<Move> moves = new ArrayList<>();

            Operations operation = Operations.ADD;
            int factor = MathUtils.generateRandomNumber(1, 20);
            String text = "+" + String.valueOf(factor);
            int newResult = result + factor;

            Move move = new Move(operation, factor, text, newResult);
            moves.add(move);

            return moves;
        }
    },
    SUBTRACT {
        public boolean isPossible(int result, int steps, Operations lastMove) {
            return result > 5 && lastMove != Operations.ADD && lastMove != Operations.SUBTRACT;
        }

        @Override
        public List<Move> getMoves(int result) {
            List<Move> moves = new ArrayList<>();

            Operations operation = Operations.SUBTRACT;
            int factor = MathUtils.generateRandomNumber(1, Math.min(20, result-5));
            String text = "-" + String.valueOf(factor);
            int newResult = result - factor;

            Move move = new Move(operation, factor, text, newResult);
            moves.add(move);

            return moves;
        }
    },
    MULTIPLY {
        public boolean isPossible(int result, int steps, Operations lastMove) {
            return result < 100 && lastMove != Operations.DIVIDE;
        }

        @Override
        public List<Move> getMoves(int result) {
            List<Move> moves = new ArrayList<>();

            Operations operation = Operations.MULTIPLY;
            int factor = MathUtils.generateRandomNumber(2, (int) Math.min(10, Math.floor(200/result)));
            String text = "×" + String.valueOf(factor);
            int newResult = result * factor;

            Move move = new Move(operation, factor, text, newResult);
            moves.add(move);

            return moves;
        }
    },

    DIVIDE {
        public boolean isPossible(int result, int steps, Operations lastMove) {
            return result >= 10 && steps >= 2 && lastMove != Operations.MULTIPLY;
        }

        @Override
        public List<Move> getMoves(int result) {
            List<Move> moves = new ArrayList<>();

            int factor = MathUtils.generateRandomNumber(2, 10);
            int mod = result % factor;
            if (mod != 0) {
                if (result < factor || MathUtils.coin()) {
                    moves.add(new Move(Operations.ADD, (factor - mod), "+" + String.valueOf(factor - mod), (result + factor - mod)));
                    result += factor - mod;
                } else {
                    moves.add(new Move(Operations.SUBTRACT, mod, "-" + String.valueOf(mod), (result - mod)));
                    result -= mod;
                }
            }

            Operations operation = Operations.DIVIDE;
            String text = "÷" + String.valueOf(factor);
            int newResult = result / factor;

            Move move = new Move(operation, factor, text, newResult);
            moves.add(move);

            return moves;
        }
    },
    FRACTION {
        @Override
        public boolean isPossible(int result, int steps, Operations lastMove) {
            return steps >= 2;
        }

        @Override
        public List<Move> getMoves(int result) {
            List<Move> moves = new ArrayList<>();

            int factor = MathUtils.generateRandomNumber(2, 10);
            int mod = result % factor;

            if (mod != 0) {
                if (result < factor || MathUtils.coin()) {
                    moves.add(new Move(Operations.ADD, (factor - mod), "+" + String.valueOf(factor - mod), (result + factor - mod)));
                    result += factor - mod;
                } else {
                    moves.add(new Move(Operations.SUBTRACT, mod, "-" + String.valueOf(mod), (result - mod)));
                    result -= mod;
                }
            }

            int i = MathUtils.generateRandomNumber(1, factor - 1);
            int g = MathUtils.gcd(i, factor);

            i /= g;
            factor /= g;

            Operations operation = Operations.FRACTION;
            double actualFactor = i / factor;
            String text = String.valueOf(i) + "/" + String.valueOf(factor) + " of this";
            int newResult = result * i / factor;

            Move move = new Move(operation, actualFactor, text, newResult);
            moves.add(move);

            return moves;
        }
    },
    TEN_PERCENT {
        @Override
        public boolean isPossible(int result, int steps, Operations lastMove) {
            return result % 10 == 0;
        }

        @Override
        public List<Move> getMoves(int result) {
            List<Move> moves = new ArrayList<>();

            Operations operation = Operations.TEN_PERCENT;
            int factor = MathUtils.generateRandomNumber(1, 10);
            String text = String.valueOf(factor*10) + "% of this";
            int newResult = result * factor / 10;

            Move move = new Move(operation, factor, text, newResult);
            moves.add(move);

            return moves;
        }
    },
    DOUBLE_IT {
        @Override
        public boolean isPossible(int result, int steps, Operations lastMove) {
            return lastMove != Operations.HALVE_IT;
        }

        @Override
        public List<Move> getMoves(int result) {
            List<Move> moves = new ArrayList<>();

            Operations operation = Operations.DOUBLE_IT;
            int factor = 0;
            String text = "double it";
            int newResult = result * 2;

            Move move = new Move(operation, factor, text, newResult);
            moves.add(move);

            return moves;
        }
    },
    SQUARE_IT {
        @Override
        public boolean isPossible(int result, int steps, Operations lastMove) {
            return result > 1 &&  result < 20;
        }

        @Override
        public List<Move> getMoves(int result) {
            List<Move> moves = new ArrayList<>();

            Operations operation = Operations.SQUARE_IT;
            int factor = 0;
            String text = "multiply by itself";
            int newResult = result * result;

            Move move = new Move(operation, factor, text, newResult);
            moves.add(move);

            return moves;
        }
    },
    CUBE_IT {
        @Override
        public boolean isPossible(int result, int steps, Operations lastMove) {
            return result > 1 && result < 10;
        }

        @Override
        public List<Move> getMoves(int result) {
            List<Move> moves = new ArrayList<>();

            Operations operation = Operations.CUBE_IT;
            int factor = 0;
            String text = "multiply by itself twice";
            int newResult = result * result * result;

            Move move = new Move(operation, factor, text, newResult);
            moves.add(move);

            return moves;
        }
    },
    HALVE_IT {
        @Override
        public boolean isPossible(int result, int steps, Operations lastMove) {
            return result % 2 == 0 && lastMove != Operations.DOUBLE_IT;
        }

        @Override
        public List<Move> getMoves(int result) {
            List<Move> moves = new ArrayList<>();

            Operations operation = Operations.HALVE_IT;
            int factor = 0;
            String text = "halve it";
            int newResult = result / 2;

            Move move = new Move(operation, factor, text, newResult);
            moves.add(move);

            return moves;
        }
    }
}
