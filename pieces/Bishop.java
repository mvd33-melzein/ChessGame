package pieces;

import board.Position;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{

    public Bishop(Color color, Position position){
        super(color, position);
    }

    @Override
    public List<Position> possibleMoves(){
        List<Position> moves = new ArrayList<>();
        // Make list of all possible moves
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