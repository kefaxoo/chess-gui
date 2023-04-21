package com.chess.figure;

public class Rook extends Figure {
    private boolean isBlack;

    public Rook(boolean isBlack) {
        super(isBlack ? "♜" : "♖");
        this.isBlack = isBlack;
    }
}