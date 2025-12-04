package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import board.*;
import game.*;
import pieces.*;

// Main GUI window for the chess game
public class ChessGUI extends JFrame{
    // Chess board color scheme
    private static final Color LIGHT_SQUARE = new Color(0xEE, 0xEE, 0xD2);
    private static final Color DARK_SQUARE = new Color(0x76, 0x96, 0x56);
    private static final Color HIGHLIGHT = new Color(0xBA, 0xCA, 0x2B);

    private Game game;
    private JButton[][] buttons;
    private Position selectedSquare = null;

    // Sets up the chess board GUI with 8x8 grid of buttons
    public ChessGUI(Game game){
        this.game = game;
        this.buttons = new JButton[8][8];

        setTitle("ChessGame");
        setSize(640, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new java.awt.GridLayout(8, 8));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton btn = new JButton();
                buttons[i][j] = btn;

                btn.setOpaque(true);
                btn.setBorderPainted(false);
                btn.setContentAreaFilled(true);

                // Alternate square colors to create checkerboard pattern
                if ((i + j) % 2 == 0) {
                    btn.setBackground(LIGHT_SQUARE);
                } else {
                    btn.setBackground(DARK_SQUARE);
                }

                final int row = i;
                final int col = j;

                btn.addActionListener(e -> handleClick(row, col));

                panel.add(btn);
            }
        }

        add(panel);
        setVisible(true);

        refreshBoard();
    }

    // Handles square clicks: first click selects piece, second click moves it
    private void handleClick(int row, int col){
        if(!game.isGameRunning()){
            return;
        }
        Board board = game.getBoard();
        Position clicked = new Position(row, col);

        // First click: select a piece
        if(selectedSquare == null){
            Piece piece = board.getPiece(clicked);
            if(piece == null){
                return;
            }
            if(piece.getPlayerColor() != game.getCurrentPlayerColor()){
                return;
            }
            selectedSquare = clicked;
            refreshBoard();
            highlightSquare(row, col);
            return;
        }

        // second click
        boolean moved = game.handleMove(selectedSquare, clicked);
        if (moved) {
            game.switchPlayer();
        }

        selectedSquare = null;
        refreshBoard();
    }

    // Highlights a square to show the selected piece
    private void highlightSquare(int row, int col) {
        buttons[row][col].setBackground(HIGHLIGHT);
    }

    public void showGameOverWindow(String winner) {
        JFrame endFrame = new JFrame("Game Over");

        endFrame.setSize(300, 150);
        endFrame.setLocationRelativeTo(null);
        endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel(winner + " wins by checkmate!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));

        endFrame.add(label);

        endFrame.setVisible(true);
    }

    // Updates the visual representation of the board with current piece positions
    public void refreshBoard(){
        Board board = game.getBoard();
        
        int imageSize = 60;

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if ((i + j) % 2 == 0) {
                    buttons[i][j].setBackground(LIGHT_SQUARE);
                } else {
                    buttons[i][j].setBackground(DARK_SQUARE);
                }

                Position pos = new Position(i, j);
                Piece piece = board.getPiece(pos);
                if (piece != null) {
                    String imagePath = piece.getImagePath();
                    ImageIcon icon = new ImageIcon(imagePath);
                    // Scale the image to fit the button nicely
                    Image img = icon.getImage();
                    Image scaledImg = img.getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH);
                    buttons[i][j].setIcon(new ImageIcon(scaledImg));
                    buttons[i][j].setText("");
                } else {
                    buttons[i][j].setIcon(null);
                    buttons[i][j].setText("");
                }
            }
        }
    }
}