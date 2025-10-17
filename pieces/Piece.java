package pieces;

import java.util.List;
import board.*;

public abstract class Piece{
    private Color color;
    private Position position;

    public Piece(Color color, Position postition){
        this.color = color;
        this.position = position;
    }

    public Color getColor(){
        return color;
    }

    public Position getPosition(){
        return position;
    }

    public void move(Position newPosition){
        position = newPosition;
    }

    public abstract List<Position> possibleMoves();
}