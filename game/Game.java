package game;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import javax.swing.*;
import board.*;
import pieces.*;
import gui.*;

// Manages game state and move validation
public class Game{
    private Board board;
    private boolean isRunning;
    private Player whitePlayer;
    private Player blackPlayer;
    private Player currentPlayer;

    // Initializes a new game with players and board
    public Game(){
        this.board = new Board();
        whitePlayer = new Player(PlayerColor.WHITE);
        blackPlayer = new Player(PlayerColor.BLACK);
        currentPlayer = whitePlayer;
        isRunning = true;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isGameRunning(){
        return isRunning;
    }

    // Alternates between white and black players
    public void switchPlayer() {
        if (currentPlayer == whitePlayer) {
            currentPlayer = blackPlayer;
        } else {
            currentPlayer = whitePlayer;
        }
    }

    public PlayerColor getCurrentPlayerColor() {
        return currentPlayer.getPlayerColor();
    }

    public void start(){
        //Scanner scanner = new Scanner(System.in);
        board.initialize();
        /*
        while(isRunning){
            board.display();
            System.out.println(currentPlayer.getPlayerColor() + "'s turn.");
            System.out.print("Enter a move(e2 e4) or 'quit': ");
            String input = scanner.nextLine().trim();

            boolean moveWasSuccessful = handleInput(input);
            if(moveWasSuccessful){
                if(currentPlayer == whitePlayer){
                    currentPlayer = blackPlayer;
                }
                else{
                    currentPlayer = whitePlayer;
                }
            }

        }
        

        System.out.println("Game has ended");
        */
    }

    // parses string into input
    public boolean handleInput(String input){
        if(input.equals("quit")){
            isRunning = false;
            return false;
        }

        if(isValidInput(input)){
            String[] parts = input.toUpperCase().split(" ");

            Position from = Position.fromNotation(parts[0]);
            Position to = Position.fromNotation(parts[1]);

            Piece piece = board.getPiece(from);

            if (piece == null) {
                System.out.println("No piece at " + parts[0] + "\n");
                return false;
            }
            if (piece.getPlayerColor() != currentPlayer.getPlayerColor()) {
                System.out.println("Wrong color piece");
                return false;
            }

            List<Position> validMoves = piece.possibleMoves(board);
            boolean isLegalMove = false;
            for (Position move : validMoves) {
                if (Position.equals(move, to)) {
                    isLegalMove = true;
                    break;
                }
            }

            if (isLegalMove) {
                board.movePiece(from, to);
                System.out.println("Move executed");
                return true;
            } else {
                System.out.println("Illegal move for that piece\n");
                return false;
            }
            
        } else{
            System.out.println("Invalid input, try again\n");
            return false;
        }
    }

    // Validates and executes a move from the GUI
    public boolean handleMove(Position from, Position to){
        Piece piece = board.getPiece(from);

        if (piece == null) {
            System.out.println("No piece at selected square");
            return false;
        }
        if (piece.getPlayerColor() != currentPlayer.getPlayerColor()) {
            System.out.println("Wrong color piece");
            return false;
        }
        
        List<Position> validMoves = piece.possibleMoves(board);
        boolean isLegalMove = false;
        for (Position move : validMoves) {
            if (Position.equals(move, to)) {
                isLegalMove = true;
                break;
            }
        }

        if (!isLegalMove) {
            System.out.println("Illegal move for that piece");
            return false;
        }

        // Verify the move doesn't leave the king in check
        if (!board.simulateMove(from, to, currentPlayer.getPlayerColor())) {
            System.out.println("Illegal: King would be in check");
            return false;
        }

        board.movePiece(from, to);
        System.out.println("Move executed");

        // Check if the move results in checkmate
        
        PlayerColor opponentColor = (currentPlayer == whitePlayer) ? PlayerColor.BLACK : PlayerColor.WHITE;

        if (board.isKingInCheck(opponentColor)) {

            if (isCheckmate(opponentColor)) {
                System.out.println(opponentColor + " is in checkmate! Game over.");
                isRunning = false;
                JFrame endFrame = new JFrame("Game Over");
                endFrame.setSize(300, 150);
                endFrame.setLocationRelativeTo(null);
                endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JLabel label = new JLabel(opponentColor + " is in checkmate! " + currentPlayer.getPlayerColor() + " wins!");
                label.setFont(new Font("Arial", Font.BOLD, 16));
                endFrame.add(label);

                endFrame.setVisible(true);
            } else {
                System.out.println(opponentColor + " is in check.");
            }
        }
        
        return true;
    }

    // Validates that input matches chess notation format (e.g., "e2 e4")
    public boolean isValidInput(String input){
        if(input.length() != 5){
            return false;
        }
        char fromCol = Character.toUpperCase(input.charAt(0));
        char fromRow = input.charAt(1);
        char space   = input.charAt(2);
        char toCol   = Character.toUpperCase(input.charAt(3));
        char toRow   = input.charAt(4);

        if(fromCol < 'A' || fromCol > 'H'){
            return false;
        }
        if(toCol < 'A' || toCol > 'H'){
            return false;
        }
        if(space != ' '){
            return false;
        }
        if(fromRow < '1' || fromRow > '8'){
            return false;
        }
        if(toRow < '1' || toRow > '8'){
            return false;
        }

        return true;
    }
    
    // Determines if the given color is in checkmate (in check with no legal moves)
    public boolean isCheckmate(PlayerColor color) {
    if (!board.isKingInCheck(color)) {
        return false;
    }

    List<Piece> pieces = board.getPiecesForColor(color);

    // Try all possible moves to see if any can escape check
    for (Piece piece : pieces) {
        List<Position> moves = piece.possibleMoves(board);

        for (Position to : moves) {
            Position from = piece.getPosition();

            Piece captured = board.getPiece(to);
            board.forceMove(from, to);

            boolean stillInCheck = board.isKingInCheck(color);

            board.forceMove(to, from);
            if (captured != null) board.setPiece(to, captured);

            if (!stillInCheck) {
                return false;
            }
        }
    }

        return true;
    }
    
    // Entry point: creates game and GUI
    public static void main(String[] args){
        Game game = new Game();
        game.start();

        ChessGUI gui = new ChessGUI(game);

    }
}