package com.chess.figure;

public class Pawn extends Figure {
    private boolean firstMove = true;

    public Pawn(boolean isBlack) {
        super(isBlack ? "♟" : "♙", isBlack);
    }

    public void makeFirstMove() {
        firstMove = false;
    }

    public boolean isFirstMove() {
        return firstMove;
    }
}
