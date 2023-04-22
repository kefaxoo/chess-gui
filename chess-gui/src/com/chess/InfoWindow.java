package com.chess;

import javax.swing.*;
import java.awt.*;

public class InfoWindow extends JFrame {
    public InfoWindow() {
        setSize(300, 300);
        setLocation(600, 0);
        setVisible(true);
        setBackground(null);
        setTitle("Info");
        createWindow();
    }

    private void createWindow() {
        var blackScoreLabel = new JLabel("Black Score: " + Game.getBlackScore());
        var whiteScoreLabel = new JLabel("White Score: " + Game.getWhiteScore());
        var whoWillMakeMove = new JLabel("Move color: " + (Game.isBlackMakeMove() ? "Black" : "White"));
        var panel = new JPanel(new GridLayout(3, 1));
        panel.add(whoWillMakeMove);
        panel.add(blackScoreLabel);
        panel.add(whiteScoreLabel);
        this.getContentPane().add(panel);
    }

    public void updateWindow() {
        this.getContentPane().removeAll();
        createWindow();
        this.revalidate();
        this.repaint();
    }
}
