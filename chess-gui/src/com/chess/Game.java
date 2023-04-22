package com.chess;

import com.chess.figure.*;
import java.util.Objects;

public class Game {

    private Game() { throw new IllegalStateException("Only static class"); }

    private static Square[][] squares = null;
    private static Square selectedSquare = null;
    private static Board board = null;
    private static boolean isBlackMakeMove = false;
    private static int blackScore = 0;
    private static int whiteScore = 0;
    private static InfoWindow infoWindow = null;
    private static boolean isModifiedSquare = false;

    public static void init() {
        squares = new Square[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Figure figure = null;
                if (i < 2 || i > 5) {
                    if (i == 0 || i == 7) {
                        figure = switch (j) {
                            case 0, 7 -> new Rook(i < 2);
                            case 1, 6 -> new Knight(i < 2);
                            case 2, 5 -> new Bishop(i < 2);
                            case 3 -> new Queen(i < 2);
                            default -> new King(i < 2);
                        };
                    } else {
                        figure = new Pawn(i < 2);
                    }
                }

                var square = new Square(((i + j) % 2) == 1, figure, i, j);
                squares[i][j] = square;
            }
        }

        board = new Board();
        infoWindow = new InfoWindow();
    }

    public static Square[][] getSquares() {
        return squares;
    }

    public static void makeMove(Square square) {
        var selectedX = selectedSquare.getCoordX();
        var selectedY = selectedSquare.getCoordY();
        if (square.getFigure() != null) {
            if (isBlackMakeMove) {
                blackScore++;
            } else {
                whiteScore++;
            }

            infoWindow.updateWindow();
        }

        squares[selectedX][selectedY] = new Square((selectedX + selectedY) % 2 == 1, null, selectedX,
                selectedY);
        var newX = square.getCoordX();
        var newY = square.getCoordY();
        squares[newX][newY] = new Square((newX + newY) % 2 == 1, selectedSquare.getFigure(), newX, newY);
        board.updateGrid();
        isBlackMakeMove = !selectedSquare.isBlackFigure();
        selectedSquare = null;
        isModifiedSquare = true;
    }

    public static void setSquare(Square square) {
        if (selectedSquare == null && square.getFigure() != null && Boolean.TRUE.equals(square.isBlackFigure() ==
                isBlackMakeMove)) {
            selectedSquare = square;
        } else if (selectedSquare != null) {
            var deltaX = Math.abs(square.getCoordX() - selectedSquare.getCoordX());
            var deltaY = Math.abs(square.getCoordY() - selectedSquare.getCoordY());
            if (selectedSquare.getFigure() instanceof Pawn pawn) {
                var isBackMove = false;
                if (Boolean.TRUE.equals(selectedSquare.isBlackFigure())) {
                    isBackMove = square.getCoordX() - selectedSquare.getCoordX() < 0;
                } else {
                    isBackMove = selectedSquare.getCoordX() - square.getCoordX() < 0;
                }

                if (!isBackMove) {
                    if (pawn.isFirstMove() && deltaX <= 2 && deltaY == 0) {
                        makeMove(square);
                        pawn.makeFirstMove();
                    } else if (deltaX == 1 && deltaY == 0 || (deltaX == deltaY && deltaX == 1 &&
                            square.getFigure() != null &&
                            !Objects.equals(selectedSquare.isBlackFigure(), square.isBlackFigure()))) {
                        makeMove(square);
                    }
                }
            } else if (selectedSquare.getFigure() instanceof Bishop && deltaX == deltaY && !Objects.equals(
                    selectedSquare.isBlackFigure(), square.isBlackFigure())) {
                makeMove(square);
            } else if (selectedSquare.getFigure() instanceof Rook && ((deltaX != 0 && deltaY == 0) || (deltaX == 0 &&
                    deltaY != 0)) && !Objects.equals(selectedSquare.isBlackFigure(), square.isBlackFigure())) {
                makeMove(square);
            } else if (selectedSquare.getFigure() instanceof Knight && ((deltaX == 2 && deltaY == 1) || (deltaX == 1 &&
                    deltaY == 2)) && !Objects.equals(selectedSquare.isBlackFigure(), square.isBlackFigure())) {
                makeMove(square);
            } else if (selectedSquare.getFigure() instanceof Queen && ((deltaX != 0 && deltaY == 0) || (deltaX == 0 &&
                    deltaY != 0) || (deltaX == deltaY) && !Objects.equals(selectedSquare.isBlackFigure(),
                    square.isBlackFigure()))) {
                makeMove(square);
            } else if (selectedSquare.getFigure() instanceof King && deltaX <= 1 && deltaY <= 1 &&
                    !Objects.equals(selectedSquare.isBlackFigure(), square.isBlackFigure())) {
                makeMove(square);
            }
        }
    }

    public static int getBlackScore() {
        return blackScore;
    }

    public static int getWhiteScore() {
        return whiteScore;
    }

    public static boolean isModifiedSquare() {
        return isModifiedSquare;
    }
}
