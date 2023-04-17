import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Figure {
    private String name;

    public Figure(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class King extends Figure {
    private boolean isBlack;

    public King(boolean isBlack) {
        super(isBlack ? "K" : "k");
        this.isBlack = isBlack;
    }
}

class Queen extends Figure {
    private boolean isBlack;

    public Queen(boolean isBlack) {
        super(isBlack ? "Q" : "q");
        this.isBlack = isBlack;
    }
}

class Rook extends Figure {
    private boolean isBlack;

    public Rook(boolean isBlack) {
        super(isBlack ? "R" : "r");
        this.isBlack = isBlack;
    }
}

class Bishop extends Figure {
    private boolean isBlack;

    public Bishop(boolean isBlack) {
        super(isBlack ? "B" : "b");
        this.isBlack = isBlack;
    }
}

class Knight extends Figure {
    private boolean isBlack;

    public Knight(boolean isBlack) {
        super(isBlack ? "KN" : "kn");
        this.isBlack = isBlack;
    }
}

class Pawn extends Figure {
    private boolean isBlack;

    public Pawn(boolean isBlack) {
        super(isBlack ? "P" : "p");
        this.isBlack = isBlack;
    }
}

class Square extends JButton {
    private boolean isBlack = false;
    private Figure figure;

    private int x;
    private int y;

    public Square(boolean isBlack, Figure figure, int x, int y) {
        var listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button selected: " + e.getActionCommand() + "\nCoordinates: " + x + " " + y);
            }
        };

        this.isBlack = isBlack;
        this.figure = figure;
        this.x = x;
        this.y = y;
        setText(figure.getName());
        addActionListener(listener);
        if (isBlack) {
            setOpaque(true);
            setBorderPainted(false);
            setBackground(Color.BLACK); // for the background
            setForeground(Color.WHITE); // for the text
        }
    }
}

class Board extends JFrame {
    private Square[][] squares = new Square[8][8];

    public Board() {
        setSize(600, 600);
        createGrid();
        setBackground(null);
        setVisible(true);
        setTitle("Chess");
    }

    private void createGrid() {
        var panel = new JPanel(new GridLayout(8, 8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Figure figure = new Figure(null);
                if (i < 2 || i > 5) {
                    if (i == 0 || i == 7) {
                        figure = switch(j) {
                            case 0, 7 -> new Rook(i % 2 == 0);
                            case 1, 6 -> new Knight(i % 2 == 0);
                            case 2, 5 -> new Bishop(i % 2 == 0);
                            case 3 -> new Queen(i % 2 == 0);
                            default -> new King(i % 2 == 0);
                        };
                    } else {
                        figure = new Pawn(i % 2 == 1);
                    }
                }

                var square = new Square((i + j) % 2 == 1, figure, i, j);
                squares[i][j] = square;

                panel.add(square);
            }
        }

        this.getContentPane().add(panel);
    }
}

public class Main {
    public static void main(String[] args) {
        new Board();
    }
}