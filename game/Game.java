package game;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import board.*;
import pieces.*;

public class Game{
    private Board board;
    private boolean isRunning;
    private Player whitePlayer;
    private Player blackPlayer;
    private Player currentPlayer;

    public Game(){
        this.board = new Board();
        whitePlayer = new Player(Color.WHITE);
        blackPlayer = new Player(Color.BLACK);
        currentPlayer = whitePlayer;
        isRunning = true;
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);
        board.initialize();
        while(isRunning){
            board.display();
            System.out.println(currentPlayer.getColor() + "'s turn.");
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
    }

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
            if (piece.getColor() != currentPlayer.getColor()) {
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

    public static void main(String[] args){
        Game game = new Game();
        game.start();

    }
}