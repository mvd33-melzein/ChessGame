package game;

import java.util.Scanner;
import board.*;
import pieces.*;

public class Game{
    private Board board;
    private boolean isRunning;

    public Game(){
        this.board = new Board();
        isRunning = true;
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);
        board.initialize();
        while(isRunning){
            board.display();
            System.out.print("Enter a move(e2 e4) or 'quit': ");
            String input = scanner.nextLine().trim();

            handleInput(input);

        }

        System.out.println("Game has ended");
    }

    public void handleInput(String input){
        if(input.equals("quit")){
            isRunning = false;
            return;
        }

        if(isValidInput(input)){
            System.out.println("Input is valid\n");
            // Input is now in correct format
            // check whether piece can actually move there
            // check whether the piece is their color
        } else{
            System.out.println("Invalid input, try again\n");
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