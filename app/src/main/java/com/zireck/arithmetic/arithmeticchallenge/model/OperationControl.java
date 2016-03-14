package com.zireck.arithmetic.arithmeticchallenge.model;

import com.zireck.arithmetic.arithmeticchallenge.model.enums.Operations;

import java.util.List;

public interface OperationControl {
    boolean isPossible(int result, int steps, Operations lastMove);
    List<Move> getMoves(int result);
}
