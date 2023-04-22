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
}