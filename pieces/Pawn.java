package pieces;

import board.*;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{

    public Pawn(PlayerColor color, Position position){
        super(color, position);
    }

    @Override
    public List<Position> possibleMoves(Board board){
        List<Position> moves = new ArrayList<>();

        if(this.getPosition() == null){
            System.out.println("Piece has no position");
            return moves;
        }

        int row = position.getRow();
        int col = position.getCol();
        int direction = 0;

        if(color == PlayerColor.WHITE){
            direction = 1;
        }
        else{
            direction = -1;
        }

        // 1 step move
        Position oneStep = new Position(row + direction, col);
        if (board.isInBounds(oneStep) && board.getPiece(oneStep) == null) {
            moves.add(oneStep);

            // 2 steps on first move
            Position twoStep = new Position(row + 2 * direction, col);
            if (!hasMoved() && board.isInBounds(twoStep) && board.getPiece(twoStep) == null) {
                moves.add(twoStep);
            }
        }

        // capturing
        Position captureLeft = new Position(row + direction, col - 1);
        if (board.isInBounds(captureLeft)) {
            Piece target = board.getPiece(captureLeft);
            if (target != null && target.getPlayerColor() != this.color) {
                moves.add(captureLeft);
            }
        }

        Position captureRight = new Position(row + direction, col + 1);
        if (board.isInBounds(captureRight)) {
            Piece target = board.getPiece(captureRight);
            if (target != null && target.getPlayerColor() != this.color) {
                moves.add(captureRight);
            }
        }

        return moves;
    }
    
    @Override
    public String toString(){
        if(getPlayerColor() == PlayerColor.WHITE){
            return "wp";
        }
        else{
            return "bp";
        }
    }
    
    @Override
    public String getImagePath(){
        if(getPlayerColor() == PlayerColor.WHITE){
            return "images/WhitePawn.png";
        }
        else{
            return "images/BlackPawn.png";
        }
    }
    
}