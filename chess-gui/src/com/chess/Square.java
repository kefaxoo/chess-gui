package com.chess;

import com.chess.figure.Figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Square extends JButton {
    private final Boolean isBlackSquare;
    private final Figure figure;

    private final int coordX;
    private final int coordY;

    public Square(Boolean isBlackSquare, Figure figure, int coordX, int coordY) {
        var listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game.setSquare(new Square(isBlackSquare, figure, coordX, coordY));
            }
        };

        this.isBlackSquare = isBlackSquare;
        this.figure = figure;
        this.coordX = coordX;
        this.coordY = coordY;
        setText(figure.getName());
        setFont(new Font("Arial", Font.PLAIN, 40));
        addActionListener(listener);
        if (isBlackSquare != null && isBlackSquare) {
            setOpaque(true);
            setBorderPainted(false);
            setBackground(Color.BLACK); // for the background
            setForeground(Color.WHITE); // for the text
        }
    }

    @Override
    public String toString() {
        return "Is black square: " + isBlackSquare + "\nFigure: " + figure.getName() + "\nCoordinates: (" + coordX + "; " + coordY + ")";
    }

    @Override
    public String getName() {
        return figure.getName();
    }

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public Figure getFigure() {
        return figure;
    }

    public Boolean isBlackFigure() {
        return figure.isBlack();
    }
}
