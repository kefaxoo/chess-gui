package com.chess.figure;

public class King extends Figure {
    private boolean isBlack;

    public King(boolean isBlack) {
        super(isBlack ? "♚" : "♔");
        this.isBlack = isBlack;
    }
}
