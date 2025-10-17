package pieces;

import board.Position;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece{

public Queen(Color color, Position position){
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
            return "wQ";
        }
        else{
            return "bQ";
        }
    }
}