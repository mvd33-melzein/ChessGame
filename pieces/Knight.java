package pieces;

import board.*;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{

    public Knight(PlayerColor color, Position position){
        super(color, position);
    }

    @Override
    public List<Position> possibleMoves(Board board){
        List<Position> moves = new ArrayList<>();
        int row = getPosition().getRow();
        int col = getPosition().getCol();

        // Knight moves in L-shape 
        int[][] directions = {{2,1},{2,-1},{-2,1},{-2,-1},{1,2},{1,-2},{-1,2},{-1,-2}};

        for (int[] d : directions) {
            Position p = new Position(row + d[0], col + d[1]);
            if (board.isInBounds(p)) {
                Piece target = board.getPiece(p);
                if (target == null || target.getPlayerColor() != getPlayerColor()) {
                    moves.add(p);
                }
            }
        }
        return moves;
    }

    @Override
    public String toString(){
        if(getPlayerColor() == PlayerColor.WHITE){
            return "wN";
        }
        else{
            return "bN";
        }
    }
    
    @Override
    public String getImagePath(){
        if(getPlayerColor() == PlayerColor.WHITE){
            return "images/WhiteKnight.png";
        }
        else{
            return "images/BlackKnight.png";
        }
    }
}