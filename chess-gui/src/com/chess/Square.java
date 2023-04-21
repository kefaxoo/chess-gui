package com.chess;

import com.chess.figure.Figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Square extends JButton {
    private boolean isBlack = false;
    private boolean isBlackFigure = false;
    private Figure figure;

    private int x;
    private int y;

    public Square(boolean isBlack, boolean isBlackFigure, Figure figure, int x, int y) {
        var listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game.setSquare(new Square(isBlack, isBlackFigure, figure, x, y));
            }
        };

        this.isBlack = isBlack;
        this.isBlackFigure = isBlackFigure;
        this.figure = figure;
        this.x = x;
        this.y = y;
        setText(figure.getName());
        setFont(new Font("Arial", Font.PLAIN, 40));
        addActionListener(listener);
        if (isBlack) {
            setOpaque(true);
            setBorderPainted(false);
            setBackground(Color.BLACK); // for the background
            setForeground(Color.WHITE); // for the text
        }
    }

    @Override
    public String toString() {
        return "Is black: " + isBlack + "\nFigure: " + figure.getName() + "\nCoordinates: (" + x + "; " + y + ")";
    }

    @Override
    public String getName() {
        return figure.getName();
    }

    public int getCoordinateX() {
        return x;
    }

    public int getCoordinateY() {
        return y;
    }

    public Figure getFigure() {
        return figure;
    }

    public boolean isBlackFigure() {
        return isBlackFigure;
    }
}
