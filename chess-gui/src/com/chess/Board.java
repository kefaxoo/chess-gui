package com.chess;

import javax.swing.*;
import java.awt.*;

public class Board extends JFrame {
    public Board() {
        setSize(600, 600);
        createGrid();
        setBackground(null);
        setVisible(true);
        setTitle("Chess");
    }

    private void createGrid() {
        var panel = new JPanel(new GridLayout(8, 8));
        for (var row: Game.getSquares()) {
            for (var square: row) {
                panel.add(square);
            }
        }

        this.getContentPane().add(panel);
    }

    public void updateGrid() {
        this.getContentPane().removeAll();
        createGrid();
        this.revalidate();
        this.repaint();
    }
}
