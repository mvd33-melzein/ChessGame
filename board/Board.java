package board;

import pieces.*;

public class Board{
    private Piece[][] board;

    public Board(){
        board = new Piece[8][8];
    }
    
    
    public void initialize(){
        // Black Pieces
        board[7][0] = new Rook(Color.BLACK, new Position(0,0));
        board[7][1] = new Knight(Color.BLACK, new Position(0,1));
        board[7][2] = new Bishop(Color.BLACK, new Position(0,2));
        board[7][3] = new Queen(Color.BLACK, new Position(0,3));
        board[7][4] = new King(Color.BLACK, new Position(0,4));
        board[7][5] = new Bishop(Color.BLACK, new Position(0,5));
        board[7][6] = new Knight(Color.BLACK, new Position(0,6));
        board[7][7] = new Rook(Color.BLACK, new Position(0,7));

        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(Color.BLACK, new Position(1, i));
        }

        // White pieces
        board[0][0] = new Rook(Color.WHITE, new Position(7,0));
        board[0][1] = new Knight(Color.WHITE, new Position(7,1));
        board[0][2] = new Bishop(Color.WHITE, new Position(7,2));
        board[0][3] = new Queen(Color.WHITE, new Position(7,3));
        board[0][4] = new King(Color.WHITE, new Position(7,4));
        board[0][5] = new Bishop(Color.WHITE, new Position(7,5));
        board[0][6] = new Knight(Color.WHITE, new Position(7,6));
        board[0][7] = new Rook(Color.WHITE, new Position(7,7));

        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(Color.WHITE, new Position(6, i));
        }

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
}