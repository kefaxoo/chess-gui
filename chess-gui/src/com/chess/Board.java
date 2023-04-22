package com.chess;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JFrame implements KeyListener {
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
                square.addKeyListener(this);
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

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getModifiersEx() == InputEvent.CTRL_DOWN_MASK) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_O -> System.out.println("Ctrl+o");
                case KeyEvent.VK_S -> System.out.println("Ctrl+s");
                case KeyEvent.VK_W -> {
                    System.out.println("Ctrl+w");
                    if (Game.isModifiedSquare()) {
                        var dialogueResult = JOptionPane.showConfirmDialog(null,
                                "Do you want to save state of the game?", "", JOptionPane.YES_NO_OPTION);
                        if (dialogueResult == JOptionPane.YES_OPTION) {
                            JFileChooser fileChooser = new JFileChooser();
                            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                                    "Binary files (*.bin)", "bin");
                            fileChooser.setFileFilter(filter);
                            int dialogResult = fileChooser.showSaveDialog(null);
                            if (dialogResult == JFileChooser.APPROVE_OPTION) {
                                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                                System.out.println("Selected file path: " + filePath);
                            } else {
                                System.exit(0);
                            }
                        } else {
                            System.exit(0);
                        }
                    } else {
                        System.exit(0);
                    }
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
