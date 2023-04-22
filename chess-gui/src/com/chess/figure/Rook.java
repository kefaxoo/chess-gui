package com.chess.figure;

public class Rook extends Figure {
    public Rook(boolean isBlack) {
        super(isBlack ? "♜" : "♖", isBlack);
    }
}