package pieces;

import board.*;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece{

    public King(Color color, Position position){
        super(color, position);
    }

    @Override
    public List<Position> possibleMoves(Board board){
        List<Position> moves = new ArrayList<>();
        int row = getPosition().getRow();
        int col = getPosition().getCol();

        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};

        for (int[] d : directions) {
            Position p = new Position(row + d[0], col + d[1]);
            if (board.isInBounds(p)) {
                Piece target = board.getPiece(p);
                if (target == null || target.getColor() != getColor()) {
                    moves.add(p);
                }
            }
        }
        return moves;
    }

    @Override
    public String toString(){
        if(getColor() == Color.WHITE){
            return "wK";
        }
        else{
            return "bK";
        }
    }
}