package com.chess.figure;

public class Queen extends Figure {
    public Queen(boolean isBlack) {
        super(isBlack ? "♛" : "♕", isBlack);
    }
}
