package com.chess.figure;

public abstract class Figure {
    private final String name;
    private final Boolean isBlack;

    protected Figure(String name, Boolean isBlack) {
        this.name = name;
        this.isBlack = isBlack;
    }

    public String getName() {
        return name;
    }

    public Boolean isBlack() {
        return isBlack;
    }

    @Override
    public String toString() {
        return "IsBlack: " + isBlack + " " + "\nName: " + name;
    }

    public static int getType(Figure figure) {
        if (figure instanceof King) {
            return 1;
        } else if (figure instanceof Queen) {
            return 2;
        } else if (figure instanceof Rook) {
            return 3;
        } else if (figure instanceof Bishop) {
            return 4;
        } else if (figure instanceof Knight) {
            return 5;
        }

        return 0;
    }
}