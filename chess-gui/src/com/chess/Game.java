package com.chess;

import com.chess.figure.*;

public class Game {
    private static Square[][] squares = null;
    private static Square selectedSquare = null;
    private static Board board = null;

    public static void init() {
        squares = new Square[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Figure figure = new Figure(null);
                if (i < 2 || i > 5) {
                    if (i == 0 || i == 7) {
                        figure = switch (j) {
                            case 0, 7 -> new Rook((i % 2) == 0);
                            case 1, 6 -> new Knight((i % 2) == 0);
                            case 2, 5 -> new Bishop((i % 2) == 0);
                            case 3 -> new Queen((i % 2) == 0);
                            default -> new King((i % 2) == 0);
                        };
                    } else {
                        figure = new Pawn((i % 2) == 1);
                    }
                }

                var square = new Square(((i + j) % 2) == 1, i > 5, figure, i, j);
                squares[i][j] = square;
            }
        }

        board = new Board();
    }

    public static Square[][] getSquares() {
        return squares;
    }

    public static void makeMove(Square square) {
            var selectedX = selectedSquare.getCoordinateX();
            var selectedY = selectedSquare.getCoordinateY();
            squares[selectedX][selectedY] = new Square((selectedX + selectedY) % 2 == 1,
                    selectedSquare.isBlackFigure(), new Figure(null), selectedX, selectedY);
            var newX = square.getCoordinateX();
            var newY = square.getCoordinateY();
            squares[newX][newY] = new Square((newX + newY) % 2 == 1, square.isBlackFigure(),
                    selectedSquare.getFigure(), newX, newY);
            board.updateGrid();
            selectedSquare = null;
    }

    public static void setSquare(Square square) {
        if (selectedSquare == null && square.getName() != null) {
            selectedSquare = square;
            System.out.println("Was null now figure: " + square.getName());
        } else if (selectedSquare != null && square.getName() == null) {
            var deltaX = Math.abs(square.getCoordinateX() - selectedSquare.getCoordinateX());
            if (selectedSquare.getFigure() instanceof Pawn pawn) {
                var isBackMove = false;
                if (selectedSquare.isBlackFigure()) {
                    isBackMove = selectedSquare.getCoordinateX() - square.getCoordinateX() < 0;
                } else {
                    isBackMove = square.getCoordinateX() - selectedSquare.getCoordinateX() < 0;
                }

                if (!isBackMove) {
                    if (pawn.isFirstMove() && deltaX <= 2) {
                        makeMove(square);
                        pawn.makeFirstMove();
                    } else if (deltaX == 1) {
                        makeMove(square);
                    }
                }
            } else if (selectedSquare.getFigure() instanceof King) {
                if (square.getName() == null && deltaX == 1) {
                    makeMove(square);
                }
            } else if (selectedSquare.getFigure() instanceof Queen queen) {
                var deltaY = Math.abs(square.getCoordinateY() - selectedSquare.getCoordinateY());
                if (deltaX != 0 && deltaY == 0 || deltaX == 0 && deltaY != 0 || deltaX == deltaY) {
                    makeMove(square);
                }
            }
        }
    }
}
