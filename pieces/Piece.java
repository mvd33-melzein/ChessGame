package pieces;

import java.util.List;
import board.*;

public abstract class Piece{
    protected Color color;
    protected Position position;
    protected boolean hasMoved = false;

    public Piece(Color color, Position position){
        this.color = color;
        this.position = position;
    }

    public Color getColor(){
        return color;
    }

    public Position getPosition(){
        return position;
    }

    public boolean hasMoved(){
        return hasMoved;
    }

    public void move(Position newPosition){
        position = newPosition;
        hasMoved = true;
    }

    public abstract List<Position> possibleMoves(Board board);
}