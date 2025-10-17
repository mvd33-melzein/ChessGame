package pieces;

import board.Position;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{

    public Knight(Color color, Position position){
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
            return "wN";
        }
        else{
            return "bN";
        }
    }
}