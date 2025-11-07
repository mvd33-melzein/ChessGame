package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import board.*;
import game.*;
import pieces.*;

public class ChessGUI extends JFrame{
    private Game game;
    private JButton[][] buttons;

    public ChessGUI(Game game){
        this.game = game;
        this.buttons = new JButton[8][8];

        setTitle("ChessGame");
        setSize(640, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 8));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton btn = new JButton();
                buttons[i][j] = btn;

                panel.add(btn);
            }
        }

        add(panel);
        setVisible(true);

        refreshBoard();
    }

    public void refreshBoard(){
        Board board = game.getBoard();

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Position pos = new Position(i, j);
                Piece piece = board.getPiece(pos);
                if (piece != null) {
                    buttons[i][j].setText(piece.toString());
                } else {
                    buttons[i][j].setText("");
                }
            }
        }
    }
}