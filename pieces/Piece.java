package pieces;

import java.util.List;
import board.*;

public abstract class Piece{
    protected PlayerColor color;
    protected Position position;
    protected boolean hasMoved = false;

    public Piece(PlayerColor color, Position position){
        this.color = color;
        this.position = position;
    }

    public PlayerColor getPlayerColor(){
        return color;
    }

    public Position getPosition(){
        return position;
    }

    public void setPosition(Position newPos) {
        position = newPos;
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