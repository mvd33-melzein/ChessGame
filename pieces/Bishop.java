package pieces;

import board.*;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{

    public Bishop(Color color, Position position){
        super(color, position);
    }

    @Override
    public List<Position> possibleMoves(Board board){
        List<Position> moves = new ArrayList<>();
        
        int row = getPosition().getRow();
        int col = getPosition().getCol();

        int[][] directions = {{1,1},{1,-1},{-1,1},{-1,-1}};

        for (int[] d : directions) {
            int r = row + d[0];
            int c = col + d[1];
            while (board.isInBounds(new Position(r, c))) {
                Piece target = board.getPiece(new Position(r, c));
                if (target == null) {
                    moves.add(new Position(r, c));
                } else {
                    if (target.getColor() != getColor()) {
                        moves.add(new Position(r, c));
                    }
                    break;
                }
                r += d[0];
                c += d[1];
            }
        }
        return moves;
    }

    @Override
    public String toString(){
        if(getColor() == Color.WHITE){
            return "wB";
        }
        else{
            return "bB";
        }
    }
}