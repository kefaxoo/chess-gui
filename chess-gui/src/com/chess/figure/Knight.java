package com.chess.figure;

public class Knight extends Figure {
    private boolean isBlack;

    public Knight(boolean isBlack) {
        super(isBlack ? "♞" : "♘");
        this.isBlack = isBlack;
    }
}
