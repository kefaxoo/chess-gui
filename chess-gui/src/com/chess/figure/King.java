package com.chess.figure;

public class King extends Figure {
    public King(boolean isBlack) {
        super(isBlack ? "♚" : "♔", isBlack);
    }
}
