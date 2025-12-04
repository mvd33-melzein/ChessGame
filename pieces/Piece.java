package pieces;

import java.util.List;
import board.*;

// Base class for all chess pieces
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

    // Updates piece position and marks it as having moved
    public void move(Position newPosition){
        position = newPosition;
        hasMoved = true;
    }

    // Returns all legal moves for this piece on the given board
    public abstract List<Position> possibleMoves(Board board);
    
    public abstract String getImagePath();
}