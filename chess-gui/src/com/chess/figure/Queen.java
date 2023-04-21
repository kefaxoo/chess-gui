package com.chess.figure;

public class Queen extends Figure {
    private boolean isBlack;

    public Queen(boolean isBlack) {
        super(isBlack ? "♛" : "♕");
        this.isBlack = isBlack;
    }
}
