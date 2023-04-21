package com.chess.figure;

public class Bishop extends Figure {
    private boolean isBlack;

    public Bishop(boolean isBlack) {
        super(isBlack ? "♝" : "♗");
        this.isBlack = isBlack;
    }
}
