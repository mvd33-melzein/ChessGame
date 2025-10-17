package board;

import pieces.*;

public class Board{
    private Piece[][] board;

    public Board(){
        board = new Piece[8][8];
    }
    
    
    private void initialize(){
        // Used to set up board, call after creating board
    }

    public Piece getPiece(Position pos){
        return board[pos.getRow()][pos.getColumn()];
    }

    public void movePiece(Position from, Position to){

    }

    public void display(){
        System.out.println("  A  B  C  D  E  F  G  H");
        for(int i = 7; i >= 0; i--){
            System.out.print((i + 1) + " ");

            for(int j = 0; j < 8; j++){
                Piece piece = board[i][j];

                if(piece == null){
                    if((i + j) % 2 == 0){
                        System.out.print("## ");
                    }
                    else{
                        System.out.print("   ");
                    }
                }
                else{
                    System.out.print(piece.toString() + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Board board = new Board();
        board.display();
    }
}